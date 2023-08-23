package by.itAcademy.userservice.inPoint.web;

import by.itAcademy.userservice.core.dto.UserDTO;
import by.itAcademy.userservice.core.dto.UserViewDTO;
import by.itAcademy.userservice.inPoint.untils.JwtTokenHandler;
import by.itAcademy.userservice.service.api.IPersonalUserService;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
public class PersonalController {
    private final IPersonalUserService personalUserService;
    private final JwtTokenHandler jwtTokenHandler;

    public PersonalController(IPersonalUserService personalUserService, JwtTokenHandler jwtTokenHandler) {
        this.personalUserService = personalUserService;
        this.jwtTokenHandler = jwtTokenHandler;
    }
    @PermitAll
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody UserDTO userDTO){
        personalUserService.save(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PermitAll
    @RequestMapping(path = "/verification", method = RequestMethod.GET)
    public ResponseEntity<?> verification(@RequestParam String code,
                                          @RequestParam String mail){
        personalUserService.verification(code,mail);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PermitAll
    @RequestMapping(path = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
        UserViewDTO userViewDTO = personalUserService.login(userDTO);
        Map<String, Object> mapForToken = new HashMap<>();
        mapForToken.put("role",userViewDTO.getUserRole());
        mapForToken.put("mail", userViewDTO.getMail());
        mapForToken.put("uuid",userViewDTO.getUuid());
        return ResponseEntity.status(HttpStatus.OK).body(jwtTokenHandler.generateAccessToken(mapForToken));
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(path = "/me", method = RequestMethod.GET)
    public ResponseEntity<?> me(@RequestHeader String authorization){
        String token = authorization.split(" ")[1].trim();
        String uuid = jwtTokenHandler.getClaim(token,"uuid");
        UserViewDTO viewDTO = personalUserService.get(UUID.fromString(uuid));
        return ResponseEntity.status(HttpStatus.OK).body(viewDTO);
    }
}
