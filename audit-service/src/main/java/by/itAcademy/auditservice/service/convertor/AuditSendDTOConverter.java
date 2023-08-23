package by.itAcademy.auditservice.service.convertor;

import by.itAcademy.auditservice.core.dto.AuditSendDTO;
import by.itAcademy.auditservice.repositories.entity.AuditEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class AuditSendDTOConverter implements Converter<AuditEntity, AuditSendDTO> {

    @Override
    public AuditSendDTO convert(AuditEntity source) {
        return new AuditSendDTO(
                source.getUuid().toString(),
                source.getIdString(),
                source.getRole(),
                source.getText(),
                source.getUserEntity().getUuidUser(),
                source.getUserEntity().getFio(),
                source.getUserEntity().getMail(),
                source.getUserEntity().getRole(),
                source.getUserEntity().getStatus()
        );
    }
}
