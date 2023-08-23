package by.itacademy.taskservice.repository;

import by.itacademy.taskservice.repository.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface IStaffRepository extends JpaRepository<StaffEntity, UUID> {
}
