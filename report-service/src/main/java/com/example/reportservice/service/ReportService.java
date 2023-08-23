package com.example.reportservice.service;

import com.example.reportservice.core.DTO.PageDTO;
import com.example.reportservice.core.DTO.PageDTOView;
import com.example.reportservice.core.DTO.ReportParamsDTO;
import com.example.reportservice.core.DTO.ReportViewDTO;
import com.example.reportservice.core.enums.ReportStatus;
import com.example.reportservice.core.enums.ReportType;
import com.example.reportservice.repository.api.IReportRepository;
import com.example.reportservice.repository.entity.FileReportEntity;
import com.example.reportservice.repository.entity.ReportEntity;
import com.example.reportservice.service.api.ICreateReportService;
import com.example.reportservice.service.api.IReportService;
import com.example.reportservice.service.util.convertors.DTOConvertor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReportService implements IReportService {
    private final IReportRepository repository;
    private final ICreateReportService reportService;
    private final DTOConvertor dtoConvertor;

    public ReportService(IReportRepository repository,
                         ICreateReportService reportService,
                         DTOConvertor dtoConvertor) {
        this.repository = repository;
        this.reportService = reportService;
        this.dtoConvertor = dtoConvertor;
    }

    @Override
    @Transactional
    public void create(ReportParamsDTO paramsDTO, ReportType reportType) {
        UUID uuid = UUID.fromString(paramsDTO.getUser());
        ReportEntity entity = new ReportEntity(uuid,paramsDTO.getFrom(),paramsDTO.getTo());
        entity.setUuid(UUID.fromString("ecf5052b-b0cf-4d31-8b54-107a183be8d9"));
        entity.setFileReportEntity(new FileReportEntity(
                entity.getUuid(),
                ReportStatus.LOADED));
        entity.setType(reportType);
        entity.setDescription(getDescription(paramsDTO,reportType));
        reportService.saveEntity(entity.getFileReportEntity());
        repository.save(entity);
        reportService.create(entity.getUuid(),paramsDTO.getFrom(),paramsDTO.getTo(),reportType,uuid);
    }

    @Override
    @Transactional(readOnly = true)
    public PageDTOView getPage(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<ReportEntity> reportEntities = repository.findAll(pageable);
        List<ReportViewDTO> context = new ArrayList<>();
        for(ReportEntity reportEntity : reportEntities){
            ReportViewDTO reportViewDTO = dtoConvertor.convert(reportEntity);
            reportViewDTO.setStatus(reportEntity.getFileReportEntity().getStatus());
            context.add(reportViewDTO);
        }
            return new PageDTOView(
                reportEntities.getNumber(),
                reportEntities.getSize(),
                reportEntities.getTotalPages(),
                reportEntities.getTotalElements(),
                reportEntities.isFirst(),
                reportEntities.getNumberOfElements(),
                reportEntities.isLast(),
                context
        );
    }

    @Override
    public ReportStatus getStatus(UUID uuid) {
        Optional<ReportEntity> reportEnity = repository.findById(uuid);
        ReportStatus status = reportEnity.orElseThrow(()->new IllegalArgumentException("Данного отчета нет"))
                .getFileReportEntity()
                .getStatus();

        return status;
    }

    @Override
    public String getUrl(UUID uuid) {
        return reportService.getUrl(uuid);
    }

    private String getDescription(ReportParamsDTO paramsDTO, ReportType reportType){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Отчета по ");
        switch(reportType){
            case JOURNAL_AUDIT:{
                stringBuilder.append("журналу аудита ");
                break;
            }
        }
        stringBuilder.append("за: ");
        stringBuilder.append(paramsDTO.getFrom());
        stringBuilder.append("-");
        stringBuilder.append(paramsDTO.getTo());
        stringBuilder.append(", по пользователю, которой изменил данные: ");
        stringBuilder.append(paramsDTO.getUser().toString());
        return stringBuilder.toString();
    }
}
