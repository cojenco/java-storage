/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.storage;

import static com.google.common.truth.Truth.assertThat;

import com.google.api.core.SettableApiFuture;
import com.google.cloud.storage.WriteCtx.WriteObjectRequestBuilderFactory;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.protobuf.ByteString;
import com.google.storage.v2.Object;
import com.google.storage.v2.StartResumableWriteRequest;
import com.google.storage.v2.StartResumableWriteResponse;
import com.google.storage.v2.StorageClient;
import com.google.storage.v2.StorageGrpc.StorageImplBase;
import com.google.storage.v2.WriteObjectRequest;
import com.google.storage.v2.WriteObjectResponse;
import com.google.storage.v2.WriteObjectSpec;
import io.grpc.Status.Code;
import io.grpc.stub.CallStreamObserver;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.junit.Test;

public final class GapicUnbufferedWritableByteChannelTest {

  ChunkSegmenter segmenter = new ChunkSegmenter(Hasher.noop(), ByteStringStrategy.copy(), 10, 5);

  @Test
  public void directUpload() throws IOException, InterruptedException, ExecutionException {
    Object obj = Object.newBuilder().setBucket("buck").setName("obj").build();
    WriteObjectSpec spec = WriteObjectSpec.newBuilder().setResource(obj).build();

    byte[] bytes = DataGenerator.base64Characters().genBytes(40);
    WriteObjectRequest req1 =
        WriteObjectRequest.newBuilder()
            .setWriteObjectSpec(spec)
            .setChecksummedData(TestUtils.getChecksummedData(ByteString.copyFrom(bytes, 0, 10)))
            .build();
    WriteObjectRequest req2 =
        WriteObjectRequest.newBuilder()
            .setWriteOffset(10)
            .setChecksummedData(TestUtils.getChecksummedData(ByteString.copyFrom(bytes, 10, 10)))
            .build();
    WriteObjectRequest req3 =
        WriteObjectRequest.newBuilder()
            .setWriteOffset(20)
            .setChecksummedData(TestUtils.getChecksummedData(ByteString.copyFrom(bytes, 20, 10)))
            .build();
    WriteObjectRequest req4 =
        WriteObjectRequest.newBuilder()
            .setWriteOffset(30)
            .setChecksummedData(TestUtils.getChecksummedData(ByteString.copyFrom(bytes, 30, 10)))
            .build();
    WriteObjectRequest req5 =
        WriteObjectRequest.newBuilder().setWriteOffset(40).setFinishWrite(true).build();

    WriteObjectResponse resp =
        WriteObjectResponse.newBuilder().setResource(obj.toBuilder().setSize(40)).build();

    WriteObjectRequest base = WriteObjectRequest.newBuilder().setWriteObjectSpec(spec).build();
    WriteObjectRequestBuilderFactory reqFactory = WriteObjectRequestBuilderFactory.simple(base);

    StorageImplBase service =
        new DirectWriteService(
            ImmutableMap.of(ImmutableList.of(req1, req2, req3, req4, req5), resp));
    try (FakeServer fake = FakeServer.of(service);
        StorageClient sc = StorageClient.create(fake.storageSettings())) {
      SettableApiFuture<WriteObjectResponse> result = SettableApiFuture.create();
      try (GapicUnbufferedWritableByteChannel<?> c =
          new GapicUnbufferedWritableByteChannel<>(
              result,
              segmenter,
              reqFactory,
              WriteFlushStrategy.fsyncOnClose(sc.writeObjectCallable()))) {
        c.write(ByteBuffer.wrap(bytes));
      }
      assertThat(result.get()).isEqualTo(resp);
    }
  }

