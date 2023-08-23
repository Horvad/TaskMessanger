package by.itAcademy.userservice.service.converters;

import by.itAcademy.userservice.core.dto.UserDTO;
import by.itAcademy.userservice.repositories.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class UserDTOToEntityConverter implements Converter<UserDTO, UserEntity> {
    @Override
    public UserEntity convert(UserDTO source) {
        return new UserEntity(source.getFio(),source.getMail(),source.getPassword(),
                source.getRole(), source.getStatus());
    }
}
