package by.itAcademy.userservice.service.api;

import by.itAcademy.userservice.core.dto.UserDTO;
import by.itAcademy.userservice.core.dto.UserViewDTO;

import java.util.UUID;

public interface IPersonalUserService {
    void save(UserDTO userDTO);
    void verification(String code, String mail);
    UserViewDTO login(UserDTO userDTO);
    UserViewDTO get(UUID uuid);
    UserViewDTO get(String mail);
}