  @Test
  public void resumableUpload() throws IOException, InterruptedException, ExecutionException {
    String uploadId = "upload-id";

    Object obj = Object.newBuilder().setBucket("buck").setName("obj").build();
    WriteObjectSpec spec = WriteObjectSpec.newBuilder().setResource(obj).build();

    StartResumableWriteRequest startReq =
        StartResumableWriteRequest.newBuilder().setWriteObjectSpec(spec).build();
    StartResumableWriteResponse startResp =
        StartResumableWriteResponse.newBuilder().setUploadId(uploadId).build();

    byte[] bytes = DataGenerator.base64Characters().genBytes(40);
    WriteObjectRequest req1 =
        WriteObjectRequest.newBuilder()
            .setUploadId(uploadId)
            .setChecksummedData(TestUtils.getChecksummedData(ByteString.copyFrom(bytes, 0, 10)))
            .build();
    WriteObjectRequest req2 =
        WriteObjectRequest.newBuilder()
            .setUploadId(uploadId)
            .setWriteOffset(10)
            .setChecksummedData(TestUtils.getChecksummedData(ByteString.copyFrom(bytes, 10, 10)))
            .build();
    WriteObjectRequest req3 =
        WriteObjectRequest.newBuilder()
            .setUploadId(uploadId)
            .setWriteOffset(20)
            .setChecksummedData(TestUtils.getChecksummedData(ByteString.copyFrom(bytes, 20, 10)))
            .build();
    WriteObjectRequest req4 =
        WriteObjectRequest.newBuilder()
            .setUploadId(uploadId)
            .setWriteOffset(30)
            .setChecksummedData(TestUtils.getChecksummedData(ByteString.copyFrom(bytes, 30, 10)))
            .build();
    WriteObjectRequest req5 =
        WriteObjectRequest.newBuilder()
            .setUploadId(uploadId)
            .setWriteOffset(40)
            .setFinishWrite(true)
            .build();

    WriteObjectResponse resp1 = WriteObjectResponse.newBuilder().setPersistedSize(10).build();
    WriteObjectResponse resp2 = WriteObjectResponse.newBuilder().setPersistedSize(20).build();
    WriteObjectResponse resp3 = WriteObjectResponse.newBuilder().setPersistedSize(30).build();
    WriteObjectResponse resp4 = WriteObjectResponse.newBuilder().setPersistedSize(40).build();
    WriteObjectResponse resp5 =
        WriteObjectResponse.newBuilder().setResource(obj.toBuilder().setSize(40)).build();

    WriteObjectRequestBuilderFactory reqFactory = new ResumableWrite(startReq, startResp);

    ImmutableMap<List<WriteObjectRequest>, WriteObjectResponse> writes =
        ImmutableMap.<List<WriteObjectRequest>, WriteObjectResponse>builder()
            .put(ImmutableList.of(req1), resp1)
            .put(ImmutableList.of(req2), resp2)
            .put(ImmutableList.of(req3), resp3)
            .put(ImmutableList.of(req4), resp4)
            .put(ImmutableList.of(req5), resp5)
            .build();
    StorageImplBase service = new DirectWriteService(writes);
    try (FakeServer fake = FakeServer.of(service);
        StorageClient sc = StorageClient.create(fake.storageSettings())) {
      SettableApiFuture<WriteObjectResponse> result = SettableApiFuture.create();
      try (GapicUnbufferedWritableByteChannel<?> c =
          new GapicUnbufferedWritableByteChannel<>(
              result,
              segmenter,
              reqFactory,
              WriteFlushStrategy.fsyncEveryFlush(sc.writeObjectCallable()))) {
        ImmutableList<ByteBuffer> buffers = TestUtils.subDivide(bytes, 10);
        for (ByteBuffer buf : buffers) {
          c.write(buf);
        }
      }
      assertThat(result.get()).isEqualTo(resp5);
    }
  }

  private static class DirectWriteService extends StorageImplBase {
    private final ImmutableMap<List<WriteObjectRequest>, WriteObjectResponse> writes;

    private ImmutableList.Builder<WriteObjectRequest> requests;

    private DirectWriteService(ImmutableMap<List<WriteObjectRequest>, WriteObjectResponse> writes) {
      this.writes = writes;
      this.requests = new ImmutableList.Builder<>();
    }

    @Override
    public StreamObserver<WriteObjectRequest> writeObject(StreamObserver<WriteObjectResponse> obs) {
      return new Adapter() {
        @Override
        public void onNext(WriteObjectRequest value) {
          requests.add(value);
        }

        @Override
        public void onError(Throwable t) {}

        @Override
        public void onCompleted() {
          ImmutableList<WriteObjectRequest> build = requests.build();
          if (writes.containsKey(build)) {
            requests = new ImmutableList.Builder<>();
            obs.onNext(writes.get(build));
            obs.onCompleted();
          } else {
            obs.onError(
                TestUtils.apiException(Code.PERMISSION_DENIED, "Unexpected request chain."));
          }
        }
      };
    }
  }

  private abstract static class Adapter extends CallStreamObserver<WriteObjectRequest> {

    private Adapter() {}

    @Override
    public boolean isReady() {
      return true;
    }

    @Override
    public void setOnReadyHandler(Runnable onReadyHandler) {}

    @Override
    public void disableAutoInboundFlowControl() {}

    @Override
    public void request(int count) {}

    @Override
    public void setMessageCompression(boolean enable) {}
  }
}