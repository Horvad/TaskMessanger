package by.itAcademy.auditservice.repositories.api;

import by.itAcademy.auditservice.repositories.entity.AuditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IAuditRepository extends CrudRepository<AuditEntity, UUID> {
    Page<AuditEntity> findAll(Pageable pageable);
    Page<AuditEntity> findByUuidAndDtCreateBetween(Pageable pageable,
                                                   UUID uuid,
                                                   LocalDateTime dateTimeFrom,
                                                   LocalDateTime localDateTimeTo);
}
