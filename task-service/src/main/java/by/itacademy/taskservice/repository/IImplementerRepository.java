package by.itacademy.taskservice.repository;

import by.itacademy.taskservice.repository.entity.ImplementerEntity;
import by.itacademy.taskservice.repository.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IImplementerRepository extends JpaRepository<ImplementerEntity, UUID> {
    List<ManagerEntity> findAllByUuidIn(List<UUID> uuids);
}
