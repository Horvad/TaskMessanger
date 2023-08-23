package by.itAcademy.auditservice.service.convertor;

import by.itAcademy.auditservice.core.dto.AuditViewDTO;
import by.itAcademy.auditservice.core.dto.UserAuditDTO;
import by.itAcademy.auditservice.repositories.entity.AuditEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class AuditDTOConverter implements Converter<AuditEntity, AuditViewDTO> {
    @Override
    public AuditViewDTO convert(AuditEntity source) {
        return new AuditViewDTO(
                source.getUuid().toString(),
                source.getRole(),
                new UserAuditDTO(
                        source.getUserEntity().getUuidUser(),
                        source.getUserEntity().getFio(),
                        source.getUserEntity().getMail(),
                        source.getUserEntity().getRole(),
                        source.getUserEntity().getStatus()
                ),
                source.getText(),
                source.getIdString()
        );
    }
}
