package by.itAcademy.userservice.service.api;

import by.itAcademy.userservice.core.dto.AuditSendDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "audit-service", url = "${feign.audit.url}")
public interface IAuditFeignClient {
    @RequestMapping(method = RequestMethod.POST, value = "/userService", consumes = "application/json")
    void sendUser(@RequestBody AuditSendDTO auditDTO,
                  @RequestHeader("Authorization") String Authorization);
}
