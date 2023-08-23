package by.itAcademy.userservice.inPoint.web;

import by.itAcademy.userservice.core.dto.task.ImplementerDTO;
import by.itAcademy.userservice.core.dto.task.ManagerDTO;
import by.itAcademy.userservice.core.dto.task.StaffDTO;
import by.itAcademy.userservice.service.api.ITaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/service")
public class OutController {
    private final ITaskService taskService;

    public OutController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @Secured("ROLE_SERVICE")
    @RequestMapping(path = "/manager/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<ManagerDTO> getListManager(@PathVariable("uuid") UUID uuid){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getManager(uuid));
    }


    @Secured("ROLE_SERVICE")
    @RequestMapping(path = "/staff/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<StaffDTO> getListStaff(@PathVariable("uuid") UUID uuid){
        StaffDTO staffDTO = taskService.getStaff(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(staffDTO);
    }

    @Secured("ROLE_SERVICE")
    @RequestMapping(path = "/implementer/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<ImplementerDTO> getListImplementer(@PathVariable("uuid") UUID uuid){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getImplementer(uuid));
    }
}
