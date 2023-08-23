package by.itacademy.taskservice.service.user;

import by.itacademy.taskservice.core.dto.other.ManagerDTO;
import by.itacademy.taskservice.core.dto.other.StaffDTO;
import by.itacademy.taskservice.core.exception.NotFoundException;
import by.itacademy.taskservice.repository.IManagerRepository;
import by.itacademy.taskservice.repository.IStaffRepository;
import by.itacademy.taskservice.repository.entity.ManagerEntity;
import by.itacademy.taskservice.repository.entity.StaffEntity;
import by.itacademy.taskservice.service.user.api.IStaffService;
import by.itacademy.taskservice.service.user.api.IUserService;
import by.itacademy.taskservice.service.user.until.ManagerEntityConverter;
import by.itacademy.taskservice.service.user.until.StaffEntityConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StaffService implements IStaffService {
    private final IUserService userService;
    private final IStaffRepository staffRepository;
    private final StaffEntityConverter staffEntityConverter;

    public StaffService(IUserService userService,
                        IStaffRepository staffRepository,
                        StaffEntityConverter staffEntityConverter) {
        this.userService = userService;
        this.staffRepository = staffRepository;
        this.staffEntityConverter = staffEntityConverter;
    }

    @Override
    @Transactional
    public StaffEntity get(UUID uuid) {
        Optional<StaffEntity> entity = staffRepository.findById(uuid);
        if(entity.isEmpty()){
            StaffEntity staff = new StaffEntity();
            staff = staffEntityConverter.convert(userService.getStaff(uuid));
            staffRepository.save(staff);
            return staff;
        }
        return entity.get();
    }

    @Override
    public List<StaffEntity> findAll() {
        return staffRepository.findAll();
    }
}
