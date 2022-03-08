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

public interface UpdateObjectRequestOrBuilder
    extends
    // @@protoc_insertion_point(interface_extends:google.storage.v2.UpdateObjectRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   *
   *
   * <pre>
   * The object to update.
   * The object's bucket and name fields are used to identify the object to
   * update. If present, the object's generation field selects a specific
   * revision of this object whose metadata should be updated. Otherwise,
   * assumes the current, live version of the object.
   * </pre>
   *
   * <code>.google.storage.v2.Object object = 1;</code>
   *
   * @return Whether the object field is set.
   */
  boolean hasObject();
  /**
   *
   *
   * <pre>
   * The object to update.
   * The object's bucket and name fields are used to identify the object to
   * update. If present, the object's generation field selects a specific
   * revision of this object whose metadata should be updated. Otherwise,
   * assumes the current, live version of the object.
   * </pre>
   *
   * <code>.google.storage.v2.Object object = 1;</code>
   *
   * @return The object.
   */
  com.google.storage.v2.Object getObject();
  /**
   *
   *
   * <pre>
   * The object to update.
   * The object's bucket and name fields are used to identify the object to
   * update. If present, the object's generation field selects a specific
   * revision of this object whose metadata should be updated. Otherwise,
   * assumes the current, live version of the object.
   * </pre>
   *
   * <code>.google.storage.v2.Object object = 1;</code>
   */
  com.google.storage.v2.ObjectOrBuilder getObjectOrBuilder();

  /**
   *
   *
   * <pre>
   * Makes the operation conditional on whether the object's current generation
   * matches the given value. Setting to 0 makes the operation succeed only if
   * there are no live versions of the object.
   * </pre>
   *
   * <code>optional int64 if_generation_match = 2;</code>
   *
   * @return Whether the ifGenerationMatch field is set.
   */
  boolean hasIfGenerationMatch();
  /**
   *
   *
   * <pre>
   * Makes the operation conditional on whether the object's current generation
   * matches the given value. Setting to 0 makes the operation succeed only if
   * there are no live versions of the object.
   * </pre>
   *
   * <code>optional int64 if_generation_match = 2;</code>
   *
   * @return The ifGenerationMatch.
   */
  long getIfGenerationMatch();

  /**
   *
   *
   * <pre>
   * Makes the operation conditional on whether the object's current generation
   * does not match the given value. If no live object exists, the precondition
   * fails. Setting to 0 makes the operation succeed only if there is a live
   * version of the object.
   * </pre>
   *
   * <code>optional int64 if_generation_not_match = 3;</code>
   *
   * @return Whether the ifGenerationNotMatch field is set.
   */
  boolean hasIfGenerationNotMatch();
  /**
   *
   *
   * <pre>
   * Makes the operation conditional on whether the object's current generation
   * does not match the given value. If no live object exists, the precondition
   * fails. Setting to 0 makes the operation succeed only if there is a live
   * version of the object.
   * </pre>
   *
   * <code>optional int64 if_generation_not_match = 3;</code>
   *
   * @return The ifGenerationNotMatch.
   */
  long getIfGenerationNotMatch();

  /**
   *
   *
   * <pre>
   * Makes the operation conditional on whether the object's current
   * metageneration matches the given value.
   * </pre>
   *
   * <code>optional int64 if_metageneration_match = 4;</code>
   *
   * @return Whether the ifMetagenerationMatch field is set.
   */
  boolean hasIfMetagenerationMatch();
  /**
   *
   *
   * <pre>
   * Makes the operation conditional on whether the object's current
   * metageneration matches the given value.
   * </pre>
   *
   * <code>optional int64 if_metageneration_match = 4;</code>
   *
   * @return The ifMetagenerationMatch.
   */
  long getIfMetagenerationMatch();

  /**
   *
   *
   * <pre>
   * Makes the operation conditional on whether the object's current
   * metageneration does not match the given value.
   * </pre>
   *
   * <code>optional int64 if_metageneration_not_match = 5;</code>
   *
   * @return Whether the ifMetagenerationNotMatch field is set.
   */
  boolean hasIfMetagenerationNotMatch();
  /**
   *
   *
   * <pre>
   * Makes the operation conditional on whether the object's current
   * metageneration does not match the given value.
   * </pre>
   *
   * <code>optional int64 if_metageneration_not_match = 5;</code>
   *
   * @return The ifMetagenerationNotMatch.
   */
  long getIfMetagenerationNotMatch();

  /**
   *
   *
   * <pre>
   * Apply a predefined set of access controls to this object.
   * </pre>
   *
   * <code>.google.storage.v2.PredefinedObjectAcl predefined_acl = 6;</code>
   *
   * @return The enum numeric value on the wire for predefinedAcl.
   */
  int getPredefinedAclValue();
  /**
   *
   *
   * <pre>
   * Apply a predefined set of access controls to this object.
   * </pre>
   *
   * <code>.google.storage.v2.PredefinedObjectAcl predefined_acl = 6;</code>
   *
   * @return The predefinedAcl.
   */
  com.google.storage.v2.PredefinedObjectAcl getPredefinedAcl();

  /**
   *
   *
   * <pre>
   * List of fields to be updated.
   * To specify ALL fields, equivalent to the JSON API's "update" function,
   * specify a single field with the value `*`. Note: not recommended. If a new
   * field is introduced at a later time, an older client updating with the `*`
   * may accidentally reset the new field's value.
   * Not specifying any fields is an error.
   * Not specifying a field while setting that field to a non-default value is
   * an error.
   * </pre>
   *
   * <code>.google.protobuf.FieldMask update_mask = 7;</code>
   *
   * @return Whether the updateMask field is set.
   */
  boolean hasUpdateMask();
  /**
   *
   *
   * <pre>
   * List of fields to be updated.
   * To specify ALL fields, equivalent to the JSON API's "update" function,
   * specify a single field with the value `*`. Note: not recommended. If a new
   * field is introduced at a later time, an older client updating with the `*`
   * may accidentally reset the new field's value.
   * Not specifying any fields is an error.
   * Not specifying a field while setting that field to a non-default value is
   * an error.
   * </pre>
   *
   * <code>.google.protobuf.FieldMask update_mask = 7;</code>
   *
   * @return The updateMask.
   */
  com.google.protobuf.FieldMask getUpdateMask();
  /**
   *
   *
   * <pre>
   * List of fields to be updated.
   * To specify ALL fields, equivalent to the JSON API's "update" function,
   * specify a single field with the value `*`. Note: not recommended. If a new
   * field is introduced at a later time, an older client updating with the `*`
   * may accidentally reset the new field's value.
   * Not specifying any fields is an error.
   * Not specifying a field while setting that field to a non-default value is
   * an error.
   * </pre>
   *
   * <code>.google.protobuf.FieldMask update_mask = 7;</code>
   */
  com.google.protobuf.FieldMaskOrBuilder getUpdateMaskOrBuilder();

  /**
   *
   *
   * <pre>
   * A set of parameters common to Storage API requests concerning an object.
   * </pre>
   *
   * <code>.google.storage.v2.CommonObjectRequestParams common_object_request_params = 8;</code>
   *
   * @return Whether the commonObjectRequestParams field is set.
   */
  boolean hasCommonObjectRequestParams();
  /**
   *
   *
   * <pre>
   * A set of parameters common to Storage API requests concerning an object.
   * </pre>
   *
   * <code>.google.storage.v2.CommonObjectRequestParams common_object_request_params = 8;</code>
   *
   * @return The commonObjectRequestParams.
   */
  com.google.storage.v2.CommonObjectRequestParams getCommonObjectRequestParams();
  /**
   *
   *
   * <pre>
   * A set of parameters common to Storage API requests concerning an object.
   * </pre>
   *
   * <code>.google.storage.v2.CommonObjectRequestParams common_object_request_params = 8;</code>
   */
  com.google.storage.v2.CommonObjectRequestParamsOrBuilder getCommonObjectRequestParamsOrBuilder();

  /**
   *
   *
   * <pre>
   * A set of parameters common to all Storage API requests.
   * </pre>
   *
   * <code>.google.storage.v2.CommonRequestParams common_request_params = 9;</code>
   *
   * @return Whether the commonRequestParams field is set.
   */
  boolean hasCommonRequestParams();
  /**
   *
   *
   * <pre>
   * A set of parameters common to all Storage API requests.
   * </pre>
   *
   * <code>.google.storage.v2.CommonRequestParams common_request_params = 9;</code>
   *
   * @return The commonRequestParams.
   */
  com.google.storage.v2.CommonRequestParams getCommonRequestParams();
  /**
   *
   *
   * <pre>
   * A set of parameters common to all Storage API requests.
   * </pre>
   *
   * <code>.google.storage.v2.CommonRequestParams common_request_params = 9;</code>
   */
  com.google.storage.v2.CommonRequestParamsOrBuilder getCommonRequestParamsOrBuilder();
}