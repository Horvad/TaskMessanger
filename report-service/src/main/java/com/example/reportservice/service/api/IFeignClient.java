package com.example.reportservice.service.api;

import com.example.reportservice.core.DTO.PageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;

@FeignClient(name = "audit-service", url = "${feign.audit.url}")
public interface IFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/report")
    ResponseEntity<PageDTO> getAudit(@RequestHeader int page,
                                     @RequestHeader int size,
                                     @RequestHeader LocalDate from,
                                     @RequestHeader LocalDate to,
                                     @RequestHeader String uuid,
                                     @RequestHeader("Authorization") String Authorization);
}
