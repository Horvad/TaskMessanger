package com.example.reportservice.service;

import com.example.reportservice.core.DTO.AuditSendDTO;
import com.example.reportservice.core.DTO.PageDTO;
import com.example.reportservice.inPoint.untils.JwtTokenHandler;
import com.example.reportservice.service.api.IAuditReportService;
import com.example.reportservice.service.api.IFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.time.LocalDate;
import java.util.*;

@Service
public class AuditReport implements IAuditReportService {
    @Autowired
    private final IFeignClient feignClient;
    private final JwtTokenHandler tokenHandler;

    public AuditReport(IFeignClient feignClient, JwtTokenHandler tokenHandler) {
        this.feignClient = feignClient;
        this.tokenHandler = tokenHandler;
    }

    @Override
    public List<AuditSendDTO> getAudits(LocalDate dateFrom, LocalDate dateTo, UUID userUuid) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setLast(false);
        int page = 0;
        int size = 20;
        List<AuditSendDTO> auditViewDTOS = new ArrayList<>();
        while (!pageDTO.isLast()){
            try {
                pageDTO = feignClient.getAudit(page,size,dateFrom,dateTo, userUuid.toString(),generateToken()).getBody();
            } catch (RuntimeException e){
                throw new FindException("Внутренняю ошибка сервера " +e.getMessage());
            }
            if (pageDTO.getContent()==null|| pageDTO.getContent().size()==0){
                auditViewDTOS.add(new AuditSendDTO("","","","","","","","",""));
            } else {
                for(AuditSendDTO auditSendDTO : pageDTO.getContent()){
                    auditViewDTOS.add(auditSendDTO);
                }
            }
            page++;
        }
        return auditViewDTOS;
    }

    private String generateToken(){
        Map<String, Object> authorities = new HashMap<>();
        authorities.put("role","SERVICE");
        authorities.put("mail","report-service");
        String token = tokenHandler.generateAccessToken(authorities);
        return "Bearer "+token;
    }
}
