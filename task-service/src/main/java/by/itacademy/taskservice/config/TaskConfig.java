package by.itacademy.taskservice.config;

import by.itacademy.taskservice.repository.*;
import by.itacademy.taskservice.service.ProjectService;
import by.itacademy.taskservice.service.TaskService;
import by.itacademy.taskservice.service.api.IProjectService;
import by.itacademy.taskservice.service.api.ITaskService;
import by.itacademy.taskservice.service.until.ProjectDTOConverter;
import by.itacademy.taskservice.service.until.ProjectEntityConverter;
import by.itacademy.taskservice.service.until.TaskDTOConverter;
import by.itacademy.taskservice.service.until.TaskEntityConverter;
import by.itacademy.taskservice.service.user.ImplementerService;
import by.itacademy.taskservice.service.user.ManagerService;
import by.itacademy.taskservice.service.user.StaffService;
import by.itacademy.taskservice.service.user.api.IImplementerService;
import by.itacademy.taskservice.service.user.api.IManagerService;
import by.itacademy.taskservice.service.user.api.IStaffService;
import by.itacademy.taskservice.service.user.api.IUserService;
import by.itacademy.taskservice.service.user.until.*;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.format.DateTimeFormatter;

@EnableFeignClients
@Configuration
//@AutoConfigureBefore({JacksonAutoConfiguration.class})
@EnableJpaRepositories
public class TaskConfig {
//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperBuilderCustomizer() {
//        return new Jackson2ObjectMapperBuilderCustomizer() {
//
//            @Override
//            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
//                final String dateFormat = "yyyy-MM-dd";
//                final String timeFormat = "hh:mm:ss.SSS";
//                final String dateTimeFormat = "yyyy-MM-dd hh:mm:ss.SSS";
//                jacksonObjectMapperBuilder
//                        .serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)))
//                        .deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(dateFormat)))
//                        .serializers(new LocalTimeSerializer(DateTimeFormatter.ofPattern(timeFormat)))
//                        .deserializers(new LocalTimeDeserializer(DateTimeFormatter.ofPattern(timeFormat)))
//                        .serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)))
//                        .deserializers(
//                                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
//            }
//        };
//    }
}
