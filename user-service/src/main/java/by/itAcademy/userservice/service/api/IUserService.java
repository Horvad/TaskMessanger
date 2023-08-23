package by.itAcademy.userservice.service.api;

import by.itAcademy.userservice.core.dto.PageDTO;
import by.itAcademy.userservice.core.dto.UserDTO;
import by.itAcademy.userservice.core.dto.UserViewDTO;
import by.itAcademy.userservice.core.enums.UserRole;
import by.itAcademy.userservice.repositories.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    UserViewDTO getByUuid(UUID uuid);
    void edit(UUID uuid, LocalDateTime dtUpdate, UserDTO userDTO, UUID uuidEditor, UserRole userRoleEditor);
    PageDTO getPage(int page, int size);
    void save(UserDTO userCreateDTO, UUID uuidSaver, UserRole userRoleSaver);
    Optional<UserEntity> findByMail(String mail);
    void save(UserEntity userEntity);
}
