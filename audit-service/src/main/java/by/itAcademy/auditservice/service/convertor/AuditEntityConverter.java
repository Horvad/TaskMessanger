package by.itAcademy.auditservice.service.convertor;

import by.itAcademy.auditservice.core.dto.AuditSendDTO;
import by.itAcademy.auditservice.repositories.entity.AuditEntity;
import by.itAcademy.auditservice.repositories.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuditEntityConverter implements Converter<AuditSendDTO, AuditEntity> {
    @Override
    public AuditEntity convert(AuditSendDTO source) {
        return new AuditEntity(
                UUID.randomUUID(),
                source.getText(),
                source.getUserRole(),
                source.getUuid(),
                new UserEntity(
                        UUID.randomUUID(),
                        source.getUserMail(),
                        source.getUserFio(),
                        source.getUserRole(),
                        source.getUserStatus(),
                        source.getUserUuid()
                )
        );
    }
}
