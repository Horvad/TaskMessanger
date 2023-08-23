package by.itAcademy.userservice.config;



import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.format.DateTimeFormatter;
import java.util.Set;
@SpringBootApplication
@Configuration
//@AutoConfigureBefore({JacksonAutoConfiguration.class})
public class UserConfig {
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

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
