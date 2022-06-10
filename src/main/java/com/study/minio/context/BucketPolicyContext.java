package com.study.minio.context;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
/**
 * @description
 * @Author cxk
 * @Date 2022/5/11 0:34
 */
public class BucketPolicyContext {

    public static final HashMap<BucketPolicyEnum, String> policyJsonMap = new HashMap<>();

    static {
        // 提前加载好痛配置策略
        // 1. 加载任何用户可访问的custom策略
        String customPolicy = loadPolicy("bucket-policy/bucket-custom-policy.json");
        policyJsonMap.put(BucketPolicyEnum.CUSTOM, customPolicy);
    }

    /**
     * 加载指定json配置文件的json配置
     *
     * @param path json文件的路径
     * @return
     */
    private static String loadPolicy(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        ClassPathResource classPathResource = new ClassPathResource(path);
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(classPathResource.getInputStream()))) {
            String s;
            while ((s = bf.readLine()) != null) {
                stringBuilder.append(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }


    /**
     * 策略枚举
     */
    public enum BucketPolicyEnum {
        CUSTOM
    }

}
