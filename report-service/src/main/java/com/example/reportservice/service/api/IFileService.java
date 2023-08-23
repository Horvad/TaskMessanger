package com.example.reportservice.service.api;

import com.example.reportservice.core.DTO.AuditSendDTO;

import java.io.File;
import java.util.List;

public interface IFileService {
    File generateReport(List<AuditSendDTO> auditViewDTOList, String fileName);

    void deleteFile(String fileName);
}
