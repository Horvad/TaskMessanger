package by.itAcademy.userservice.repositories.api;

import by.itAcademy.userservice.repositories.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface IPersonalRepository extends CrudRepository<UserEntity, UUID> {
    UserEntity findByMail(String mail);
}
