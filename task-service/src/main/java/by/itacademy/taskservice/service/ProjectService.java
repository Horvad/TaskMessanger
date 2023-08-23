package by.itacademy.taskservice.service;

import by.itacademy.taskservice.core.dto.PageDTO;
import by.itacademy.taskservice.core.dto.other.StaffDTO;
import by.itacademy.taskservice.core.dto.project.ProjectCreateDTO;
import by.itacademy.taskservice.core.dto.project.ProjectViewDTO;
import by.itacademy.taskservice.core.dto.task.ProjectForTaskDTO;
import by.itacademy.taskservice.core.enums.ProjectStatus;
import by.itacademy.taskservice.core.exception.IncorrectDataException;
import by.itacademy.taskservice.core.exception.NotFoundException;
import by.itacademy.taskservice.repository.IProjectRepository;
import by.itacademy.taskservice.repository.entity.ManagerEntity;
import by.itacademy.taskservice.repository.entity.ProjectEntity;
import by.itacademy.taskservice.repository.entity.StaffEntity;
import by.itacademy.taskservice.service.user.api.IManagerService;
import by.itacademy.taskservice.service.api.IProjectService;
import by.itacademy.taskservice.service.user.api.IStaffService;
import by.itacademy.taskservice.service.until.ProjectDTOConverter;
import by.itacademy.taskservice.service.until.ProjectEntityConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectService implements IProjectService {
    private final IProjectRepository projectRepository;
    private final IManagerService managerService;
    private final IStaffService staffService;
    private final ProjectDTOConverter dtoConverter;
    private final ProjectEntityConverter entityConverter;

    public ProjectService(IProjectRepository projectRepository,
                          IManagerService managerService,
                          IStaffService staffService,
                          ProjectDTOConverter dtoConverter,
                          ProjectEntityConverter entityConverter) {
        this.projectRepository = projectRepository;
        this.managerService = managerService;
        this.staffService = staffService;
        this.dtoConverter = dtoConverter;
        this.entityConverter = entityConverter;
    }

    @Override
    public void save(ProjectCreateDTO projectCreateDTO) {
        ProjectEntity projectEntity = entityConverter.convert(projectCreateDTO);
        projectEntity.setId(UUID.randomUUID());
        List<StaffEntity> staffEntities = new ArrayList<>();
        for(StaffDTO staffDTO : projectCreateDTO.getStaff()){
            staffEntities.add(staffService.get(staffDTO.getUuid()));
        }
        projectEntity.setStaff(staffEntities);
        projectEntity.setManager(managerService.get(projectCreateDTO.getManager().getUuid()));
        projectRepository.save(projectEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public PageDTO getPage(int page, int size, boolean archived, String userRole, UUID uuid) {
        if(page<0||size<0){
            throw new IncorrectDataException("Не правлильные номер страницы или размер");
        }
        int i = 0;
        Pageable pageable = PageRequest.of(page, size);
        Page<ProjectEntity> projects;
        if(userRole.equals("ADMIN")){
        if(archived){
                 projects = projectRepository.findAll(pageable);
            } else {
                 projects = projectRepository.findAllByStatus(pageable, ProjectStatus.ACTIVE);
            }
        } else {
            ManagerEntity manager = managerService.get(uuid);
            i = 2;
            if (manager == null) {
                manager = new ManagerEntity();
            }
            StaffEntity staff = staffService.get(uuid);
            if (staff == null) {
                staff = new StaffEntity();
            }
            if(archived) {
                projects = projectRepository.findAllByManagerOrStaff(pageable, manager, staff);
            } else {
                projects = projectRepository.findAllByStatusAndByManagerOrStaff(pageable,ProjectStatus.ACTIVE,manager,staff);
            }
        }
        List<ProjectViewDTO> context = projects
                .getContent()
                .stream()
                .map(s->dtoConverter.convert(s))
                .collect(Collectors.toList());
        return new PageDTO(
                projects.getNumber(),
                projects.getSize(),
                projects.getTotalPages(),
                projects.getTotalElements()-i,
                projects.isFirst(),
                projects.getNumberOfElements()-i,
                projects.isLast(),
                context
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectViewDTO get(UUID uuid, String userRole, String userUuid) {
        Optional<ProjectEntity> project = projectRepository.findById(uuid);
        ProjectViewDTO viewDTO = dtoConverter.convert(
                project.orElseThrow(()->new NotFoundException("Данного проекта нет"))
        );
        System.out.println(userRole);
        if(!userRole.equals("ADMIN")){
            for(StaffEntity staff : project.get().getStaff()){
                if(staff.getUuid().toString().equals(userUuid)){
                    return viewDTO;
                }
            }
            if(project.get().getManager().getUuid().toString().equals(userUuid)){
                return viewDTO;
            }
            throw new IncorrectDataException("Вы не учавствуете в этом проекте");
        }
        return viewDTO;
    }

    @Override
//    @Transactional
    public void update(UUID uuid, LocalDateTime dtUpdate, ProjectCreateDTO updateDTO) {
        Optional<ProjectEntity> oldProject = projectRepository.findById(uuid);
        if(!oldProject
                .orElseThrow(()->new NotFoundException("Данного проекта нет"))
                .getDtUpdate()
                .isEqual(dtUpdate)){
            throw new NotFoundException("Не правильная версия");
        }
        ProjectEntity editProject = entityConverter.convert(updateDTO);
        editProject.setId(oldProject.get().getId());
        editProject.setDtUpdate(oldProject.get().getDtUpdate());
        editProject.setDtCreate(oldProject.get().getDtCreate());
        editProject.setManager(managerService.get(updateDTO.getManager().getUuid()));
        List<StaffEntity> staffEntities = new ArrayList<>();
        for(StaffDTO staffDTO : updateDTO.getStaff()){
            staffEntities.add(staffService.get(staffDTO.getUuid()));
        }
        editProject.setStaff(staffEntities);
        projectRepository.save(editProject);

        String sss = "";
    }

    @Override
    public boolean exist(UUID uuid) {
        return projectRepository.existsById(uuid);
    }

    @Override
    public ProjectEntity getEntity(UUID uuid) {
        return projectRepository.findById(uuid).orElseThrow(
                ()->new NotFoundException("Данного проекта не существует")
        );
    }

    @Override
    public List<ProjectEntity> findByStaff(UUID staff) {
        StaffEntity staffEntity = staffService.get(staff);
        return projectRepository.findAllByStaff(staffEntity);
    }

    @Override
    public List<ProjectEntity> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public List<ProjectEntity> findById(List<ProjectForTaskDTO> project) {
        List<UUID> uuids = new ArrayList<>();
        for(ProjectForTaskDTO dto : project){
            uuids.add(dto.getUuid());
        }
        return projectRepository.findAllByIdIn(uuids);
    }

    @Override
    public List<ProjectEntity> findAllByProjectInOrImplementerIn(List<ProjectEntity> projectEntities) {
        return null;
    }

    @Override
    public List<ProjectEntity> findAllByUuid(UUID uuid) {
        ManagerEntity manager = managerService.get(uuid);
        if(manager==null){
            manager = new ManagerEntity();
        }
        StaffEntity staff = staffService.get(uuid);
        if(staff==null){
            staff = new StaffEntity();
        }
        return projectRepository.findAllByManagerOrStaff(manager,staff);
    }
}
