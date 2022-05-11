package com.study.minio.util;

import org.springframework.util.Assert;

import sun.misc.BASE64Decoder;

import java.io.IOException;


/**
 * @description
 * @Author cxk
 * @Date 2022/5/11 13:51
 */
public class Base64StringUtils {

    /**
     * 获取字节数组
     *
     * @param base64String base64字符串
     * @return
     */
    public static byte[] getBytes(String base64String) {
        isValid(base64String);
        base64String = base64String.split(",")[1];
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] bytes;
        try {
            bytes = base64Decoder.decodeBuffer(base64String);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {
                    bytes[i] += 256;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }

    /**
     * 鉴定是否是有效的base64字符串
     *
     * @param base64String base字符串
     */
    private static void isValid(String base64String) {
        Assert.notNull(base64String, "base64 is null");
        boolean empty = base64String.isEmpty();
        if (empty) {
            throw new IllegalArgumentException("base64 is empty");
        }
    }

    /**
     * 获取base64转换文件的类型
     *
     * @param base64String base64字符串
     * @return
     */
    public static String getContentType(String base64String) {
        isValid(base64String);
        String[] strings = base64String.split(";");
        String string = strings[0];
        if (string.isEmpty()){
            throw new IllegalArgumentException("base64 is illegal");
        }
        String[] base = string.split(":");
        String contentType = base[1];
        if (contentType == null || contentType.isEmpty()){
            throw new IllegalArgumentException("base64 is illegal");
        }
        return contentType;
    }

}
