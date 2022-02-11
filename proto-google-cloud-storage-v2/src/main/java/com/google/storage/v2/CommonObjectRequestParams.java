/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/storage/v2/storage.proto

package com.google.storage.v2;

/**
 *
 *
 * <pre>
 * Parameters that can be passed to any object request.
 * </pre>
 *
 * Protobuf type {@code google.storage.v2.CommonObjectRequestParams}
 */
public final class CommonObjectRequestParams extends com.google.protobuf.GeneratedMessageV3
    implements
    // @@protoc_insertion_point(message_implements:google.storage.v2.CommonObjectRequestParams)
    CommonObjectRequestParamsOrBuilder {
  private static final long serialVersionUID = 0L;
  // Use CommonObjectRequestParams.newBuilder() to construct.
  private CommonObjectRequestParams(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }

  private CommonObjectRequestParams() {
    encryptionAlgorithm_ = "";
    encryptionKeyBytes_ = com.google.protobuf.ByteString.EMPTY;
    encryptionKeySha256Bytes_ = com.google.protobuf.ByteString.EMPTY;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
    return new CommonObjectRequestParams();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet getUnknownFields() {
    return this.unknownFields;
  }

  private CommonObjectRequestParams(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10:
            {
              java.lang.String s = input.readStringRequireUtf8();

              encryptionAlgorithm_ = s;
              break;
            }
          case 34:
            {
              encryptionKeyBytes_ = input.readBytes();
              break;
            }
          case 42:
            {
              encryptionKeySha256Bytes_ = input.readBytes();
              break;
            }
          default:
            {
              if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }

  public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
    return com.google.storage.v2.StorageProto
        .internal_static_google_storage_v2_CommonObjectRequestParams_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.storage.v2.StorageProto
        .internal_static_google_storage_v2_CommonObjectRequestParams_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.storage.v2.CommonObjectRequestParams.class,
            com.google.storage.v2.CommonObjectRequestParams.Builder.class);
  }

  public static final int ENCRYPTION_ALGORITHM_FIELD_NUMBER = 1;
  private volatile java.lang.Object encryptionAlgorithm_;
  /**
   *
   *
   * <pre>
   * Encryption algorithm used with Customer-Supplied Encryption Keys feature.
   * </pre>
   *
   * <code>string encryption_algorithm = 1;</code>
   *
   * @return The encryptionAlgorithm.
   */
  @java.lang.Override
  public java.lang.String getEncryptionAlgorithm() {
    java.lang.Object ref = encryptionAlgorithm_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      encryptionAlgorithm_ = s;
      return s;
    }
  }
  /**
   *
   *
   * <pre>
   * Encryption algorithm used with Customer-Supplied Encryption Keys feature.
   * </pre>
   *
   * <code>string encryption_algorithm = 1;</code>
   *
   * @return The bytes for encryptionAlgorithm.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getEncryptionAlgorithmBytes() {
    java.lang.Object ref = encryptionAlgorithm_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
      encryptionAlgorithm_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ENCRYPTION_KEY_BYTES_FIELD_NUMBER = 4;
  private com.google.protobuf.ByteString encryptionKeyBytes_;
  /**
   *
   *
   * <pre>
   * Encryption key used with Customer-Supplied Encryption Keys feature.
   * In raw bytes format (not base64-encoded).
   * </pre>
   *
   * <code>bytes encryption_key_bytes = 4;</code>
   *
   * @return The encryptionKeyBytes.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getEncryptionKeyBytes() {
    return encryptionKeyBytes_;
  }

  public static final int ENCRYPTION_KEY_SHA256_BYTES_FIELD_NUMBER = 5;
  private com.google.protobuf.ByteString encryptionKeySha256Bytes_;
  /**
   *
   *
   * <pre>
   * SHA256 hash of encryption key used with Customer-Supplied Encryption Keys
   * feature.
   * </pre>
   *
   * <code>bytes encryption_key_sha256_bytes = 5;</code>
   *
   * @return The encryptionKeySha256Bytes.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getEncryptionKeySha256Bytes() {
    return encryptionKeySha256Bytes_;
  }

  private byte memoizedIsInitialized = -1;

  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
    if (!getEncryptionAlgorithmBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, encryptionAlgorithm_);
    }
    if (!encryptionKeyBytes_.isEmpty()) {
      output.writeBytes(4, encryptionKeyBytes_);
    }
    if (!encryptionKeySha256Bytes_.isEmpty()) {
      output.writeBytes(5, encryptionKeySha256Bytes_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getEncryptionAlgorithmBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, encryptionAlgorithm_);
    }
    if (!encryptionKeyBytes_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream.computeBytesSize(4, encryptionKeyBytes_);
    }
    if (!encryptionKeySha256Bytes_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream.computeBytesSize(5, encryptionKeySha256Bytes_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof com.google.storage.v2.CommonObjectRequestParams)) {
      return super.equals(obj);
    }
    com.google.storage.v2.CommonObjectRequestParams other =
        (com.google.storage.v2.CommonObjectRequestParams) obj;

    if (!getEncryptionAlgorithm().equals(other.getEncryptionAlgorithm())) return false;
    if (!getEncryptionKeyBytes().equals(other.getEncryptionKeyBytes())) return false;
    if (!getEncryptionKeySha256Bytes().equals(other.getEncryptionKeySha256Bytes())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ENCRYPTION_ALGORITHM_FIELD_NUMBER;
    hash = (53 * hash) + getEncryptionAlgorithm().hashCode();
    hash = (37 * hash) + ENCRYPTION_KEY_BYTES_FIELD_NUMBER;
    hash = (53 * hash) + getEncryptionKeyBytes().hashCode();
    hash = (37 * hash) + ENCRYPTION_KEY_SHA256_BYTES_FIELD_NUMBER;
    hash = (53 * hash) + getEncryptionKeySha256Bytes().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.google.storage.v2.CommonObjectRequestParams parseFrom(java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static com.google.storage.v2.CommonObjectRequestParams parseFrom(
      java.nio.ByteBuffer data, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static com.google.storage.v2.CommonObjectRequestParams parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static com.google.storage.v2.CommonObjectRequestParams parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static com.google.storage.v2.CommonObjectRequestParams parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static com.google.storage.v2.CommonObjectRequestParams parseFrom(
      byte[] data, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static com.google.storage.v2.CommonObjectRequestParams parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
  }

  public static com.google.storage.v2.CommonObjectRequestParams parseFrom(
      java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(
        PARSER, input, extensionRegistry);
  }

  public static com.google.storage.v2.CommonObjectRequestParams parseDelimitedFrom(
      java.io.InputStream input) throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }

  public static com.google.storage.v2.CommonObjectRequestParams parseDelimitedFrom(
      java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(
        PARSER, input, extensionRegistry);
  }

  public static com.google.storage.v2.CommonObjectRequestParams parseFrom(
      com.google.protobuf.CodedInputStream input) throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
  }

  public static com.google.storage.v2.CommonObjectRequestParams parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(
        PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() {
    return newBuilder();
  }

  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }

  public static Builder newBuilder(com.google.storage.v2.CommonObjectRequestParams prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }

  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   *
   *
   * <pre>
   * Parameters that can be passed to any object request.
   * </pre>
   *
   * Protobuf type {@code google.storage.v2.CommonObjectRequestParams}
   */
  public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder>
      implements
      // @@protoc_insertion_point(builder_implements:google.storage.v2.CommonObjectRequestParams)
      com.google.storage.v2.CommonObjectRequestParamsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
      return com.google.storage.v2.StorageProto
          .internal_static_google_storage_v2_CommonObjectRequestParams_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.storage.v2.StorageProto
          .internal_static_google_storage_v2_CommonObjectRequestParams_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.storage.v2.CommonObjectRequestParams.class,
              com.google.storage.v2.CommonObjectRequestParams.Builder.class);
    }

    // Construct using com.google.storage.v2.CommonObjectRequestParams.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders) {}
    }

    @java.lang.Override
    public Builder clear() {
      super.clear();
      encryptionAlgorithm_ = "";

      encryptionKeyBytes_ = com.google.protobuf.ByteString.EMPTY;

      encryptionKeySha256Bytes_ = com.google.protobuf.ByteString.EMPTY;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
      return com.google.storage.v2.StorageProto
          .internal_static_google_storage_v2_CommonObjectRequestParams_descriptor;
    }

    @java.lang.Override
    public com.google.storage.v2.CommonObjectRequestParams getDefaultInstanceForType() {
      return com.google.storage.v2.CommonObjectRequestParams.getDefaultInstance();
    }

    @java.lang.Override
    public com.google.storage.v2.CommonObjectRequestParams build() {
      com.google.storage.v2.CommonObjectRequestParams result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.google.storage.v2.CommonObjectRequestParams buildPartial() {
      com.google.storage.v2.CommonObjectRequestParams result =
          new com.google.storage.v2.CommonObjectRequestParams(this);
      result.encryptionAlgorithm_ = encryptionAlgorithm_;
      result.encryptionKeyBytes_ = encryptionKeyBytes_;
      result.encryptionKeySha256Bytes_ = encryptionKeySha256Bytes_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }

    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field, java.lang.Object value) {
      return super.setField(field, value);
    }

    @java.lang.Override
    public Builder clearField(com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }

    @java.lang.Override
    public Builder clearOneof(com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }

    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field, int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }

    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field, java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.google.storage.v2.CommonObjectRequestParams) {
        return mergeFrom((com.google.storage.v2.CommonObjectRequestParams) other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.storage.v2.CommonObjectRequestParams other) {
      if (other == com.google.storage.v2.CommonObjectRequestParams.getDefaultInstance())
        return this;
      if (!other.getEncryptionAlgorithm().isEmpty()) {
        encryptionAlgorithm_ = other.encryptionAlgorithm_;
        onChanged();
      }
      if (other.getEncryptionKeyBytes() != com.google.protobuf.ByteString.EMPTY) {
        setEncryptionKeyBytes(other.getEncryptionKeyBytes());
      }
      if (other.getEncryptionKeySha256Bytes() != com.google.protobuf.ByteString.EMPTY) {
        setEncryptionKeySha256Bytes(other.getEncryptionKeySha256Bytes());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.google.storage.v2.CommonObjectRequestParams parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.google.storage.v2.CommonObjectRequestParams) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object encryptionAlgorithm_ = "";
    /**
     *
     *
     * <pre>
     * Encryption algorithm used with Customer-Supplied Encryption Keys feature.
     * </pre>
     *
     * <code>string encryption_algorithm = 1;</code>
     *
     * @return The encryptionAlgorithm.
     */
    public java.lang.String getEncryptionAlgorithm() {
      java.lang.Object ref = encryptionAlgorithm_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        encryptionAlgorithm_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     *
     *
     * <pre>
     * Encryption algorithm used with Customer-Supplied Encryption Keys feature.
     * </pre>
     *
     * <code>string encryption_algorithm = 1;</code>
     *
     * @return The bytes for encryptionAlgorithm.
     */
    public com.google.protobuf.ByteString getEncryptionAlgorithmBytes() {
      java.lang.Object ref = encryptionAlgorithm_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
        encryptionAlgorithm_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     *
     *
     * <pre>
     * Encryption algorithm used with Customer-Supplied Encryption Keys feature.
     * </pre>
     *
     * <code>string encryption_algorithm = 1;</code>
     *
     * @param value The encryptionAlgorithm to set.
     * @return This builder for chaining.
     */
    public Builder setEncryptionAlgorithm(java.lang.String value) {
      if (value == null) {
        throw new NullPointerException();
      }

      encryptionAlgorithm_ = value;
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * Encryption algorithm used with Customer-Supplied Encryption Keys feature.
     * </pre>
     *
     * <code>string encryption_algorithm = 1;</code>
     *
     * @return This builder for chaining.
     */
    public Builder clearEncryptionAlgorithm() {

      encryptionAlgorithm_ = getDefaultInstance().getEncryptionAlgorithm();
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * Encryption algorithm used with Customer-Supplied Encryption Keys feature.
     * </pre>
     *
     * <code>string encryption_algorithm = 1;</code>
     *
     * @param value The bytes for encryptionAlgorithm to set.
     * @return This builder for chaining.
     */
    public Builder setEncryptionAlgorithmBytes(com.google.protobuf.ByteString value) {
      if (value == null) {
        throw new NullPointerException();
      }
      checkByteStringIsUtf8(value);

      encryptionAlgorithm_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString encryptionKeyBytes_ =
        com.google.protobuf.ByteString.EMPTY;
    /**
     *
     *
     * <pre>
     * Encryption key used with Customer-Supplied Encryption Keys feature.
     * In raw bytes format (not base64-encoded).
     * </pre>
     *
     * <code>bytes encryption_key_bytes = 4;</code>
     *
     * @return The encryptionKeyBytes.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getEncryptionKeyBytes() {
      return encryptionKeyBytes_;
    }
    /**
     *
     *
     * <pre>
     * Encryption key used with Customer-Supplied Encryption Keys feature.
     * In raw bytes format (not base64-encoded).
     * </pre>
     *
     * <code>bytes encryption_key_bytes = 4;</code>
     *
     * @param value The encryptionKeyBytes to set.
     * @return This builder for chaining.
     */
    public Builder setEncryptionKeyBytes(com.google.protobuf.ByteString value) {
      if (value == null) {
        throw new NullPointerException();
      }

      encryptionKeyBytes_ = value;
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * Encryption key used with Customer-Supplied Encryption Keys feature.
     * In raw bytes format (not base64-encoded).
     * </pre>
     *
     * <code>bytes encryption_key_bytes = 4;</code>
     *
     * @return This builder for chaining.
     */
    public Builder clearEncryptionKeyBytes() {

      encryptionKeyBytes_ = getDefaultInstance().getEncryptionKeyBytes();
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString encryptionKeySha256Bytes_ =
        com.google.protobuf.ByteString.EMPTY;
    /**
     *
     *
     * <pre>
     * SHA256 hash of encryption key used with Customer-Supplied Encryption Keys
     * feature.
     * </pre>
     *
     * <code>bytes encryption_key_sha256_bytes = 5;</code>
     *
     * @return The encryptionKeySha256Bytes.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getEncryptionKeySha256Bytes() {
      return encryptionKeySha256Bytes_;
    }
    /**
     *
     *
     * <pre>
     * SHA256 hash of encryption key used with Customer-Supplied Encryption Keys
     * feature.
     * </pre>
     *
     * <code>bytes encryption_key_sha256_bytes = 5;</code>
     *
     * @param value The encryptionKeySha256Bytes to set.
     * @return This builder for chaining.
     */
    public Builder setEncryptionKeySha256Bytes(com.google.protobuf.ByteString value) {
      if (value == null) {
        throw new NullPointerException();
      }

      encryptionKeySha256Bytes_ = value;
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * SHA256 hash of encryption key used with Customer-Supplied Encryption Keys
     * feature.
     * </pre>
     *
     * <code>bytes encryption_key_sha256_bytes = 5;</code>
     *
     * @return This builder for chaining.
     */
    public Builder clearEncryptionKeySha256Bytes() {

      encryptionKeySha256Bytes_ = getDefaultInstance().getEncryptionKeySha256Bytes();
      onChanged();
      return this;
    }

    @java.lang.Override
    public final Builder setUnknownFields(final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }

    // @@protoc_insertion_point(builder_scope:google.storage.v2.CommonObjectRequestParams)
  }

  // @@protoc_insertion_point(class_scope:google.storage.v2.CommonObjectRequestParams)
  private static final com.google.storage.v2.CommonObjectRequestParams DEFAULT_INSTANCE;

  static {
    DEFAULT_INSTANCE = new com.google.storage.v2.CommonObjectRequestParams();
  }

  public static com.google.storage.v2.CommonObjectRequestParams getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CommonObjectRequestParams> PARSER =
      new com.google.protobuf.AbstractParser<CommonObjectRequestParams>() {
        @java.lang.Override
        public CommonObjectRequestParams parsePartialFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
          return new CommonObjectRequestParams(input, extensionRegistry);
        }
      };

  public static com.google.protobuf.Parser<CommonObjectRequestParams> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CommonObjectRequestParams> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.storage.v2.CommonObjectRequestParams getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
