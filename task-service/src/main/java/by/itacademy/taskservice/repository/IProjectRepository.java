package by.itacademy.taskservice.repository;

import by.itacademy.taskservice.core.enums.ProjectStatus;
import by.itacademy.taskservice.repository.entity.ManagerEntity;
import by.itacademy.taskservice.repository.entity.ProjectEntity;
import by.itacademy.taskservice.repository.entity.StaffEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface IProjectRepository extends JpaRepository<ProjectEntity, UUID>,
        ListPagingAndSortingRepository<ProjectEntity,UUID> {
    List<ProjectEntity> findAllByStaff(StaffEntity staffEntity);
    Page<ProjectEntity> findAllByStatus(Pageable pageable, ProjectStatus status);
    List<ProjectEntity> findAllByIdIn(List<UUID> uuids);
    List<ProjectEntity> findAllByManagerOrStaff(ManagerEntity manager, StaffEntity staff);
    Page<ProjectEntity>  findAllByManagerOrStaff(Pageable pageable, ManagerEntity manager, StaffEntity staff);
    Page<ProjectEntity> findAllByStatusAndByManagerOrStaff(Pageable pageable, ProjectStatus status, ManagerEntity manager, StaffEntity staff);

}
