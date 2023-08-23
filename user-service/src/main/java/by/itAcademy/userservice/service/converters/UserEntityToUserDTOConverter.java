package by.itAcademy.userservice.service.converters;

import by.itAcademy.userservice.core.dto.UserViewDTO;
import by.itAcademy.userservice.repositories.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;

@Service
public class UserEntityToUserDTOConverter implements Converter<UserEntity, UserViewDTO> {
    @Override
    public UserViewDTO convert(UserEntity source) {
        UserViewDTO userViewDTO = new UserViewDTO(
                source.getUuid(),
                source.getFio(),
                source.getDtCreate(),
                source.getDtUpdate(),
                source.getMail(),
                source.getRole(),
                source.getStatus()
        );
        return userViewDTO;
    }
}
