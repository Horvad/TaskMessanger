package by.itAcademy.userservice.repositories.api;

import by.itAcademy.userservice.repositories.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends ListCrudRepository<UserEntity, UUID> {
    Page<UserEntity> findAll(Pageable pageable);
    Optional<UserEntity> findByMail(String mail);
}
