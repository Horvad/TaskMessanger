package by.itAcademy.userservice.core.dto.task;

import java.util.UUID;

public class ImplementerDTO {
    private UUID uuid;

    public ImplementerDTO() {
    }

    public ImplementerDTO(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
