package com.example.reportservice.repository.minio;

import com.example.reportservice.core.DTO.AuditSendDTO;
import com.example.reportservice.service.api.IFileService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class XLSXGenarateFile implements IFileService {
    @Override
    public File generateReport(List<AuditSendDTO> auditViewDTOList, String fileName) {
        Workbook wkb = new HSSFWorkbook();
        Sheet sheet = wkb.createSheet("Audit Report");
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("UUID audit");
        headerRow.createCell(1).setCellValue("uuid editor");
        headerRow.createCell(2).setCellValue("role editor");
        headerRow.createCell(3).setCellValue("text");
        headerRow.createCell(4).setCellValue("user uuid");
        headerRow.createCell(5).setCellValue("user fio");
        headerRow.createCell(6).setCellValue("user mail");
        headerRow.createCell(7).setCellValue("user role");
        headerRow.createCell(8).setCellValue("user status");
        int count = 1;
        for(AuditSendDTO auditDTO : auditViewDTOList){
            Row row = sheet.createRow(count);
            row.createCell(0).setCellValue(auditDTO.getUuid());
            row.createCell(1).setCellValue(auditDTO.getUuidEditor());
            row.createCell(2).setCellValue(auditDTO.getUserRole());
            row.createCell(3).setCellValue(auditDTO.getText());
            row.createCell(4).setCellValue(auditDTO.getUserUuid());
            row.createCell(5).setCellValue(auditDTO.getUserFio());
            row.createCell(6).setCellValue(auditDTO.getUserMail());
            row.createCell(7).setCellValue(auditDTO.getUserRole());
            row.createCell(8).setCellValue(auditDTO.getUserStatus());
        }
        File file = new File(fileName);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)){
            wkb.write(fileOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    @Override
    public void deleteFile(String fileName) {
    }
}
