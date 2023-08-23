package by.itAcademy.auditservice.inPoint.web;

import by.itAcademy.auditservice.core.dto.AuditSendDTO;
import by.itAcademy.auditservice.core.dto.PageDTOView;
import by.itAcademy.auditservice.service.api.IAuditService;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/out")
public class FeignController {
    private final IAuditService auditService;

    public FeignController(IAuditService auditService) {
        this.auditService = auditService;
    }

    @Secured("ROLE_SERVICE")
    @RequestMapping(path = "/userService", consumes = "application/json", produces = "application/json",
            method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody AuditSendDTO auditSendDTO){
        this.auditService.save(auditSendDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Secured("ROLE_SERVICE")
    @RequestMapping(method = RequestMethod.GET, value = "/report")
    public ResponseEntity<PageDTOView> getAudit(@RequestHeader int page,
                                                @RequestHeader int size,
                                                @RequestHeader LocalDate from,
                                                @RequestHeader LocalDate to,
                                                @RequestHeader String uuid) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(auditService
                        .getByUuidBetweenData(size,page, UUID.fromString(uuid),from,to));
    }
}

