package by.itacademy.taskservice.service.user;

import by.itacademy.taskservice.core.dto.other.ImplementerDTO;
import by.itacademy.taskservice.core.dto.other.ManagerDTO;
import by.itacademy.taskservice.core.exception.NotFoundException;
import by.itacademy.taskservice.repository.IManagerRepository;
import by.itacademy.taskservice.repository.entity.ImplementerEntity;
import by.itacademy.taskservice.repository.entity.ManagerEntity;
import by.itacademy.taskservice.service.user.api.IManagerService;
import by.itacademy.taskservice.service.user.api.IUserService;
import by.itacademy.taskservice.service.user.until.ManagerEntityConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ManagerService implements IManagerService {
    private final IUserService userService;
    private final IManagerRepository managerRepository;
    private final ManagerEntityConverter managerEntityConverter;

    public ManagerService(IUserService userService, IManagerRepository managerRepository, ManagerEntityConverter managerEntityConverter) {
        this.userService = userService;
        this.managerRepository = managerRepository;
        this.managerEntityConverter = managerEntityConverter;
    }

    @Override
    @Transactional
    public ManagerEntity get(UUID uuid) {
        Optional<ManagerEntity> entity = managerRepository.findById(uuid);
        if(entity.isEmpty()){
            ManagerEntity manager = new ManagerEntity();
            manager = managerEntityConverter.convert(userService.getManager(uuid));
            managerRepository.save(manager);
            return manager;
        }
        return entity.get();
    }

    @Override
    public List<ManagerEntity> findAll() {
        return managerRepository.findAll();
    }
}
