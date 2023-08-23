package com.example.reportservice.repository.entity;

import com.example.reportservice.core.enums.ReportStatus;
import com.example.reportservice.core.enums.ReportType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.print.attribute.standard.MediaSize;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class ReportEntity {
    @Id
    @Column(name = "uuid")
    private UUID uuid;
    @CreationTimestamp
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @UpdateTimestamp
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ReportType type;
    @Column(name = "description")
    private String description;
    @Column(name = "uuidCreate")
    private UUID uuidCreate;
    @Column(name = "dateFrom")
    private LocalDate dateFrom;
    @Column(name = "dateTo")
    private LocalDate dateTo;

    @OneToOne
    private FileReportEntity fileReportEntity;

    public ReportEntity() {
    }

    public ReportEntity(UUID uuidCreate,
                        LocalDate dateFrom,
                        LocalDate dateTo) {
        this.uuidCreate = uuidCreate;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
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

    public UUID getUuidCreate() {
        return uuidCreate;
    }

    public void setUuidCreate(UUID uuidCreate) {
        this.uuidCreate = uuidCreate;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public FileReportEntity getFileReportEntity() {
        return fileReportEntity;
    }

    public void setFileReportEntity(FileReportEntity fileReportEntity) {
        this.fileReportEntity = fileReportEntity;
    }
}
