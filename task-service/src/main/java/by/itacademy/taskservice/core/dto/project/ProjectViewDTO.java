package by.itacademy.taskservice.core.dto.project;

import by.itacademy.taskservice.core.dto.other.ManagerDTO;
import by.itacademy.taskservice.core.dto.other.StaffDTO;
import by.itacademy.taskservice.core.enums.ProjectStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ProjectViewDTO {
    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String name;
    private String description;
    private ManagerDTO manager;
    private List<StaffDTO> staff;
    private ProjectStatus projectStatus;

    public ProjectViewDTO() {
    }

    public ProjectViewDTO(UUID uuid,
                          LocalDateTime dtCreate,
                          LocalDateTime dtUpdate,
                          String name,
                          String description,
                          ManagerDTO manager,
                          List<StaffDTO> staff,
                          ProjectStatus projectStatus) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.name = name;
        this.description = description;
        this.manager = manager;
        this.staff = staff;
        this.projectStatus = projectStatus;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ManagerDTO getManager() {
        return manager;
    }

    public void setManager(ManagerDTO manager) {
        this.manager = manager;
    }

    public List<StaffDTO> getStaff() {
        return staff;
    }

    public void setStaff(List<StaffDTO> staff) {
        this.staff = staff;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }
}
