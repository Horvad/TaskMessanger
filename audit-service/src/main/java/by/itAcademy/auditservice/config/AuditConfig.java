package by.itAcademy.auditservice.config;

import by.itAcademy.auditservice.repositories.api.IAuditRepository;
import by.itAcademy.auditservice.repositories.api.IUserRepository;
import by.itAcademy.auditservice.service.AuditService;
import by.itAcademy.auditservice.service.api.IAuditService;
import by.itAcademy.auditservice.service.convertor.AuditDTOConverter;
import by.itAcademy.auditservice.service.convertor.AuditEntityConverter;
import by.itAcademy.auditservice.service.convertor.AuditSendDTOConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditConfig {
}
