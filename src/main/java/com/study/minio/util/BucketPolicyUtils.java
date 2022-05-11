package com.study.minio.util;

import com.study.minio.context.BucketPolicyContext;

/**
 * @description
 * @Author cxk
 * @Date 2022/5/11 1:03
 */
public class BucketPolicyUtils {

    /**
     * 加载custom桶策略
     * @param bucketName 桶名
     * @return 桶的json配置
     */
    public static String upLoadCustomJson(String bucketName){
        String policy = BucketPolicyContext.policyJsonMap.get(BucketPolicyContext.BucketPolicyEnum.CUSTOM);
        return policy.replaceAll("BUCKET_NAME",bucketName);
    }

}
