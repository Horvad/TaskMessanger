package by.itAcademy.userservice.repositories.api;

import by.itAcademy.userservice.repositories.entity.MailEntity;
import org.springframework.data.repository.CrudRepository;

public interface IMailRepository extends CrudRepository<MailEntity, Long> {
}
