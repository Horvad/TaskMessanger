package by.itacademy.taskservice.service.user.api;

import by.itacademy.taskservice.repository.entity.ManagerEntity;

import java.util.List;
import java.util.UUID;

public interface IManagerService {
    ManagerEntity get(UUID uuid);
    List<ManagerEntity> findAll();
}
