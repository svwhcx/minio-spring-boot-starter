package com.study.minio.core;

import com.study.minio.conf.MinioProperties;
import io.minio.PutObjectArgs;
import org.springframework.util.Assert;

import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

/**
 * @description 处理输入流格式的文件
 * @Author cxk
 * @Date 2022/5/11 12:43
 */
public class InputStreamMinioTemplate extends BaseMinioTemplate{

    @Override
    public String uploadFile(MinioFile minioFile) throws Exception{
        Assert.notNull(minioFile,"MinioFile is null");
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(minioFile.getBucketName())
                .object(minioFile.getObjectName()).
                stream(minioFile.getInputStream(), -1, 10485760)
                .build());
        return getObjectUrl(minioFile,7, TimeUnit.DAYS);
    }

    @Override
    public String upLoadFileAllAccess(MinioFile minioFile) throws Exception {
        Assert.notNull(minioFile,"MinioFile is null");
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(minioFile.getBucketName())
                .object(minioFile.getObjectName()).
                stream(minioFile.getInputStream(), -1, 10485760)
                .contentType(minioFile.getContentType())
                .build());
        // 返回默认的访问路径
        StringJoiner stringJoiner = new StringJoiner("/")
                .add(this.hostUrl)
                .add(minioFile.getBucketName())
                .add(minioFile.getObjectName());
        return stringJoiner.toString();
    }

    public InputStreamMinioTemplate() {
        super();
    }

    public InputStreamMinioTemplate(String hostUrl, String access, String secret) {
        super(hostUrl, access, secret);
    }

    public InputStreamMinioTemplate(MinioProperties minioProperties){
        super(minioProperties);
    }
}
