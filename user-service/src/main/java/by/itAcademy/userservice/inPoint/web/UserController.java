package by.itAcademy.userservice.inPoint.web;

import by.itAcademy.userservice.core.dto.PageDTO;
import by.itAcademy.userservice.core.dto.UserDTO;
import by.itAcademy.userservice.core.dto.UserViewDTO;
import by.itAcademy.userservice.core.enums.UserRole;
import by.itAcademy.userservice.inPoint.untils.JwtTokenHandler;
import by.itAcademy.userservice.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;
    private final JwtTokenHandler tokenHandler;

    public UserController(IUserService userService, JwtTokenHandler tokenHandler) {
        this.userService = userService;
        this.tokenHandler = tokenHandler;
    }
    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addNewUser(@RequestBody UserDTO userCreateDTO,
                                        @RequestHeader() String authorization){
        String token = authorization.split(" ")[1].trim();
        UUID uuid = UUID.fromString(tokenHandler.getClaim(token,"uuid"));
        UserRole userRole = UserRole.valueOf(tokenHandler.getClaim(token,"role"));
        userService.save(userCreateDTO, uuid,userRole);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDTO> getPage(
            @RequestParam(name = "page", defaultValue = "0") int numberPage,
            @RequestParam(name = "size", defaultValue = "20") int size
    ){
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.getPage(numberPage,size));
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(path = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<UserViewDTO> viewUser(@PathVariable("uuid") UUID uuid){
        UserViewDTO userViewDTO = userService.getByUuid(uuid);
        return new ResponseEntity<>(userViewDTO,HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(path = "/{uuid}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> edit(@PathVariable("uuid") UUID uuid,
                                  @PathVariable("dt_update") String dtUpdate,
                                  @RequestHeader() String authorization,
                                  @RequestBody UserDTO userCreateDTO){
        String token = authorization.split(" ")[1].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime dateUpdate = LocalDateTime.parse(dtUpdate,formatter);
        System.out.println(dateUpdate);
        System.out.println(dateUpdate);
        UUID uuidEditor = UUID.fromString(tokenHandler.getClaim(token,"uuid"));
        UserRole userRoleEditor = UserRole.valueOf(tokenHandler.getClaim(token,"role"));
        userService.edit(uuid,dateUpdate,userCreateDTO,uuidEditor,userRoleEditor);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
