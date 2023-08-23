package by.itAcademy.auditservice.service.api;

import by.itAcademy.auditservice.core.dto.AuditSendDTO;
import by.itAcademy.auditservice.core.dto.AuditViewDTO;
import by.itAcademy.auditservice.core.dto.PageDTOView;

import java.time.LocalDate;
import java.util.UUID;

public interface IAuditService {
    PageDTOView getPageAudit(int page, int size);
    AuditViewDTO getAuditDTO(UUID uuid);
    void save(AuditSendDTO auditDTO);
    PageDTOView getByUuidBetweenData(int size, int page, UUID uuid, LocalDate dateFrom, LocalDate dateTo);
}
