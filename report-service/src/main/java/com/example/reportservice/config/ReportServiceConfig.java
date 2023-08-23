package com.example.reportservice.config;

import com.example.reportservice.config.properties.MinioProperties;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.format.DateTimeFormatter;

@Configuration
@AutoConfigureBefore({JacksonAutoConfiguration.class})
public class ReportServiceConfig {
    private final MinioProperties minioProperties;

    public ReportServiceConfig(MinioProperties minioProperties) {
        this.minioProperties = minioProperties;
    }
    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint(minioProperties.getUrl())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperBuilderCustomizer() {
        return new Jackson2ObjectMapperBuilderCustomizer() {

            @Override
            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
                final String dateFormat = "yyyy-MM-dd";
                final String timeFormat = "hh:mm:ss a";
                final String dateTimeFormat = "yyyy-MM-dd hh:mm:ss a";
                jacksonObjectMapperBuilder
                        .serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)))
                        .deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(dateFormat)))
                        .serializers(new LocalTimeSerializer(DateTimeFormatter.ofPattern(timeFormat)))
                        .deserializers(new LocalTimeDeserializer(DateTimeFormatter.ofPattern(timeFormat)))
                        .serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)))
                        .deserializers(
                                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
            }
        };
    }

}
