package by.itacademy.taskservice.service.user.until;

import by.itacademy.taskservice.core.dto.other.ManagerDTO;
import by.itacademy.taskservice.repository.entity.ManagerEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class ManagerDTOConverter implements Converter<ManagerEntity, ManagerDTO> {
    @Override
    public ManagerDTO convert(ManagerEntity source) {
        return new ManagerDTO(
                source.getUuid()
        );
    }
}
