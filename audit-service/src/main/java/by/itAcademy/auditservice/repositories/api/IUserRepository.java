package by.itAcademy.auditservice.repositories.api;

import by.itAcademy.auditservice.repositories.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IUserRepository extends CrudRepository<UserEntity, UUID> {
}
