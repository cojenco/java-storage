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
 * Parameters that can be passed to any request.
 * </pre>
 *
 * Protobuf type {@code google.storage.v2.CommonRequestParams}
 */
public final class CommonRequestParams extends com.google.protobuf.GeneratedMessageV3
    implements
    // @@protoc_insertion_point(message_implements:google.storage.v2.CommonRequestParams)
    CommonRequestParamsOrBuilder {
  private static final long serialVersionUID = 0L;
  // Use CommonRequestParams.newBuilder() to construct.
  private CommonRequestParams(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }

  private CommonRequestParams() {
    userProject_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
    return new CommonRequestParams();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet getUnknownFields() {
    return this.unknownFields;
  }

  private CommonRequestParams(
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

              userProject_ = s;
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
        .internal_static_google_storage_v2_CommonRequestParams_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.storage.v2.StorageProto
        .internal_static_google_storage_v2_CommonRequestParams_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.storage.v2.CommonRequestParams.class,
            com.google.storage.v2.CommonRequestParams.Builder.class);
  }

  public static final int USER_PROJECT_FIELD_NUMBER = 1;
  private volatile java.lang.Object userProject_;
  /**
   *
   *
   * <pre>
   * Required. Required when using buckets with Requestor Pays feature enabled.
   * Example: `projects/123456`.
   * </pre>
   *
   * <code>
   * string user_project = 1 [(.google.api.field_behavior) = REQUIRED, (.google.api.resource_reference) = { ... }
   * </code>
   *
   * @return The userProject.
   */
  @java.lang.Override
  public java.lang.String getUserProject() {
    java.lang.Object ref = userProject_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      userProject_ = s;
      return s;
    }
  }
  /**
   *
   *
   * <pre>
   * Required. Required when using buckets with Requestor Pays feature enabled.
   * Example: `projects/123456`.
   * </pre>
   *
   * <code>
   * string user_project = 1 [(.google.api.field_behavior) = REQUIRED, (.google.api.resource_reference) = { ... }
   * </code>
   *
   * @return The bytes for userProject.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getUserProjectBytes() {
    java.lang.Object ref = userProject_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
      userProject_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (!getUserProjectBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, userProject_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getUserProjectBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, userProject_);
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
    if (!(obj instanceof com.google.storage.v2.CommonRequestParams)) {
      return super.equals(obj);
    }
    com.google.storage.v2.CommonRequestParams other =
        (com.google.storage.v2.CommonRequestParams) obj;

    if (!getUserProject().equals(other.getUserProject())) return false;
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
    hash = (37 * hash) + USER_PROJECT_FIELD_NUMBER;
    hash = (53 * hash) + getUserProject().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.google.storage.v2.CommonRequestParams parseFrom(java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static com.google.storage.v2.CommonRequestParams parseFrom(
      java.nio.ByteBuffer data, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static com.google.storage.v2.CommonRequestParams parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static com.google.storage.v2.CommonRequestParams parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static com.google.storage.v2.CommonRequestParams parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static com.google.storage.v2.CommonRequestParams parseFrom(
      byte[] data, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static com.google.storage.v2.CommonRequestParams parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
  }

  public static com.google.storage.v2.CommonRequestParams parseFrom(
      java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(
        PARSER, input, extensionRegistry);
  }

  public static com.google.storage.v2.CommonRequestParams parseDelimitedFrom(
      java.io.InputStream input) throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }

  public static com.google.storage.v2.CommonRequestParams parseDelimitedFrom(
      java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(
        PARSER, input, extensionRegistry);
  }

  public static com.google.storage.v2.CommonRequestParams parseFrom(
      com.google.protobuf.CodedInputStream input) throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
  }

  public static com.google.storage.v2.CommonRequestParams parseFrom(
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

  public static Builder newBuilder(com.google.storage.v2.CommonRequestParams prototype) {
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
   * Parameters that can be passed to any request.
   * </pre>
   *
   * Protobuf type {@code google.storage.v2.CommonRequestParams}
   */
  public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder>
      implements
      // @@protoc_insertion_point(builder_implements:google.storage.v2.CommonRequestParams)
      com.google.storage.v2.CommonRequestParamsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
      return com.google.storage.v2.StorageProto
          .internal_static_google_storage_v2_CommonRequestParams_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.storage.v2.StorageProto
          .internal_static_google_storage_v2_CommonRequestParams_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.storage.v2.CommonRequestParams.class,
              com.google.storage.v2.CommonRequestParams.Builder.class);
    }

    // Construct using com.google.storage.v2.CommonRequestParams.newBuilder()
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
      userProject_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
      return com.google.storage.v2.StorageProto
          .internal_static_google_storage_v2_CommonRequestParams_descriptor;
    }

    @java.lang.Override
    public com.google.storage.v2.CommonRequestParams getDefaultInstanceForType() {
      return com.google.storage.v2.CommonRequestParams.getDefaultInstance();
    }

    @java.lang.Override
    public com.google.storage.v2.CommonRequestParams build() {
      com.google.storage.v2.CommonRequestParams result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.google.storage.v2.CommonRequestParams buildPartial() {
      com.google.storage.v2.CommonRequestParams result =
          new com.google.storage.v2.CommonRequestParams(this);
      result.userProject_ = userProject_;
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
      if (other instanceof com.google.storage.v2.CommonRequestParams) {
        return mergeFrom((com.google.storage.v2.CommonRequestParams) other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.storage.v2.CommonRequestParams other) {
      if (other == com.google.storage.v2.CommonRequestParams.getDefaultInstance()) return this;
      if (!other.getUserProject().isEmpty()) {
        userProject_ = other.userProject_;
        onChanged();
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
      com.google.storage.v2.CommonRequestParams parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.google.storage.v2.CommonRequestParams) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object userProject_ = "";
    /**
     *
     *
     * <pre>
     * Required. Required when using buckets with Requestor Pays feature enabled.
     * Example: `projects/123456`.
     * </pre>
     *
     * <code>
     * string user_project = 1 [(.google.api.field_behavior) = REQUIRED, (.google.api.resource_reference) = { ... }
     * </code>
     *
     * @return The userProject.
     */
    public java.lang.String getUserProject() {
      java.lang.Object ref = userProject_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        userProject_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     *
     *
     * <pre>
     * Required. Required when using buckets with Requestor Pays feature enabled.
     * Example: `projects/123456`.
     * </pre>
     *
     * <code>
     * string user_project = 1 [(.google.api.field_behavior) = REQUIRED, (.google.api.resource_reference) = { ... }
     * </code>
     *
     * @return The bytes for userProject.
     */
    public com.google.protobuf.ByteString getUserProjectBytes() {
      java.lang.Object ref = userProject_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
        userProject_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     *
     *
     * <pre>
     * Required. Required when using buckets with Requestor Pays feature enabled.
     * Example: `projects/123456`.
     * </pre>
     *
     * <code>
     * string user_project = 1 [(.google.api.field_behavior) = REQUIRED, (.google.api.resource_reference) = { ... }
     * </code>
     *
     * @param value The userProject to set.
     * @return This builder for chaining.
     */
    public Builder setUserProject(java.lang.String value) {
      if (value == null) {
        throw new NullPointerException();
      }

      userProject_ = value;
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * Required. Required when using buckets with Requestor Pays feature enabled.
     * Example: `projects/123456`.
     * </pre>
     *
     * <code>
     * string user_project = 1 [(.google.api.field_behavior) = REQUIRED, (.google.api.resource_reference) = { ... }
     * </code>
     *
     * @return This builder for chaining.
     */
    public Builder clearUserProject() {

      userProject_ = getDefaultInstance().getUserProject();
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * Required. Required when using buckets with Requestor Pays feature enabled.
     * Example: `projects/123456`.
     * </pre>
     *
     * <code>
     * string user_project = 1 [(.google.api.field_behavior) = REQUIRED, (.google.api.resource_reference) = { ... }
     * </code>
     *
     * @param value The bytes for userProject to set.
     * @return This builder for chaining.
     */
    public Builder setUserProjectBytes(com.google.protobuf.ByteString value) {
      if (value == null) {
        throw new NullPointerException();
      }
      checkByteStringIsUtf8(value);

      userProject_ = value;
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

    // @@protoc_insertion_point(builder_scope:google.storage.v2.CommonRequestParams)
  }

  // @@protoc_insertion_point(class_scope:google.storage.v2.CommonRequestParams)
  private static final com.google.storage.v2.CommonRequestParams DEFAULT_INSTANCE;

  static {
    DEFAULT_INSTANCE = new com.google.storage.v2.CommonRequestParams();
  }

  public static com.google.storage.v2.CommonRequestParams getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CommonRequestParams> PARSER =
      new com.google.protobuf.AbstractParser<CommonRequestParams>() {
        @java.lang.Override
        public CommonRequestParams parsePartialFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
          return new CommonRequestParams(input, extensionRegistry);
        }
      };

  public static com.google.protobuf.Parser<CommonRequestParams> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CommonRequestParams> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.storage.v2.CommonRequestParams getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
