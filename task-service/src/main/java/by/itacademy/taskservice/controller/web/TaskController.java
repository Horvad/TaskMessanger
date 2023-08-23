package by.itacademy.taskservice.controller.web;

import by.itacademy.taskservice.controller.untils.JwtTokenHandler;
import by.itacademy.taskservice.core.dto.PageDTO;
import by.itacademy.taskservice.core.dto.other.FilterDTO;
import by.itacademy.taskservice.core.dto.task.TaskCreateDTO;
import by.itacademy.taskservice.core.dto.task.TaskViewDTO;
import by.itacademy.taskservice.core.enums.TaskStatus;
import by.itacademy.taskservice.service.api.ITaskService;
import by.itacademy.taskservice.service.user.until.creator.FilterDTOCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final ITaskService taskService;
    private final JwtTokenHandler tokenHandler;
    private final FilterDTOCreator creator;

    public TaskController(ITaskService taskService, JwtTokenHandler tokenHandler, FilterDTOCreator creator) {
        this.taskService = taskService;
        this.tokenHandler = tokenHandler;
        this.creator = creator;
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"})
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> create(@RequestBody TaskCreateDTO taskCreateDTO){
        taskService.save(taskCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"})
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDTO> getAll(@RequestParam(required = false, defaultValue = "0") int page,
                                          @RequestParam(required = false, defaultValue = "20") int size,
                                          @RequestParam(required = false) List<TaskStatus> statuses,
                                          @RequestParam(required = false) List<UUID> projects,
                                          @RequestParam(required = false) List<UUID> implementers,
                                          @RequestHeader(defaultValue = "") String authorization){
        String token = authorization.split(" ")[1].trim();
        UUID uuidUser = UUID.fromString(tokenHandler.getClaim(token,"uuid"));
        String userRole = String.valueOf(tokenHandler.getClaim(token,"role"));
//        ImplementerDTO implementerDTO = new ImplementerDTO();
//        implementerDTO.setUuid(UUID.fromString(implementers));
//        List<ImplementerDTO> list = new ArrayList<>();
//        list.add(implementerDTO);
        FilterDTO filterDTO = creator.createFilterDTO(implementers,projects,statuses);
        filterDTO.setUserRole(userRole);
        filterDTO.setUuid(uuidUser);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getPage(page,size,filterDTO));
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"})
    @RequestMapping(path = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<TaskViewDTO> get(@PathVariable("uuid") UUID uuid){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.get(uuid));
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"})
    @RequestMapping(path = "/{uuid}/dt_update/{dt_update}", method = RequestMethod.PUT,
            consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> update(@PathVariable("uuid")UUID uuid,
                                    @PathVariable("dt_update")String dtUpdateStr,
                                    @RequestBody TaskCreateDTO taskCreateDTO){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime dtUpdate = LocalDateTime.parse(dtUpdateStr,formatter);
        taskService.update(uuid,dtUpdate,taskCreateDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
