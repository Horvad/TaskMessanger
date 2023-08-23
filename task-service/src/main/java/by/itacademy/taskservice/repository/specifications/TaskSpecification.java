package by.itacademy.taskservice.repository.specifications;;

import by.itacademy.taskservice.core.dto.other.ImplementerDTO;
import by.itacademy.taskservice.core.dto.project.ProjectViewDTO;
import by.itacademy.taskservice.core.dto.task.ProjectForTaskDTO;
import by.itacademy.taskservice.core.enums.TaskStatus;
import by.itacademy.taskservice.repository.entity.TaskEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public class TaskSpecification {
    public static Specification<TaskEntity> byStatus(List<TaskStatus> status) {
        return (root, query, builder) ->
                builder.in(root.get("status")).value(status);
    }
    public static Specification<TaskEntity> byImplementer(List<ImplementerDTO> implementer) {
        List<UUID> uuids = implementer.stream().map(ImplementerDTO::getUuid).toList();
        return (root, query, builder) ->
                builder.in(root.get("implementer").get("uuid")).value(uuids);
    }
    public static Specification<TaskEntity> byProject(List<ProjectForTaskDTO> project) {
        List<UUID> uuids = project.stream().map(ProjectForTaskDTO::getUuid).toList();
        return (root, query, builder) ->
                builder.in(root.get("project").get("id")).value(uuids);
    }
}