package com.study.minio.core;

import com.study.minio.conf.MinioProperties;
import com.study.minio.util.BucketPolicyUtils;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.DeleteObject;

import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @Author cxk
 * @Date 2022/5/7 22:20
 */
public abstract class BaseMinioTemplate implements MinioTemplate {

    protected String hostUrl;

    protected String access;

    protected String secret;

    protected MinioClient minioClient;


    public BaseMinioTemplate() {}

    public BaseMinioTemplate(String hostUrl, String access, String secret) {
        this.hostUrl = hostUrl;
        this.access = access;
        this.secret = secret;
        minioClient  = createMinioClient(this.hostUrl, this.access, this.secret);
    }

    public BaseMinioTemplate(MinioProperties minioProperties){
        this.hostUrl = minioProperties.getHostUrl();
        this.access = minioProperties.getAccess();
        this.secret = minioProperties.getSecret();
        minioClient = createMinioClient(this.hostUrl,this.access,this.secret);
        minioClient.setTimeout(minioProperties.getConnectionTimeout(),
                minioProperties.getWriteTimeout(),minioProperties.getReadTimeout());
    }

    protected MinioClient createMinioClient(String url,String accessKey,String secretKey){
        return MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey,secretKey)
                .build();
    }

    @Override
    public boolean bucketExists(MinioFile minioFile) throws Exception {
        Assert.notNull(minioFile,"MinioFile is null");
        return minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(minioFile.getBucketName()).build());
    }

    @Override
    public void createBucket(MinioFile minioFile) throws Exception{
        Assert.notNull(minioFile,"MinioFile is null");
        minioClient.makeBucket(MakeBucketArgs.builder()
                .bucket(minioFile.getBucketName()).build());
    }

    @Override
    public void createBucketAllCanAccess(MinioFile minioFile) throws Exception{
        // 先创建桶
        createBucket(minioFile);
        // 然后设置桶的访问策略，这里只是配置最基本的策略
        String bucketCustomPolicy = BucketPolicyUtils.upLoadCustomJson(minioFile.getBucketName());
        minioClient.setBucketPolicy(SetBucketPolicyArgs.builder()
                .bucket(minioFile.getBucketName())
                .config(bucketCustomPolicy)
                .build());
    }

    @Override
    public String getObjectUrl(MinioFile minioFile) throws Exception{
        Assert.notNull(minioFile,"MinioFile is null");
        return getObjectUrl(minioFile,7,TimeUnit.DAYS);
    }

    @Override
    public String getObjectUrl(MinioFile minioFile, int duration, TimeUnit timeUnit) throws Exception{
        Assert.notNull(minioFile,"MinioFile is null");
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(minioFile.getBucketName())
                        .object(minioFile.getObjectName())
                        .expiry(duration,timeUnit)
                        .build());
    }

    @Override
    public String uploadFile(MinioFile minioFile, String filePath) throws Exception{
        Assert.notNull(minioFile,"MinioFile is null");
        minioClient.uploadObject(UploadObjectArgs.builder()
                .bucket(minioFile.getBucketName())
                .object(minioFile.getObjectName())
                .filename(filePath)
                .build());
        return getObjectUrl(minioFile,7,TimeUnit.DAYS);
    }

    @Override
    public abstract String uploadFile(MinioFile minioFile) throws Exception;

    @Override
    public abstract String upLoadFileAllAccess(MinioFile minioFile) throws Exception;

    @Override
    public void deleteFile(MinioFile minioFile) throws Exception{
        Assert.notNull(minioFile,"MinioFile is null");
        // 使用普通的删除方式删除一个对象文件
        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(minioFile.getBucketName())
                .object(minioFile.getObjectName())
                .build());
    }

    @Override
    public void deleteFile(MinioFile minioFile, List<String> objectNameList) {
        String bucketName = minioFile.getBucketName();
        // 构造删除的对象
        List<DeleteObject> deleteObjects = new LinkedList<>();
        objectNameList.forEach(objectName -> deleteObjects.add(new DeleteObject(objectName)));
        minioClient.removeObjects(RemoveObjectsArgs.builder()
                .bucket(bucketName)
                .objects(deleteObjects)
                .build());
    }

}
