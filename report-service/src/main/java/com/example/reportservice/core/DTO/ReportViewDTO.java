package com.example.reportservice.core.DTO;

import com.example.reportservice.core.enums.ReportStatus;
import com.example.reportservice.core.enums.ReportType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class ReportViewDTO {
    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private ReportStatus status;
    private ReportType type;
    private String description;
    private ReportParamsDTO reportParamsDTO;

    public ReportViewDTO() {
    }

    public ReportViewDTO(UUID uuid,
                         LocalDateTime dtCreate,
                         LocalDateTime dtUpdate,
                         ReportStatus status,
                         ReportType type,
                         String description,
                         ReportParamsDTO reportParamsDTO) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.status = status;
        this.type = type;
        this.description = description;
        this.reportParamsDTO = reportParamsDTO;
    }

    public ReportViewDTO(UUID uuid,
                         LocalDateTime dtCreate,
                         LocalDateTime dtUpdate,
                         ReportType type,
                         String description,
                         ReportParamsDTO reportParamsDTO) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.type = type;
        this.description = description;
        this.reportParamsDTO = reportParamsDTO;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReportParamsDTO getReportParamsDTO() {
        return reportParamsDTO;
    }

    public void setReportParamsDTO(ReportParamsDTO reportParamsDTO) {
        this.reportParamsDTO = reportParamsDTO;
    }
}
