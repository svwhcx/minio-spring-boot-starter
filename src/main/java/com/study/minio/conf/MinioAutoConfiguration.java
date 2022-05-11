package com.study.minio.conf;

import com.study.minio.core.InputStreamMinioTemplate;
import com.study.minio.core.MinioTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @description
 * @Author cxk
 * @Date 2022/5/7 21:56
 */
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioAutoConfiguration {

    @Bean
    @ConditionalOnClass(MinioProperties.class)
    public MinioTemplate minioTemplate(MinioProperties minioProperties){
        // 默认为InputStream形式文件上传
        return new InputStreamMinioTemplate(minioProperties);
    }

}
