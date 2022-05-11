package com.study.minio.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 *   TODO 没有完成前端传递大文件实现方式，以及分片上传
 * @description
 * @Author cxk
 * @Date 2022/5/7 21:53
 */
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    /**
     * minio服务器地址
     */
    private String hostUrl;



    /**
     * 访问用户名
     */
    private String access;

    /**
     * 访问用户名对应的秘钥
     */
    private String secret;

    /**
     * 连接超时时间 （毫秒（毫秒,默认：6000ms）
     */
    private long connectionTimeout = 6000;
    /**
     * 写入超时时间 （毫秒,默认：0,无超时限制）
     */
    private long writeTimeout = 0;
    /**
     * 读取文件超时时间 （毫秒,默认：0,无超时限制）
     */
    private long readTimeout = 0;



    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public long getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(long writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public long getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(long readTimeout) {
        this.readTimeout = readTimeout;
    }
}
