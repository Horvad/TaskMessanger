package by.itacademy.taskservice.service.user;

import by.itacademy.taskservice.core.dto.other.ImplementerDTO;
import by.itacademy.taskservice.core.exception.NotFoundException;
import by.itacademy.taskservice.repository.IImplementerRepository;
import by.itacademy.taskservice.repository.entity.ImplementerEntity;
import by.itacademy.taskservice.service.user.api.IImplementerService;
import by.itacademy.taskservice.service.user.api.IUserService;
import by.itacademy.taskservice.service.user.until.ImplementerEntityConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImplementerService implements IImplementerService {
    private final IUserService userService;
    private final IImplementerRepository implementerRepository;
    private final ImplementerEntityConverter implementerEntityConverter;

    public ImplementerService(IUserService userService,
                              IImplementerRepository implementerRepository,
                              ImplementerEntityConverter implementerEntityConverter) {
        this.userService = userService;
        this.implementerRepository = implementerRepository;
        this.implementerEntityConverter = implementerEntityConverter;
    }

    @Override
    @Transactional
    public ImplementerEntity get(UUID uuid) {
        Optional<ImplementerEntity> entity = implementerRepository.findById(uuid);
        if(entity.isEmpty()){
            ImplementerEntity implementer  = implementerEntityConverter
                    .convert(userService.getImplementers(uuid));
            implementerRepository.save(implementer);
            return implementer;
        }
        return entity.get();
    }

    @Override
    public List<ImplementerEntity> getAll() {
        return implementerRepository.findAll();
    }

    @Override
    public List<ImplementerEntity> getAllByList(List<ImplementerDTO> uuids) {
        List<UUID> uuidsL = new ArrayList<>();
        for(ImplementerDTO implementerDTO : uuids){
            uuidsL.add(implementerDTO.getUuid());
        }
        return implementerRepository.findAllById(uuidsL);
    }
}
