package com.example.reportservice;

import com.example.reportservice.config.properties.FeignProperty;
import com.example.reportservice.config.properties.JWTProperty;
import com.example.reportservice.config.properties.MinioProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories
@EnableConfigurationProperties({MinioProperties.class, JWTProperty.class, FeignProperty.class})
public class ReportServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportServiceApplication.class, args);
    }

}
