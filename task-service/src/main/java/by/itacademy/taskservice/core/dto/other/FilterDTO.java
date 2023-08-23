package by.itacademy.taskservice.core.dto.other;

import by.itacademy.taskservice.core.dto.task.ProjectForTaskDTO;
import by.itacademy.taskservice.core.enums.TaskStatus;

import java.util.List;
import java.util.UUID;

public class FilterDTO {
    private UUID uuid;
    String userRole;
    List<ProjectForTaskDTO> project;
    List<ImplementerDTO> implementerDTOS;
    List<TaskStatus> statusTask;

    public FilterDTO() {
    }

    public FilterDTO(UUID uuid,
                     String userRole,
                     List<ProjectForTaskDTO> project,
                     List<ImplementerDTO> implementerDTOS,
                     List<TaskStatus> statusTask) {
        this.uuid = uuid;
        this.userRole = userRole;
        this.project = project;
        this.implementerDTOS = implementerDTOS;
        this.statusTask = statusTask;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public List<ProjectForTaskDTO> getProject() {
        return project;
    }

    public void setProject(List<ProjectForTaskDTO> project) {
        this.project = project;
    }

    public List<ImplementerDTO> getImplementerDTOS() {
        return implementerDTOS;
    }

    public void setImplementerDTOS(List<ImplementerDTO> implementerDTOS) {
        this.implementerDTOS = implementerDTOS;
    }

    public List<TaskStatus> getStatusTask() {
        return statusTask;
    }

    public void setStatusTask(List<TaskStatus> statusTask) {
        this.statusTask = statusTask;
    }
}
