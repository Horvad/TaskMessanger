package by.itacademy.taskservice.core.dto.task;

import java.util.UUID;

public class ProjectForTaskDTO {
    private UUID uuid;

    public ProjectForTaskDTO() {
    }

    public ProjectForTaskDTO(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
