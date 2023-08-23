package by.itacademy.taskservice.service.user.until;

import by.itacademy.taskservice.core.dto.other.ImplementerDTO;
import by.itacademy.taskservice.repository.entity.ImplementerEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class ImplementerDTOConverter implements Converter<ImplementerEntity, ImplementerDTO> {
    @Override
    public ImplementerDTO convert(ImplementerEntity source) {
        return new ImplementerDTO(
                source.getUuid()
        );
    }
}
