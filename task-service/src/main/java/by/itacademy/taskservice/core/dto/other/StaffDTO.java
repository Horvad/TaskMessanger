package by.itacademy.taskservice.core.dto.other;

import java.util.UUID;

public class StaffDTO{
    private UUID uuid;

    public StaffDTO() {
    }

    public StaffDTO(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
