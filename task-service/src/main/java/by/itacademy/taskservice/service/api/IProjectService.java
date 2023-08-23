package by.itacademy.taskservice.service.api;

import by.itacademy.taskservice.core.dto.PageDTO;
import by.itacademy.taskservice.core.dto.project.ProjectCreateDTO;
import by.itacademy.taskservice.core.dto.project.ProjectViewDTO;
import by.itacademy.taskservice.core.dto.task.ProjectForTaskDTO;
import by.itacademy.taskservice.repository.entity.ManagerEntity;
import by.itacademy.taskservice.repository.entity.ProjectEntity;
import by.itacademy.taskservice.repository.entity.StaffEntity;
import org.aspectj.asm.internal.ProgramElement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IProjectService {
    void save(ProjectCreateDTO projectCreateDTO);
    PageDTO getPage(int page, int size, boolean archived, String userRole, UUID uuid);
    ProjectViewDTO get(UUID uuid, String userUuid, String userRole);
    void update(UUID uuid, LocalDateTime dtUpdate, ProjectCreateDTO updateDTO);
    boolean exist(UUID uuid);
    ProjectEntity getEntity(UUID uuid);
    List<ProjectEntity> findByStaff(UUID staff);
    List<ProjectEntity> findAll();
    List<ProjectEntity> findById(List<ProjectForTaskDTO> project);
    List<ProjectEntity> findAllByProjectInOrImplementerIn(List<ProjectEntity> projectEntities);
    List<ProjectEntity> findAllByUuid(UUID uuid);
}
