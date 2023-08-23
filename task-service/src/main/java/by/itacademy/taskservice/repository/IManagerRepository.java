package by.itacademy.taskservice.repository;

import by.itacademy.taskservice.repository.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IManagerRepository extends JpaRepository<ManagerEntity, UUID> {
}
