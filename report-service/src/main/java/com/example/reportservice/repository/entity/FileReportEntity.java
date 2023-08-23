package com.example.reportservice.repository.entity;

import com.example.reportservice.core.enums.ReportStatus;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class FileReportEntity {
    @Id
    @Column(name = "id")
    private UUID uuid;
    @Column(name = "file_name")
    private String fileName;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReportStatus status;

    public FileReportEntity() {
    }

    public FileReportEntity(UUID uuid, ReportStatus status) {
        this.uuid = uuid;
        this.status = status;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
