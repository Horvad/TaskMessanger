package by.itacademy.taskservice.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "manager")
public class ManagerEntity {
    @Id
    @Column(name = "id")
    private UUID uuid;

    public ManagerEntity() {
    }

    public ManagerEntity(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
