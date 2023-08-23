package by.itacademy.taskservice.repository;

import by.itacademy.taskservice.core.enums.TaskStatus;
import by.itacademy.taskservice.repository.entity.ImplementerEntity;
import by.itacademy.taskservice.repository.entity.ProjectEntity;
import by.itacademy.taskservice.repository.entity.TaskEntity;
import ch.qos.logback.core.read.ListAppender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface ITaskRepository extends JpaRepository<TaskEntity, UUID>, ListPagingAndSortingRepository<TaskEntity, UUID> {
    Page<TaskEntity> findAll(Pageable pageable);
    Page<TaskEntity> findAllByProjectIn(Pageable pageable, List<ProjectEntity> projects);
    Page<TaskEntity> findAllByProjectInAndImplementerIn(Pageable pageable, List<ProjectEntity> projects, List<ImplementerEntity> implementers);
    Page<TaskEntity> findAllByProjectInAndStatusIn(Pageable pageable, List<ProjectEntity> projects, List<TaskStatus> statuses);
    Page<TaskEntity> findAllByImplementerIn(Pageable pageable, List<ImplementerEntity> implementerEntities);
    Page<TaskEntity> findAllByImplementerInAndStatusIn(Pageable pageable, List<ImplementerEntity> implementerEntities, List<TaskStatus> statuses);
    Page<TaskEntity> findAllByStatusIn(Pageable pageable, List<TaskStatus> statuses);
    Page<TaskEntity> findAllByProjectInAndImplementerInAndStatusIn(Pageable pageable, List<ProjectEntity> projectEntities, List<ImplementerEntity> implementerEntities, List<TaskStatus> statuses);
}
