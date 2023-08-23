package by.itacademy.taskservice.service.user.until;

import by.itacademy.taskservice.core.dto.other.ManagerDTO;
import by.itacademy.taskservice.repository.entity.ManagerEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class ManagerEntityConverter implements Converter<ManagerDTO, ManagerEntity> {

    @Override
    public ManagerEntity convert(ManagerDTO source) {
        return new ManagerEntity(
                source.getUuid()
        );
    }
}
