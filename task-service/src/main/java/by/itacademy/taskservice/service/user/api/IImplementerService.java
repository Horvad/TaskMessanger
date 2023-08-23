package by.itacademy.taskservice.service.user.api;

import by.itacademy.taskservice.core.dto.other.ImplementerDTO;
import by.itacademy.taskservice.repository.entity.ImplementerEntity;

import java.util.List;
import java.util.UUID;

public interface IImplementerService {
    ImplementerEntity get(UUID uuid);

    List<ImplementerEntity> getAll();

    List<ImplementerEntity> getAllByList(List<ImplementerDTO> uuids);
}
