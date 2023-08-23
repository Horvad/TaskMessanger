package by.itAcademy.userservice.service.api;

import by.itAcademy.userservice.core.dto.UserDTO;
import by.itAcademy.userservice.core.dto.UserViewDTO;
import by.itAcademy.userservice.core.enums.UserRole;

import java.util.UUID;

public interface IAuditService {
    void sendForAudit(UUID uuid, UserRole userRole, String nameOperation, UserViewDTO userViewDTO);
}
