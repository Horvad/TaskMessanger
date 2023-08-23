package by.itAcademy.userservice.service;

import by.itAcademy.userservice.core.dto.task.ImplementerDTO;
import by.itAcademy.userservice.core.dto.task.ManagerDTO;
import by.itAcademy.userservice.core.dto.task.StaffDTO;
import by.itAcademy.userservice.service.api.ITaskService;
import by.itAcademy.userservice.service.api.IUserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskService implements ITaskService {
    private final IUserService userService;

    public TaskService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public ImplementerDTO getImplementer(UUID uuid) {
        return new ImplementerDTO(userService.getByUuid(uuid).getUuid());
    }

    @Override
    public ManagerDTO getManager(UUID uuid) {
        return new ManagerDTO(userService.getByUuid(uuid).getUuid());
    }

    @Override
    public StaffDTO getStaff(UUID uuid) {
        return new StaffDTO(userService.getByUuid(uuid).getUuid());
    }
}
