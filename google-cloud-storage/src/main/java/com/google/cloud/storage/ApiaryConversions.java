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

import static com.google.common.base.MoreObjects.firstNonNull;
import static com.google.common.collect.Lists.newArrayList;

import com.google.api.client.util.Data;
import com.google.api.client.util.DateTime;
import com.google.api.services.storage.model.Bucket;
import com.google.api.services.storage.model.Bucket.Encryption;
import com.google.api.services.storage.model.Bucket.Lifecycle;
import com.google.api.services.storage.model.Bucket.Lifecycle.Rule;
import com.google.api.services.storage.model.Bucket.Versioning;
import com.google.api.services.storage.model.Bucket.Website;
import com.google.api.services.storage.model.BucketAccessControl;
import com.google.api.services.storage.model.ObjectAccessControl;
import com.google.api.services.storage.model.StorageObject;
import com.google.api.services.storage.model.StorageObject.Owner;
import com.google.cloud.storage.Acl.Domain;
import com.google.cloud.storage.Acl.Entity;
import com.google.cloud.storage.Acl.Group;
import com.google.cloud.storage.Acl.Project;
import com.google.cloud.storage.Acl.RawEntity;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;
import com.google.cloud.storage.BlobInfo.CustomerEncryption;
import com.google.cloud.storage.BucketInfo.AgeDeleteRule;
import com.google.cloud.storage.BucketInfo.BuilderImpl;
import com.google.cloud.storage.BucketInfo.CreatedBeforeDeleteRule;
import com.google.cloud.storage.BucketInfo.DeleteRule;
import com.google.cloud.storage.BucketInfo.IamConfiguration;
import com.google.cloud.storage.BucketInfo.IsLiveDeleteRule;
import com.google.cloud.storage.BucketInfo.LifecycleRule;
import com.google.cloud.storage.BucketInfo.LifecycleRule.DeleteLifecycleAction;
import com.google.cloud.storage.BucketInfo.LifecycleRule.LifecycleAction;
import com.google.cloud.storage.BucketInfo.LifecycleRule.LifecycleCondition;
import com.google.cloud.storage.BucketInfo.LifecycleRule.SetStorageClassLifecycleAction;
import com.google.cloud.storage.BucketInfo.Logging;
import com.google.cloud.storage.BucketInfo.NumNewerVersionsDeleteRule;
import com.google.cloud.storage.BucketInfo.PublicAccessPrevention;
import com.google.cloud.storage.BucketInfo.RawDeleteRule;
import com.google.cloud.storage.Conversions.Codec;
import com.google.cloud.storage.Cors.Origin;
import com.google.cloud.storage.HmacKey.HmacKeyMetadata;
import com.google.cloud.storage.HmacKey.HmacKeyState;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class ApiaryConversions {

  static final Codec<Entity, String> ENTITY_CODEC =
      Codec.of(ApiaryConversions::encode, ApiaryConversions::decode);

  static StorageObject encode(BlobInfo blobInfo) {
    StorageObject storageObject = encode(blobInfo.getBlobId());
    if (blobInfo.getAcl() != null) {
      storageObject.setAcl(
          blobInfo.getAcl().stream()
              .map(ApiaryConversions::encodeObject)
              .collect(ImmutableList.toImmutableList()));
    }
    if (blobInfo.getDeleteTime() != null) {
      storageObject.setTimeDeleted(new DateTime(blobInfo.getDeleteTime()));
    }
    if (blobInfo.getUpdateTime() != null) {
      storageObject.setUpdated(new DateTime(blobInfo.getUpdateTime()));
    }
    if (blobInfo.getCreateTime() != null) {
      storageObject.setTimeCreated(new DateTime(blobInfo.getCreateTime()));
    }
    if (blobInfo.getCustomTime() != null) {
      storageObject.setCustomTime(new DateTime(blobInfo.getCustomTime()));
    }
    if (blobInfo.getSize() != null) {
      storageObject.setSize(BigInteger.valueOf(blobInfo.getSize()));
    }
    if (blobInfo.getOwner() != null) {
      storageObject.setOwner(new Owner().setEntity(encode(blobInfo.getOwner())));
    }
    if (blobInfo.getStorageClass() != null) {
      storageObject.setStorageClass(blobInfo.getStorageClass().toString());
    }
    if (blobInfo.getTimeStorageClassUpdated() != null) {
      storageObject.setTimeStorageClassUpdated(new DateTime(blobInfo.getTimeStorageClassUpdated()));
    }

    Map<String, String> pbMetadata = blobInfo.getMetadata();
    if (blobInfo.getMetadata() != null && !Data.isNull(blobInfo.getMetadata())) {
      pbMetadata = Maps.newHashMapWithExpectedSize(blobInfo.getMetadata().size());
      for (Map.Entry<String, String> entry : blobInfo.getMetadata().entrySet()) {
        pbMetadata.put(entry.getKey(), firstNonNull(entry.getValue(), Data.nullOf(String.class)));
      }
    }
    if (blobInfo.getCustomerEncryption() != null) {
      storageObject.setCustomerEncryption(encode(blobInfo.getCustomerEncryption()));
    }
    if (blobInfo.getRetentionExpirationTime() != null) {
      storageObject.setRetentionExpirationTime(new DateTime(blobInfo.getRetentionExpirationTime()));
    }
    storageObject.setKmsKeyName(blobInfo.getKmsKeyName());
    storageObject.setEventBasedHold(blobInfo.getEventBasedHold());
    storageObject.setTemporaryHold(blobInfo.getTemporaryHold());
    storageObject.setMetadata(pbMetadata);
    storageObject.setCacheControl(blobInfo.getCacheControl());
    storageObject.setContentEncoding(blobInfo.getContentEncoding());
    storageObject.setCrc32c(blobInfo.getCrc32c());
    storageObject.setContentType(blobInfo.getContentType());
    storageObject.setMd5Hash(blobInfo.getMd5());
    storageObject.setMediaLink(blobInfo.getMediaLink());
    storageObject.setMetageneration(blobInfo.getMetageneration());
    storageObject.setContentDisposition(blobInfo.getContentDisposition());
    storageObject.setComponentCount(blobInfo.getComponentCount());
    storageObject.setContentLanguage(blobInfo.getContentLanguage());
    storageObject.setEtag(blobInfo.getEtag());
    storageObject.setId(blobInfo.getGeneratedId());
    storageObject.setSelfLink(blobInfo.getSelfLink());
    return storageObject;
  }

  static BlobInfo decode(StorageObject storageObject) {
    BlobInfo.Builder builder = BlobInfo.newBuilder(decodeId(storageObject));
    if (storageObject.getCacheControl() != null) {
      builder.setCacheControl(storageObject.getCacheControl());
    }
    if (storageObject.getContentEncoding() != null) {
      builder.setContentEncoding(storageObject.getContentEncoding());
    }
    if (storageObject.getCrc32c() != null) {
      builder.setCrc32c(storageObject.getCrc32c());
    }
    if (storageObject.getContentType() != null) {
      builder.setContentType(storageObject.getContentType());
    }
    if (storageObject.getMd5Hash() != null) {
      builder.setMd5(storageObject.getMd5Hash());
    }
    if (storageObject.getMediaLink() != null) {
      builder.setMediaLink(storageObject.getMediaLink());
    }
    if (storageObject.getMetageneration() != null) {
      builder.setMetageneration(storageObject.getMetageneration());
    }
    if (storageObject.getContentDisposition() != null) {
      builder.setContentDisposition(storageObject.getContentDisposition());
    }
    if (storageObject.getComponentCount() != null) {
      builder.setComponentCount(storageObject.getComponentCount());
    }
    if (storageObject.getContentLanguage() != null) {
      builder.setContentLanguage(storageObject.getContentLanguage());
    }
    if (storageObject.getEtag() != null) {
      builder.setEtag(storageObject.getEtag());
    }
    if (storageObject.getId() != null) {
      builder.setGeneratedId(storageObject.getId());
    }
    if (storageObject.getSelfLink() != null) {
      builder.setSelfLink(storageObject.getSelfLink());
    }
    if (storageObject.getMetadata() != null) {
      builder.setMetadata(storageObject.getMetadata());
    }
    if (storageObject.getTimeDeleted() != null) {
      builder.setDeleteTime(storageObject.getTimeDeleted().getValue());
    }
    if (storageObject.getUpdated() != null) {
      builder.setUpdateTime(storageObject.getUpdated().getValue());
    }
    if (storageObject.getTimeCreated() != null) {
      builder.setCreateTime(storageObject.getTimeCreated().getValue());
    }
    if (storageObject.getCustomTime() != null) {
      builder.setCustomTime(storageObject.getCustomTime().getValue());
    }
    if (storageObject.getSize() != null) {
      builder.setSize(storageObject.getSize().longValue());
    }
    if (storageObject.getOwner() != null) {
      builder.setOwner(decode(storageObject.getOwner().getEntity()));
    }
    if (storageObject.getAcl() != null) {
      builder.setAcl(
          storageObject.getAcl().stream()
              .map(ApiaryConversions::decode)
              .collect(ImmutableList.toImmutableList()));
    }
    if (storageObject.containsKey("isDirectory")) {
      builder.setIsDirectory(Boolean.TRUE);
    }
    if (storageObject.getCustomerEncryption() != null) {
      builder.setCustomerEncryption(decode(storageObject.getCustomerEncryption()));
    }
    if (storageObject.getStorageClass() != null) {
      builder.setStorageClass(StorageClass.valueOf(storageObject.getStorageClass()));
    }
    if (storageObject.getTimeStorageClassUpdated() != null) {
      builder.setTimeStorageClassUpdated(storageObject.getTimeStorageClassUpdated().getValue());
    }
    if (storageObject.getKmsKeyName() != null) {
      builder.setKmsKeyName(storageObject.getKmsKeyName());
    }
    if (storageObject.getEventBasedHold() != null) {
      builder.setEventBasedHold(storageObject.getEventBasedHold());
    }
    if (storageObject.getTemporaryHold() != null) {
      builder.setTemporaryHold(storageObject.getTemporaryHold());
    }
    if (storageObject.getRetentionExpirationTime() != null) {
      builder.setRetentionExpirationTime(storageObject.getRetentionExpirationTime().getValue());
    }
    return builder.build();
  }

  static StorageObject encode(BlobId blobId) {
    StorageObject storageObject = new StorageObject();
    storageObject.setBucket(blobId.getBucket());
    storageObject.setName(blobId.getName());
    storageObject.setGeneration(blobId.getGeneration());
    return storageObject;
  }

  static BlobId decodeId(StorageObject storageObject) {
    return BlobId.of(
        storageObject.getBucket(), storageObject.getName(), storageObject.getGeneration());
  }

  static StorageObject.CustomerEncryption encode(CustomerEncryption customerEncryption) {
    return new StorageObject.CustomerEncryption()
        .setEncryptionAlgorithm(customerEncryption.getEncryptionAlgorithm())
        .setKeySha256(customerEncryption.getKeySha256());
  }

  static CustomerEncryption decode(StorageObject.CustomerEncryption customerEncryptionPb) {
    return new CustomerEncryption(
        customerEncryptionPb.getEncryptionAlgorithm(), customerEncryptionPb.getKeySha256());
  }

  static com.google.api.services.storage.model.Bucket encode(BucketInfo bucketInfo) {
    com.google.api.services.storage.model.Bucket bucketPb =
        new com.google.api.services.storage.model.Bucket();
    bucketPb.setId(bucketInfo.getGeneratedId());
    bucketPb.setName(bucketInfo.getName());
    bucketPb.setEtag(bucketInfo.getEtag());
    if (bucketInfo.getCreateTime() != null) {
      bucketPb.setTimeCreated(new DateTime(bucketInfo.getCreateTime()));
    }
    if (bucketInfo.getUpdateTime() != null) {
      bucketPb.setUpdated(new DateTime(bucketInfo.getUpdateTime()));
    }
    if (bucketInfo.getMetageneration() != null) {
      bucketPb.setMetageneration(bucketInfo.getMetageneration());
    }
    if (bucketInfo.getLocation() != null) {
      bucketPb.setLocation(bucketInfo.getLocation());
    }
    if (bucketInfo.getLocationType() != null) {
      bucketPb.setLocationType(bucketInfo.getLocationType());
    }
    if (bucketInfo.getRpo() != null) {
      bucketPb.setRpo(bucketInfo.getRpo().toString());
    }
    if (bucketInfo.getStorageClass() != null) {
      bucketPb.setStorageClass(bucketInfo.getStorageClass().toString());
    }
    if (bucketInfo.getCors() != null) {
      bucketPb.setCors(
          bucketInfo.getCors().stream()
              .map((Function<Cors, Bucket.Cors>) ApiaryConversions::encode)
              .collect(ImmutableList.toImmutableList()));
    }
    if (bucketInfo.getAcl() != null) {
      bucketPb.setAcl(
          bucketInfo.getAcl().stream()
              .map(ApiaryConversions::encodeBucket)
              .collect(ImmutableList.toImmutableList()));
    }
    if (bucketInfo.getDefaultAcl() != null) {
      bucketPb.setDefaultObjectAcl(
          bucketInfo.getDefaultAcl().stream()
              .map(ApiaryConversions::encodeObject)
              .collect(ImmutableList.toImmutableList()));
    }
    if (bucketInfo.getOwner() != null) {
      bucketPb.setOwner(new Bucket.Owner().setEntity(encode(bucketInfo.getOwner())));
    }
    bucketPb.setSelfLink(bucketInfo.getSelfLink());
    if (bucketInfo.versioningEnabled() != null) {
      bucketPb.setVersioning(new Versioning().setEnabled(bucketInfo.versioningEnabled()));
    }
    if (bucketInfo.requesterPays() != null) {
      Bucket.Billing billing = new Bucket.Billing();
      billing.setRequesterPays(bucketInfo.requesterPays());
      bucketPb.setBilling(billing);
    }
    if (bucketInfo.getIndexPage() != null || bucketInfo.getNotFoundPage() != null) {
      Website website = new Website();
      website.setMainPageSuffix(bucketInfo.getIndexPage());
      website.setNotFoundPage(bucketInfo.getNotFoundPage());
      bucketPb.setWebsite(website);
    }

    @SuppressWarnings("deprecation")
    List<? extends DeleteRule> deleteRules = bucketInfo.getDeleteRules();
    List<? extends LifecycleRule> lifecycleRules = bucketInfo.getLifecycleRules();
    if (deleteRules != null || lifecycleRules != null) {
      Lifecycle lifecycle = new Lifecycle();

      // Here we determine if we need to "clear" any defined Lifecycle rules by explicitly setting
      // the Rule list of lifecycle to the empty list.
      // In order for us to clear the rules, one of the three following must be true:
      //   1. deleteRules is null while lifecycleRules is non-null and empty
      //   2. lifecycleRules is null while deleteRules is non-null and empty
      //   3. lifecycleRules is non-null and empty while deleteRules is non-null and empty
      // If none of the above three is true, we will interpret as the Lifecycle rules being
      // updated to the defined set of DeleteRule and LifecycleRule.
      if ((deleteRules == null && lifecycleRules.isEmpty())
          || (lifecycleRules == null && deleteRules.isEmpty())
          || (deleteRules != null && deleteRules.isEmpty() && lifecycleRules.isEmpty())) {
        lifecycle.setRule(Collections.emptyList());
      } else {
        Set<Rule> rules = new HashSet<>();
        if (deleteRules != null) {
          rules.addAll(
              deleteRules.stream()
                  .map(ApiaryConversions::encode)
                  .collect(ImmutableList.toImmutableList()));
        }
        if (lifecycleRules != null) {
          rules.addAll(
              lifecycleRules.stream()
                  .map(ApiaryConversions::encode)
                  .collect(ImmutableList.toImmutableList()));
        }

        if (!rules.isEmpty()) {
          lifecycle.setRule(ImmutableList.copyOf(rules));
        }
      }

      bucketPb.setLifecycle(lifecycle);
    }

    if (bucketInfo.getLabels() != null) {
      bucketPb.setLabels(bucketInfo.getLabels());
    }
    if (bucketInfo.getDefaultKmsKeyName() != null) {
      bucketPb.setEncryption(
          new Encryption().setDefaultKmsKeyName(bucketInfo.getDefaultKmsKeyName()));
    }
    if (bucketInfo.getDefaultEventBasedHold() != null) {
      bucketPb.setDefaultEventBasedHold(bucketInfo.getDefaultEventBasedHold());
    }
    if (bucketInfo.getRetentionPeriod() != null) {
      if (Data.isNull(bucketInfo.getRetentionPeriod())) {
        bucketPb.setRetentionPolicy(Data.nullOf(Bucket.RetentionPolicy.class));
      } else {
        Bucket.RetentionPolicy retentionPolicy = new Bucket.RetentionPolicy();
        retentionPolicy.setRetentionPeriod(bucketInfo.getRetentionPeriod());
        if (bucketInfo.getRetentionEffectiveTime() != null) {
          retentionPolicy.setEffectiveTime(new DateTime(bucketInfo.getRetentionEffectiveTime()));
        }
        if (bucketInfo.retentionPolicyIsLocked() != null) {
          retentionPolicy.setIsLocked(bucketInfo.retentionPolicyIsLocked());
        }
        bucketPb.setRetentionPolicy(retentionPolicy);
      }
    }
    if (bucketInfo.getIamConfiguration() != null) {
      bucketPb.setIamConfiguration(encode(bucketInfo.getIamConfiguration()));
    }
    if (bucketInfo.getLogging() != null) {
      bucketPb.setLogging(encode(bucketInfo.getLogging()));
    }
    return bucketPb;
  }

  static BucketInfo decode(Bucket bucketPb) {
    BucketInfo.Builder builder = new BuilderImpl(bucketPb.getName());
    if (bucketPb.getId() != null) {
      builder.setGeneratedId(bucketPb.getId());
    }

    if (bucketPb.getEtag() != null) {
      builder.setEtag(bucketPb.getEtag());
    }
    if (bucketPb.getMetageneration() != null) {
      builder.setMetageneration(bucketPb.getMetageneration());
    }
    if (bucketPb.getSelfLink() != null) {
      builder.setSelfLink(bucketPb.getSelfLink());
    }
    if (bucketPb.getTimeCreated() != null) {
      builder.setCreateTime(bucketPb.getTimeCreated().getValue());
    }
    if (bucketPb.getUpdated() != null) {
      builder.setUpdateTime(bucketPb.getUpdated().getValue());
    }
    if (bucketPb.getLocation() != null) {
      builder.setLocation(bucketPb.getLocation());
    }
    if (bucketPb.getRpo() != null) {
      builder.setRpo(Rpo.valueOf(bucketPb.getRpo()));
    }
    if (bucketPb.getStorageClass() != null) {
      builder.setStorageClass(StorageClass.valueOf(bucketPb.getStorageClass()));
    }
    if (bucketPb.getCors() != null) {
      builder.setCors(
          bucketPb.getCors().stream()
              .map((Function<Bucket.Cors, Cors>) ApiaryConversions::decode)
              .collect(ImmutableList.toImmutableList()));
    }
    if (bucketPb.getAcl() != null) {
      builder.setAcl(
          bucketPb.getAcl().stream()
              .map(ApiaryConversions::decode)
              .collect(ImmutableList.toImmutableList()));
    }
    if (bucketPb.getDefaultObjectAcl() != null) {
      builder.setDefaultAcl(
          bucketPb.getDefaultObjectAcl().stream()
              .map(ApiaryConversions::decode)
              .collect(ImmutableList.toImmutableList()));
    }
    if (bucketPb.getOwner() != null) {
      builder.setOwner(decode(bucketPb.getOwner().getEntity()));
    }
    if (bucketPb.getVersioning() != null) {
      builder.setVersioningEnabled(bucketPb.getVersioning().getEnabled());
    }
    Website website = bucketPb.getWebsite();
    if (website != null) {
      builder.setIndexPage(website.getMainPageSuffix());
      builder.setNotFoundPage(website.getNotFoundPage());
    }
    if (bucketPb.getLifecycle() != null && bucketPb.getLifecycle().getRule() != null) {
      builder.setLifecycleRules(
          bucketPb.getLifecycle().getRule().stream()
              .map(ApiaryConversions::decode)
              .collect(ImmutableList.toImmutableList()));
      builder.setDeleteRules(
          bucketPb.getLifecycle().getRule().stream()
              .map(ApiaryConversions::decodeDeleteRule)
              .collect(ImmutableList.toImmutableList()));
    }
    if (bucketPb.getLabels() != null) {
      builder.setLabels(bucketPb.getLabels());
    }
    Bucket.Billing billing = bucketPb.getBilling();
    if (billing != null) {
      builder.setRequesterPays(billing.getRequesterPays());
    }
    Encryption encryption = bucketPb.getEncryption();
    if (encryption != null
        && encryption.getDefaultKmsKeyName() != null
        && !encryption.getDefaultKmsKeyName().isEmpty()) {
      builder.setDefaultKmsKeyName(encryption.getDefaultKmsKeyName());
    }
    if (bucketPb.getDefaultEventBasedHold() != null) {
      builder.setDefaultEventBasedHold(bucketPb.getDefaultEventBasedHold());
    }
    Bucket.RetentionPolicy retentionPolicy = bucketPb.getRetentionPolicy();
    if (retentionPolicy != null) {
      if (retentionPolicy.getEffectiveTime() != null) {
        builder.setRetentionEffectiveTime(retentionPolicy.getEffectiveTime().getValue());
      }
      if (retentionPolicy.getIsLocked() != null) {
        builder.setRetentionPolicyIsLocked(retentionPolicy.getIsLocked());
      }
      if (retentionPolicy.getRetentionPeriod() != null) {
        builder.setRetentionPeriod(retentionPolicy.getRetentionPeriod());
      }
    }
    Bucket.IamConfiguration iamConfiguration = bucketPb.getIamConfiguration();

    if (bucketPb.getLocationType() != null) {
      builder.setLocationType(bucketPb.getLocationType());
    }

    if (iamConfiguration != null) {
      builder.setIamConfiguration(fromPb(iamConfiguration));
    }
    Bucket.Logging logging = bucketPb.getLogging();
    if (logging != null) {
      builder.setLogging(decode(logging));
    }
    return builder.build();
  }

  static Rule encode(DeleteRule deleteRule) {
    if (deleteRule instanceof RawDeleteRule) {
      RawDeleteRule rule = (RawDeleteRule) deleteRule;
      return rule.getRule();
    }
    Rule rule = new Rule();
    rule.setAction(new Rule.Action().setType(DeleteRule.SUPPORTED_ACTION));
    Rule.Condition condition = new Rule.Condition();
    deleteRule.populateCondition(condition);
    rule.setCondition(condition);
    return rule;
  }

  @SuppressWarnings("deprecation")
  static DeleteRule decodeDeleteRule(Rule rule) { // TODO: Name/type cleanup
    if (rule.getAction() != null
        && DeleteRule.SUPPORTED_ACTION.endsWith(rule.getAction().getType())) {
      Rule.Condition condition = rule.getCondition();
      Integer age = condition.getAge();
      if (age != null) {
        return new AgeDeleteRule(age);
      }
      DateTime dateTime = condition.getCreatedBefore();
      if (dateTime != null) {
        return new CreatedBeforeDeleteRule(dateTime.getValue());
      }
      Integer numNewerVersions = condition.getNumNewerVersions();
      if (numNewerVersions != null) {
        return new NumNewerVersionsDeleteRule(numNewerVersions);
      }
      Boolean isLive = condition.getIsLive();
      if (isLive != null) {
        return new IsLiveDeleteRule(isLive);
      }
    }
    return new RawDeleteRule(rule);
  }

  static Bucket.IamConfiguration encode(IamConfiguration in) {
    Bucket.IamConfiguration iamConfiguration = new Bucket.IamConfiguration();

    Bucket.IamConfiguration.UniformBucketLevelAccess uniformBucketLevelAccess =
        new Bucket.IamConfiguration.UniformBucketLevelAccess();
    uniformBucketLevelAccess.setEnabled(in.isUniformBucketLevelAccessEnabled());
    uniformBucketLevelAccess.setLockedTime(
        in.getUniformBucketLevelAccessLockedTime() == null
            ? null
            : new DateTime(in.getUniformBucketLevelAccessLockedTime()));

    iamConfiguration.setUniformBucketLevelAccess(uniformBucketLevelAccess);
    iamConfiguration.setPublicAccessPrevention(
        in.getPublicAccessPrevention() == null ? null : in.getPublicAccessPrevention().getValue());

    return iamConfiguration;
  }

  static IamConfiguration fromPb(Bucket.IamConfiguration iamConfiguration) {
    Bucket.IamConfiguration.UniformBucketLevelAccess uniformBucketLevelAccess =
        iamConfiguration.getUniformBucketLevelAccess();
    DateTime lockedTime = uniformBucketLevelAccess.getLockedTime();
    String publicAccessPrevention = iamConfiguration.getPublicAccessPrevention();

    PublicAccessPrevention publicAccessPreventionValue = null;
    if (publicAccessPrevention != null) {
      publicAccessPreventionValue = PublicAccessPrevention.parse(publicAccessPrevention);
    }

    return IamConfiguration.newBuilder()
        .setIsUniformBucketLevelAccessEnabled(uniformBucketLevelAccess.getEnabled())
        .setUniformBucketLevelAccessLockedTime(lockedTime == null ? null : lockedTime.getValue())
        .setPublicAccessPrevention(publicAccessPreventionValue)
        .build();
  }

  static Rule encode(LifecycleRule lifecycleRule) {
    Rule rule = new Rule();

    Rule.Action action =
        new Rule.Action().setType(lifecycleRule.getLifecycleAction().getActionType());
    if (lifecycleRule
        .getLifecycleAction()
        .getActionType()
        .equals(SetStorageClassLifecycleAction.TYPE)) {
      action.setStorageClass(
          ((SetStorageClassLifecycleAction) lifecycleRule.getLifecycleAction())
              .getStorageClass()
              .toString());
    }

    rule.setAction(action);

    Rule.Condition condition =
        new Rule.Condition()
            .setAge(lifecycleRule.getLifecycleCondition().getAge())
            .setCreatedBefore(
                lifecycleRule.getLifecycleCondition().getCreatedBefore() == null
                    ? null
                    : new DateTime(
                        true,
                        lifecycleRule.getLifecycleCondition().getCreatedBefore().getValue(),
                        0))
            .setIsLive(lifecycleRule.getLifecycleCondition().getIsLive())
            .setNumNewerVersions(lifecycleRule.getLifecycleCondition().getNumberOfNewerVersions())
            .setMatchesStorageClass(
                lifecycleRule.getLifecycleCondition().getMatchesStorageClass() == null
                    ? null
                    : lifecycleRule.getLifecycleCondition().getMatchesStorageClass().stream()
                        .map(Object::toString)
                        .collect(ImmutableList.toImmutableList()))
            .setDaysSinceNoncurrentTime(
                lifecycleRule.getLifecycleCondition().getDaysSinceNoncurrentTime())
            .setNoncurrentTimeBefore(
                lifecycleRule.getLifecycleCondition().getNoncurrentTimeBefore() == null
                    ? null
                    : new DateTime(
                        true,
                        lifecycleRule.getLifecycleCondition().getNoncurrentTimeBefore().getValue(),
                        0))
            .setCustomTimeBefore(
                lifecycleRule.getLifecycleCondition().getCustomTimeBefore() == null
                    ? null
                    : new DateTime(
                        true,
                        lifecycleRule.getLifecycleCondition().getCustomTimeBefore().getValue(),
                        0))
            .setDaysSinceCustomTime(lifecycleRule.getLifecycleCondition().getDaysSinceCustomTime());

    rule.setCondition(condition);

    return rule;
  }

  static LifecycleRule decode(Rule rule) {
    LifecycleAction lifecycleAction;

    Rule.Action action = rule.getAction();

    switch (action.getType()) {
      case DeleteLifecycleAction.TYPE:
        lifecycleAction = LifecycleAction.newDeleteAction();
        break;
      case SetStorageClassLifecycleAction.TYPE:
        lifecycleAction =
            LifecycleAction.newSetStorageClassAction(
                StorageClass.valueOf(action.getStorageClass()));
        break;
      default:
        BucketInfo.log.warning(
            "The lifecycle action "
                + action.getType()
                + " is not supported by this version of the library. "
                + "Attempting to update with this rule may cause errors. Please "
                + "update to the latest version of google-cloud-storage.");
        lifecycleAction = LifecycleAction.newLifecycleAction("Unknown action");
    }

    Rule.Condition condition = rule.getCondition();

    LifecycleCondition.Builder conditionBuilder =
        LifecycleCondition.newBuilder()
            .setAge(condition.getAge())
            .setCreatedBefore(condition.getCreatedBefore())
            .setIsLive(condition.getIsLive())
            .setNumberOfNewerVersions(condition.getNumNewerVersions())
            .setMatchesStorageClass(
                condition.getMatchesStorageClass() == null
                    ? null
                    : condition.getMatchesStorageClass().stream()
                        .map(StorageClass::valueOf)
                        .collect(ImmutableList.toImmutableList()))
            .setDaysSinceNoncurrentTime(condition.getDaysSinceNoncurrentTime())
            .setNoncurrentTimeBefore(condition.getNoncurrentTimeBefore())
            .setCustomTimeBefore(condition.getCustomTimeBefore())
            .setDaysSinceCustomTime(condition.getDaysSinceCustomTime());

    return new LifecycleRule(lifecycleAction, conditionBuilder.build());
  }

  static Bucket.Logging encode(Logging in) {
    Bucket.Logging logging;
    if (in.getLogBucket() != null || in.getLogObjectPrefix() != null) {
      logging = new Bucket.Logging();
      logging.setLogBucket(in.getLogBucket());
      logging.setLogObjectPrefix(in.getLogObjectPrefix());
    } else {
      logging = Data.nullOf(Bucket.Logging.class);
    }
    return logging;
  }

  static Logging decode(Bucket.Logging logging) {
    return Logging.newBuilder()
        .setLogBucket(logging.getLogBucket())
        .setLogObjectPrefix(logging.getLogObjectPrefix())
        .build();
  }

  static Bucket.Cors encode(Cors cors) {
    Bucket.Cors pb = new Bucket.Cors();
    pb.setMaxAgeSeconds(cors.getMaxAgeSeconds());
    pb.setResponseHeader(cors.getResponseHeaders());
    if (cors.getMethods() != null) {
      pb.setMethod(
          newArrayList(
              cors.getMethods().stream()
                  .map(Object::toString)
                  .collect(ImmutableList.toImmutableList())));
    }
    if (cors.getOrigins() != null) {
      pb.setOrigin(
          newArrayList(
              cors.getOrigins().stream()
                  .map(Object::toString)
                  .collect(ImmutableList.toImmutableList())));
    }
    return pb;
  }

  static Cors decode(Bucket.Cors cors) {
    Cors.Builder builder = Cors.newBuilder().setMaxAgeSeconds(cors.getMaxAgeSeconds());
    if (cors.getMethod() != null) {
      builder.setMethods(
          cors.getMethod().stream()
              .map(String::toUpperCase)
              .map(HttpMethod::valueOf)
              .collect(ImmutableList.toImmutableList()));
    }
    if (cors.getOrigin() != null) {
      builder.setOrigins(
          cors.getOrigin().stream().map(Origin::of).collect(ImmutableList.toImmutableList()));
    }
    builder.setResponseHeaders(cors.getResponseHeader());
    return builder.build();
  }

  static com.google.api.services.storage.model.ServiceAccount encode(
      ServiceAccount serviceAccount) {
    com.google.api.services.storage.model.ServiceAccount serviceAccountPb =
        new com.google.api.services.storage.model.ServiceAccount();
    serviceAccountPb.setEmailAddress(serviceAccount.getEmail());
    return serviceAccountPb;
  }

  static ServiceAccount decode(com.google.api.services.storage.model.ServiceAccount accountPb) {
    return ServiceAccount.of(accountPb.getEmailAddress());
  }

  static com.google.api.services.storage.model.HmacKey encode(HmacKey in) {
    com.google.api.services.storage.model.HmacKey hmacKey =
        new com.google.api.services.storage.model.HmacKey();
    hmacKey.setSecret(in.getSecretKey());

    if (in.getMetadata() != null) {
      hmacKey.setMetadata(encode(in.getMetadata()));
    }

    return hmacKey;
  }

  static HmacKey decode(com.google.api.services.storage.model.HmacKey hmacKey) {
    return HmacKey.newBuilder(hmacKey.getSecret())
        .setMetadata(decode(hmacKey.getMetadata()))
        .build();
  }

  public static com.google.api.services.storage.model.HmacKeyMetadata encode(
      HmacKeyMetadata hmacKeyMetadata) {
    com.google.api.services.storage.model.HmacKeyMetadata metadata =
        new com.google.api.services.storage.model.HmacKeyMetadata();
    metadata.setAccessId(hmacKeyMetadata.getAccessId());
    metadata.setEtag(hmacKeyMetadata.getEtag());
    metadata.setId(hmacKeyMetadata.getId());
    metadata.setProjectId(hmacKeyMetadata.getProjectId());
    metadata.setServiceAccountEmail(
        hmacKeyMetadata.getServiceAccount() == null
            ? null
            : hmacKeyMetadata.getServiceAccount().getEmail());
    metadata.setState(
        hmacKeyMetadata.getState() == null ? null : hmacKeyMetadata.getState().toString());
    metadata.setTimeCreated(
        hmacKeyMetadata.getCreateTime() == null
            ? null
            : new DateTime(hmacKeyMetadata.getCreateTime()));
    metadata.setUpdated(
        hmacKeyMetadata.getUpdateTime() == null
            ? null
            : new DateTime(hmacKeyMetadata.getUpdateTime()));

    return metadata;
  }

  static HmacKeyMetadata decode(com.google.api.services.storage.model.HmacKeyMetadata metadata) {
    return HmacKeyMetadata.newBuilder(ServiceAccount.of(metadata.getServiceAccountEmail()))
        .setAccessId(metadata.getAccessId())
        .setCreateTime(metadata.getTimeCreated().getValue())
        .setEtag(metadata.getEtag())
        .setId(metadata.getId())
        .setProjectId(metadata.getProjectId())
        .setState(HmacKeyState.valueOf(metadata.getState()))
        .setUpdateTime(metadata.getUpdated().getValue())
        .build();
  }

  static String encode(Entity e) {
    if (e instanceof RawEntity) {
      return e.getValue();
    } else if (e instanceof User) {
      switch (e.getValue()) {
        case User.ALL_AUTHENTICATED_USERS:
          return User.ALL_AUTHENTICATED_USERS;
        case User.ALL_USERS:
          return User.ALL_USERS;
        default:
          break;
      }
    }

    // intentionally not an else so that if the default is hit above it will fall through to here
    return e.getType().name().toLowerCase() + "-" + e.getValue();
  }

  static Entity decode(String entityString) {
    if (entityString.startsWith("user-")) {
      return new User(entityString.substring(5));
    }
    if (entityString.equals(User.ALL_USERS)) {
      return User.ofAllUsers();
    }
    if (entityString.equals(User.ALL_AUTHENTICATED_USERS)) {
      return User.ofAllAuthenticatedUsers();
    }
    if (entityString.startsWith("group-")) {
      return new Group(entityString.substring(6));
    }
    if (entityString.startsWith("domain-")) {
      return new Domain(entityString.substring(7));
    }
    if (entityString.startsWith("project-")) {
      int idx = entityString.indexOf('-', 8);
      String team = entityString.substring(8, idx);
      String projectId = entityString.substring(idx + 1);
      return new Project(Project.ProjectRole.valueOf(team.toUpperCase()), projectId);
    }
    return new RawEntity(entityString);
  }

  static Acl decode(ObjectAccessControl objectAccessControl) {
    Role role = Role.valueOf(objectAccessControl.getRole());
    Entity entity = decode(objectAccessControl.getEntity());
    return Acl.newBuilder(entity, role)
        .setEtag(objectAccessControl.getEtag())
        .setId(objectAccessControl.getId())
        .build();
  }

  static Acl decode(BucketAccessControl bucketAccessControl) {
    Role role = Role.valueOf(bucketAccessControl.getRole());
    Entity entity = decode(bucketAccessControl.getEntity());
    return Acl.newBuilder(entity, role)
        .setEtag(bucketAccessControl.getEtag())
        .setId(bucketAccessControl.getId())
        .build();
  }

  static BucketAccessControl encodeBucket(Acl acl) {
    BucketAccessControl bucketPb = new BucketAccessControl();
    bucketPb.setEntity(acl.getEntity().toString());
    bucketPb.setRole(acl.getRole().toString());
    bucketPb.setId(acl.getId());
    bucketPb.setEtag(acl.getEtag());
    return bucketPb;
  }

  static ObjectAccessControl encodeObject(Acl acl) {
    ObjectAccessControl objectPb = new ObjectAccessControl();
    objectPb.setEntity(encode(acl.getEntity()));
    objectPb.setRole(acl.getRole().name());
    objectPb.setId(acl.getId());
    objectPb.setEtag(acl.getEtag());
    return objectPb;
  }
}
