package by.itAcademy.auditservice.inPoint.web;

import by.itAcademy.auditservice.core.dto.AuditSendDTO;
import by.itAcademy.auditservice.service.api.IAuditService;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/audit")
public class AuditController {
    private final IAuditService auditService;

    public AuditController(IAuditService auditService) {
        this.auditService = auditService;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> audit(
            @RequestParam(name = "page", defaultValue = "0") int numberPage,
            @RequestParam(name = "size", defaultValue = "20") int size
    ){
        return ResponseEntity.status(HttpStatus.OK).body(auditService.getPageAudit(numberPage,size));
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(path = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<?> auditUuid(@PathVariable("uuid") UUID uuid){
        return ResponseEntity.status(HttpStatus.OK).body(auditService.getAuditDTO(uuid));
    }


}
