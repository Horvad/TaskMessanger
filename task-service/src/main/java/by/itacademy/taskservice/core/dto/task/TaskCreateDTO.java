package by.itacademy.taskservice.core.dto.task;

import by.itacademy.taskservice.core.dto.other.ImplementerDTO;
import by.itacademy.taskservice.core.enums.TaskStatus;

import java.util.UUID;

public class TaskCreateDTO {
    private ProjectForTaskDTO project;
    private String title;
    private String description;
    private TaskStatus status;
    private ImplementerDTO implementer;

    public TaskCreateDTO() {
    }

    public TaskCreateDTO(ProjectForTaskDTO project,
                         String title,
                         String description,
                         TaskStatus status,
                         ImplementerDTO implementer) {
        this.project = project;
        this.title = title;
        this.description = description;
        this.status = status;
        this.implementer = implementer;
    }

    public ProjectForTaskDTO getProject() {
        return project;
    }

    public void setProject(ProjectForTaskDTO project) {
        this.project = project;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public ImplementerDTO getImplementer() {
        return implementer;
    }

    public void setImplementer(ImplementerDTO implementer) {
        this.implementer = implementer;
    }
}
