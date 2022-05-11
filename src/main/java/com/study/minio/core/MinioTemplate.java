package com.study.minio.core;


import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @Author cxk
 * @Date 2022/5/7 22:04
 */
public interface MinioTemplate {

    /**
     * 桶是否存在
     * @param minioFile minioFile配置
     * @return 桶名是否存在
     */
    boolean bucketExists(MinioFile minioFile) throws Exception;

    /**
     * 新建一个桶
     * @param minioFile minioFile配置
     */
    void createBucket(MinioFile minioFile) throws Exception;

    /**
     * 创建一个所有人均可访问的桶
     * @param minioFile minioFile配置
     */
    void createBucketAllCanAccess(MinioFile minioFile) throws Exception;

    /**
     * 获取对象文件的访问地址（默认过期最大过期时间）
     * @param minioFile minioFile配置
     * @return
     */
    String getObjectUrl(MinioFile minioFile) throws Exception;

    /**
     * 获取对象的临时访问链接
     * @param minioFile minioFile配置
     * @param duration   访问有效期
     * @param timeUnit   单元时间
     * @return 临时访问链接
     */
    String getObjectUrl(MinioFile minioFile, int duration, TimeUnit timeUnit) throws Exception;

    /**
     * 上传文件
     * @param minioFile minioFile配置
     * @param filePath   待上传文件路径
     * @return 文件访问路径
     */
    String uploadFile(MinioFile minioFile,String filePath) throws Exception;

    /**
     * 通过流上传文件
     * @param minioFile minioFile配置
     * @return              文件访问路径
     */
    String uploadFile(MinioFile minioFile) throws Exception;

    /**
     * 上传一个所有人可访问的文件（桶策略为custom）
     * @param minioFile minioFile配置
     * @return
     */
    String upLoadFileAllAccess(MinioFile minioFile) throws Exception;

    /**
     * 删除一个文件
     * @param minioFile minioFile配置
     */
    void deleteFile(MinioFile minioFile) throws Exception;

    /**
     * 根据对象名批量删除文件
     * @param minioFile minioFile配置
     * @param objectNameList 待删除对象列表
     */
    void deleteFile(MinioFile minioFile, List<String> objectNameList);
}
