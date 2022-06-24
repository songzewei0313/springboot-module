package com.song.springbootminio.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author szw
 * @date 2022/6/2 13:30
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinIoClientConfig {

    private String endpoint;

    private String accessKey;

    private String secretKey;

    /**
     * 注入minio 客户端
     * @return
     */
    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
}
