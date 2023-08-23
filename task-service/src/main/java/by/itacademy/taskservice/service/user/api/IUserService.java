package by.itacademy.taskservice.service.user.api;

import by.itacademy.taskservice.core.dto.other.ImplementerDTO;
import by.itacademy.taskservice.core.dto.other.ManagerDTO;
import by.itacademy.taskservice.core.dto.other.StaffDTO;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    ImplementerDTO getImplementers(UUID uuids);
    ManagerDTO getManager(UUID uuids);
    StaffDTO getStaff(UUID uuids);
}
