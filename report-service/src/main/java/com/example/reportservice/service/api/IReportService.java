package com.example.reportservice.service.api;

import com.example.reportservice.core.DTO.PageDTO;
import com.example.reportservice.core.DTO.PageDTOView;
import com.example.reportservice.core.DTO.ReportParamsDTO;
import com.example.reportservice.core.enums.ReportStatus;
import com.example.reportservice.core.enums.ReportType;

import java.util.UUID;

public interface IReportService {
    void create(ReportParamsDTO paramsDTO, ReportType reportType);
    PageDTOView getPage(int page, int size);
    ReportStatus getStatus(UUID uuid);
    String getUrl(UUID uuid);
}
