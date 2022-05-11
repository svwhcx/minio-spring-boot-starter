package com.study.minio.core;

import java.io.InputStream;

/**
 * @description
 * @Author cxk
 * @Date 2022/5/11 12:12
 */
public class MinioFile {

    /**
     * 桶名
     */
    private String bucketName;

    /**
     * 对象名
     */
    private String objectName;

    /**
     * 文件类型
     */
    private String contentType;

    /**
     * 用于文件流处理方式的输入流
     */
    private InputStream inputStream;

    /**
     * 用于base64编码处理方式的base64字符串
     */
    private String base64String;


    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getBase64String() {
        return base64String;
    }

    public void setBase64String(String base64String) {
        this.base64String = base64String;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static final class Builder{

        private final MinioFile minioFile;

        public Builder(){
            this.minioFile = new MinioFile();
        }

        public Builder bucketName(String bucketName){
            this.minioFile.setBucketName(bucketName);
            return this;
        }

        public Builder objectName(String objectName){
            this.minioFile.setObjectName(objectName);
            return this;
        }

        public Builder contentType(String contentType){
            this.minioFile.setContentType(contentType);
            return this;
        }

        public Builder inputStream(InputStream inputStream){
            this.minioFile.setInputStream(inputStream);
            return this;
        }

        public Builder base64String(String base64String){
            this.minioFile.setBase64String(base64String);
            return this;
        }

        public MinioFile build(){
            return this.minioFile;
        }
    }

}
