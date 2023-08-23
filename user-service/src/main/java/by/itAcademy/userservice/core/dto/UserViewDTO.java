package by.itAcademy.userservice.core.dto;

import by.itAcademy.userservice.core.enums.UserRole;
import by.itAcademy.userservice.core.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class UserViewDTO {
    private UUID uuid;
    private String fio;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String mail;
    private UserRole userRole;
    private UserStatus userStatus;

    public UserViewDTO() {
    }

    public UserViewDTO(UUID uuid, String fio, LocalDateTime dtCreate, LocalDateTime dtUpdate,
                       String mail, UserRole userRole, UserStatus userStatus) {
        this.uuid = uuid;
        this.fio = fio;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.mail = mail;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }

    public UserViewDTO(UUID uuid, String fio, String mail, UserRole userRole, UserStatus userStatus) {
        this.uuid = uuid;
        this.fio = fio;
        this.mail = mail;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
