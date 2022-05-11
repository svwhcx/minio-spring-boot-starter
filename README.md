# minio-spring-boot-starter
基于minio官网封装的starter（简易版）
## 使用方法：（1.0.0） 简易版本
### 1. 加载maven依赖
```pom
<!-- 配置代理仓库-->
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
</repositories>
<!-- 依赖-->
<dependency>
    <groupId>com.github.cx2002</groupId>
	  <artifactId>minio-spring-boot-starter</artifactId>
	  <version>1.0.0</version>
</dependency>
```
### 2.注意事项
mioin官网的内部引用了okhttp中的代码，而springboot中也存在okhttp的依赖，故在启动项目的时候会发生冲突

**解决方法**：覆盖原来spring-boot-parent中定义的okhttp的依赖的版本
```maven
<properties>
    <okhttp3.version>4.8.1</okhttp3.version>
</properties>
```
### 3.配置
```yaml
  # minio 开头的属性
  minio.access：用户名
```
### bean注入
本start自动注入了MinioTemplate，使用者可直接注入，默认注入是通过InputStream（输入流）的方式进行文件传输的方案
```java

@Autowired
    private MinioTemplate minioTemplate;

```
使用者可再次注入基于base64文件编码方式的Base64MinioTemplate
```java
@Bean("base64MinioTemplate")
public MinioTemplate minioTemplate(MinioProperties minioProperties){
    return new Base64MinioTemplate(minioProperties);
}
```
说明： 在使用时需使用者构建一个MinioFile方便对所有的方法进行统一的管理
### 示例
```java
@RestController
public class TestController {

    @Autowired
    private MinioTemplate minioTemplate;
    
    @PostMapping("/createCustomBucket")
    public String create(String bucketName) throws Exception {
        minioTemplate.createBucketAllCanAccess(MinioFile.builder()
        .bucketName(bucketName)
        .build());
        return "create is successful";
    }
}
```
该示例演示了创建一个所有人都可访问的bucket（改变bucket的策略为(custom)）用于需要一直访问的文件，如：头像

使用者可查看MinioFile中的属性，自定义配置需要的属性，例如在base64方式的文件上传中需要设置其**base64String**属性
