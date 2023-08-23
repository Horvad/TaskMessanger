package by.itAcademy.userservice.repositories.entity;

import by.itAcademy.userservice.core.enums.UserRole;
import by.itAcademy.userservice.core.enums.UserStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name = "id")
    private UUID uuid;
    @Column(name = "dt_create", precision = 3)
    @CreationTimestamp
    private LocalDateTime dtCreate;
    @Column(name = "dt_update", precision = 3)
    @Version
    @UpdateTimestamp
    private LocalDateTime dtUpdate;
    @Column(name = "fio")
    private String fio;
    @Column(name = "mail")
    private String mail;
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;

    public UserEntity() {
    }

    public UserEntity(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String fio, String mail, String password, UserRole role, UserStatus status) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.fio = fio;
        this.mail = mail;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public UserEntity(String fio, String mail, String password, UserRole role, UserStatus status) {
        this.fio = fio;
        this.mail = mail;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}