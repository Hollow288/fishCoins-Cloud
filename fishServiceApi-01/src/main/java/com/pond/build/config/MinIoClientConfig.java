package com.pond.build.config;

import io.minio.MinioClient;
import jakarta.servlet.MultipartConfigElement;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;

@Data
@Component
public class MinIoClientConfig {

    @Value(value = "${minio.endpoint:}")
    private String endpoint;

    @Value(value = "${minio.accessKey:}")
    private String accessKey;

    @Value(value = "${minio.secretKey:}")
    private String secretKey;

    /**
     * 注入minio 客户端
     *
     * @return
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }

    /*
     * 控制上传文件的大小
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个数据大小
        factory.setMaxFileSize(DataSize.parse("1024MB")); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize(DataSize.parse("10240MB"));
        return factory.createMultipartConfig();
    }

}