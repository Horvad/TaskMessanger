package by.itAcademy.auditservice.repositories.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audit")
public class AuditEntity {
    @Id
    @Column(name = "id")
    private UUID uuid;
    @Column(name = "dt_create")
    @CreationTimestamp
    private LocalDateTime dtCreate;
    @Column(name = "text")
    private String text;
    @Column(name = "role")
    private String role;
    @Column(name = "id_String")
    private String idString;

    @OneToOne
    private UserEntity userEntity;

    public AuditEntity() {
    }

    public AuditEntity(UUID uuid, LocalDateTime dtCreate, String text, String role, String idString, UserEntity userEntity) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.text = text;
        this.role = role;
        this.idString = idString;
        this.userEntity = userEntity;
    }

    public AuditEntity(UUID uuid, String text, String role, String idString, UserEntity userEntity) {
        this.uuid = uuid;
        this.text = text;
        this.role = role;
        this.idString = idString;
        this.userEntity = userEntity;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
