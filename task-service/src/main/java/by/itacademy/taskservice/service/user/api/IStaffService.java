package by.itacademy.taskservice.service.user.api;

import by.itacademy.taskservice.repository.entity.StaffEntity;

import java.util.List;
import java.util.UUID;

public interface IStaffService {
    StaffEntity get(UUID uuid);
    List<StaffEntity> findAll();
}
