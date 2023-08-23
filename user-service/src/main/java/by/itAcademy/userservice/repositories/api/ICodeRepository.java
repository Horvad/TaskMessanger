package by.itAcademy.userservice.repositories.api;

import by.itAcademy.userservice.repositories.entity.CodeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ICodeRepository extends CrudRepository<CodeEntity, String> {
    Optional<CodeEntity> findByMail(String mail);
}
