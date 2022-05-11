package com.study.minio.core;

import com.study.minio.conf.MinioProperties;
import com.study.minio.util.Base64StringUtils;
import io.minio.PutObjectArgs;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.StringJoiner;

/**
 * @description 处理base64字符串格式的文件 (初步完成）
 * @Author cxk
 * @Date 2022/5/11 12:42
 */
public class Base64MinioTemplate extends BaseMinioTemplate{

    public Base64MinioTemplate() {}

    public Base64MinioTemplate(MinioProperties minioProperties) {
        super(minioProperties);
    }

    public Base64MinioTemplate(String hostUrl, String access, String secret) {
        super(hostUrl, access, secret);
    }

    @Override
    public String uploadFile(MinioFile minioFile) throws Exception {
        // 解析base64
        String base64String = minioFile.getBase64String();
        byte[] bytes = Base64StringUtils.getBytes(base64String);
        String contentType = Base64StringUtils.getContentType(base64String);
        InputStream inputStream = new ByteArrayInputStream(bytes);
        this.minioClient.putObject(PutObjectArgs.builder()
                .bucket(minioFile.getBucketName())
                .object(minioFile.getObjectName())
                .contentType(contentType)
                .stream(inputStream,inputStream.available(),-1).build());
        return getObjectUrl(minioFile);
    }

    @Override
    public String upLoadFileAllAccess(MinioFile minioFile) throws Exception {
        // 解析base64
        String base64String = minioFile.getBase64String();
        byte[] bytes = Base64StringUtils.getBytes(base64String);
        String contentType = Base64StringUtils.getContentType(base64String);
        InputStream inputStream = new ByteArrayInputStream(bytes);
        this.minioClient.putObject(PutObjectArgs.builder()
                .bucket(minioFile.getBucketName())
                .object(minioFile.getObjectName())
                .contentType(contentType)
                .stream(inputStream,inputStream.available(),-1).build());
        // 返回访问路径
        StringJoiner stringJoiner = new StringJoiner("/")
                .add(this.hostUrl)
                .add(minioFile.getBucketName())
                .add(minioFile.getObjectName());
        return stringJoiner.toString();
    }

}
