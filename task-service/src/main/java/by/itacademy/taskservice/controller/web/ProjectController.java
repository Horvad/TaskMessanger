package by.itacademy.taskservice.controller.web;

import by.itacademy.taskservice.controller.untils.JwtTokenHandler;
import by.itacademy.taskservice.core.dto.PageDTO;
import by.itacademy.taskservice.core.dto.project.ProjectCreateDTO;
import by.itacademy.taskservice.core.dto.project.ProjectViewDTO;
import by.itacademy.taskservice.service.api.IProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private final IProjectService projectService;
    private final JwtTokenHandler tokenHandler;

    public ProjectController(IProjectService projectService, JwtTokenHandler tokenHandler) {
        this.projectService = projectService;
        this.tokenHandler = tokenHandler;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> create(@RequestBody ProjectCreateDTO projectCreateDTO){
        projectService.save(projectCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"})
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDTO> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                          @RequestParam(name = "size", defaultValue = "20") int size,
                                          @RequestParam(defaultValue = "false") boolean archived,
                                          @RequestHeader(defaultValue = "") String authorization){
        String token = authorization.split(" ")[1].trim();
        UUID uuidUser = UUID.fromString(tokenHandler.getClaim(token,"uuid"));
        String userRole = String.valueOf(tokenHandler.getClaim(token,"role"));
        return ResponseEntity.status(HttpStatus.OK).body(projectService.getPage(page,size,archived, userRole, uuidUser));
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"})
    @RequestMapping(path = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<ProjectViewDTO> get(@PathVariable("uuid") UUID uuid,
                                              @RequestHeader(defaultValue = "") String authorization){
        String token = authorization.split(" ")[1].trim();
        UUID uuidUser = UUID.fromString(tokenHandler.getClaim(token,"uuid"));
        String userRole = String.valueOf(tokenHandler.getClaim(token,"role"));
        return ResponseEntity.status(HttpStatus.OK).body(projectService.get(uuid,userRole,uuidUser.toString()));
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/{uuid}/dt_update/{dt_update}", method = RequestMethod.PUT,
            consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> put(@PathVariable("uuid") UUID uuid,
                                 @PathVariable("dt_update") String dtUpdateStr,
                                 @RequestBody ProjectCreateDTO projectUpdateDTO){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime dtUpdate = LocalDateTime.parse(dtUpdateStr,formatter);
        projectService.update(uuid,dtUpdate,projectUpdateDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
