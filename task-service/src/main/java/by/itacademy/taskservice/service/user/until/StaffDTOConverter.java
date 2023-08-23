package by.itacademy.taskservice.service.user.until;

import by.itacademy.taskservice.core.dto.other.StaffDTO;
import by.itacademy.taskservice.repository.entity.StaffEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class StaffDTOConverter implements Converter<StaffEntity, StaffDTO> {
    @Override
    public StaffDTO convert(StaffEntity source) {
        return new StaffDTO(
                source.getUuid()
        );
    }
}
