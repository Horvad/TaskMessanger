package by.itacademy.taskservice.core.dto.project;

import by.itacademy.taskservice.core.dto.other.ManagerDTO;
import by.itacademy.taskservice.core.dto.other.StaffDTO;
import by.itacademy.taskservice.core.enums.ProjectStatus;

import java.util.List;
import java.util.UUID;

public class ProjectCreateDTO {
    private String name;
    private String description;
    private ManagerDTO manager;
    private List<StaffDTO> staff;
    private ProjectStatus status;

    public ProjectCreateDTO() {
    }

    public ProjectCreateDTO(String name,
                            String description,
                            ManagerDTO manager,
                            List<StaffDTO> staff,
                            ProjectStatus status) {
        this.name = name;
        this.description = description;
        this.manager = manager;
        this.staff = staff;
        this.status = status;
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

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
}
