package com.example.reportservice.inPoint.web;

import com.example.reportservice.core.DTO.PageDTO;
import com.example.reportservice.core.DTO.PageDTOView;
import com.example.reportservice.core.DTO.ReportParamsDTO;
import com.example.reportservice.core.enums.ReportStatus;
import com.example.reportservice.core.enums.ReportType;
import com.example.reportservice.service.api.IReportService;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/report")
public class ReportController {
    private final IReportService reportService;

    public ReportController(IReportService reportService) {
        this.reportService = reportService;
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"})
    @RequestMapping(path = "/{type}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> create(@PathVariable("type") ReportType reportType,
                                    @RequestBody ReportParamsDTO reportParamsDTO){
//        ReportType reportType;
//        try {
//            reportType = ReportType.valueOf(typeStr);
//        } catch (IllegalArgumentException e){
//            throw new IllegalArgumentException("Не верный тип аудита",e);
//        }
        reportService.create(reportParamsDTO, reportType);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"})
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDTOView> getPage(@RequestParam(name = "page", defaultValue = "0") int page,
                                               @RequestParam(name = "size", defaultValue = "20") int size){
        return ResponseEntity.status(HttpStatus.OK).body(reportService.getPage(page,size));
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"})
    @RequestMapping(path = "/{uuid}/export", method = RequestMethod.GET)
    public void getReport(@PathVariable("uuid") UUID uuid, HttpServletResponse response) throws IOException {
        System.out.println();
        try {
            String url = reportService.getUrl(uuid);
            response.sendRedirect(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"})
    @RequestMapping(path = "/{uuid}/export", method = RequestMethod.HEAD)
    public ResponseEntity<?> getStatus(@PathVariable("uuid") UUID uuid){
        ReportStatus status = reportService.getStatus(uuid);
        if(status.equals("DONE")){
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
