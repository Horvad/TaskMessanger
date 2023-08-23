package by.itAcademy.auditservice.service;

import by.itAcademy.auditservice.core.dto.AuditSendDTO;
import by.itAcademy.auditservice.core.dto.AuditViewDTO;
import by.itAcademy.auditservice.core.dto.PageDTOView;
import by.itAcademy.auditservice.core.exception.NotFoundException;
import by.itAcademy.auditservice.repositories.api.IAuditRepository;
import by.itAcademy.auditservice.repositories.api.IUserRepository;
import by.itAcademy.auditservice.repositories.entity.AuditEntity;
import by.itAcademy.auditservice.service.api.IAuditService;
import by.itAcademy.auditservice.service.convertor.AuditDTOConverter;
import by.itAcademy.auditservice.service.convertor.AuditEntityConverter;
import by.itAcademy.auditservice.service.convertor.AuditSendDTOConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuditService implements IAuditService {
    private final IAuditRepository auditRepository;
    private final IUserRepository userRepository;
    private final AuditDTOConverter auditDTOConverter;
    private final AuditEntityConverter auditEntityConverter;
    private final AuditSendDTOConverter sendDTOConverter;

    public AuditService(IAuditRepository auditRepository, IUserRepository userRepository, AuditDTOConverter auditDTOConverter, AuditEntityConverter auditEntityConverter, AuditSendDTOConverter sendDTOConverter) {
        this.auditRepository = auditRepository;
        this.userRepository = userRepository;
        this.auditDTOConverter = auditDTOConverter;
        this.auditEntityConverter = auditEntityConverter;
        this.sendDTOConverter = sendDTOConverter;
    }

    @Transactional(readOnly = true)
    @Override
    public PageDTOView getPageAudit(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<AuditEntity> auditEntities = auditRepository.findAll(pageable);
        List<AuditViewDTO> context = auditEntities.getContent().stream()
                .map(s->auditDTOConverter.convert(s)).collect(Collectors.toList());
        PageDTOView pageDTOView = new PageDTOView(
                auditEntities.getNumber(),
                auditEntities.getSize(),
                auditEntities.getTotalPages(),
                auditEntities.getTotalElements(),
                auditEntities.isFirst(),
                auditEntities.getNumberOfElements(),
                auditEntities.isLast(),
                context
        );
        return pageDTOView;
    }

    @Transactional(readOnly = true)
    @Override
    public AuditViewDTO getAuditDTO(UUID uuid) {
        AuditViewDTO auditViewDTO = auditDTOConverter.convert(auditRepository.findById(uuid).orElseThrow
                (()->new NotFoundException("Данного пользователя не существует")
                ));
        return auditViewDTO;
    }

    @Transactional
    @Override
    public void save(AuditSendDTO auditSendDTO) {
        AuditEntity auditEntity = auditEntityConverter.convert(auditSendDTO);
        userRepository.save(auditEntity.getUserEntity());
        auditRepository.save(auditEntity);
    }

    @Override
    public PageDTOView getByUuidBetweenData(int size, int page, UUID uuid, LocalDate dateFrom, LocalDate dateTo) {
        Pageable pageable = PageRequest.of(page,size);
        Page<AuditEntity> auditEntities = auditRepository
                .findByUuidAndDtCreateBetween(pageable,uuid,dateFrom.atStartOfDay(),dateTo.atStartOfDay());
        List<AuditSendDTO> context = auditEntities.getContent().stream()
                .map(s->sendDTOConverter.convert(s)).collect(Collectors.toList());
        PageDTOView pageDTOView = new PageDTOView(
                auditEntities.getNumber(),
                auditEntities.getSize(),
                auditEntities.getTotalPages(),
                auditEntities.getTotalElements(),
                auditEntities.isFirst(),
                auditEntities.getNumberOfElements(),
                auditEntities.isLast(),
                context
        );
        return pageDTOView;
    }
}
