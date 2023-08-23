package by.itAcademy.userservice.service.api;

import by.itAcademy.userservice.core.dto.task.ImplementerDTO;
import by.itAcademy.userservice.core.dto.task.ManagerDTO;
import by.itAcademy.userservice.core.dto.task.StaffDTO;

import java.util.List;
import java.util.UUID;

public interface ITaskService {
    ImplementerDTO getImplementer(UUID uuids);
    ManagerDTO getManager(UUID uuids);
    StaffDTO getStaff(UUID uuids);
}
