package by.itAcademy.userservice.core.dto;

import by.itAcademy.userservice.core.enums.UserRole;
import by.itAcademy.userservice.core.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserAuditDTO {
    private String uuid;
    private String fio;
    private String mail;
    private String userRole;
    private String userStatus;

    public UserAuditDTO() {
    }

    public UserAuditDTO(String uuid, String fio, String mail, String userRole, String userStatus) {
        this.uuid = uuid;
        this.fio = fio;
        this.mail = mail;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
