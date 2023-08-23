package by.itacademy.taskservice.core.dto.other;

import java.util.UUID;

public class ManagerDTO {
    private UUID uuid;

    public ManagerDTO() {
    }

    public ManagerDTO(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
