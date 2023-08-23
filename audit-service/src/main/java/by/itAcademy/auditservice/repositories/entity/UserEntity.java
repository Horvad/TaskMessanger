package by.itAcademy.auditservice.repositories.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;


@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name = "id")
    private UUID uuid;
    @Column(name = "mail")
    private String mail;
    @Column(name = "fio")
    private String fio;
    @Column(name = "role")
    private String role;

    @Column(name = "status")
    private String status;

    @Column(name = "idString")
    private String uuidUser;

    public UserEntity() {
    }

    public UserEntity(UUID uuid, String mail, String fio, String role, String status, String uuidUser) {
        this.uuid = uuid;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.uuidUser = uuidUser;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUuidUser() {
        return uuidUser;
    }

    public void setUuidUser(String uuidUser) {
        this.uuidUser = uuidUser;
    }
}
