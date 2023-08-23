package by.itacademy.taskservice.service;

import by.itacademy.taskservice.core.dto.PageDTO;
import by.itacademy.taskservice.core.dto.other.FilterDTO;
import by.itacademy.taskservice.core.dto.other.ImplementerDTO;
import by.itacademy.taskservice.core.dto.other.StaffDTO;
import by.itacademy.taskservice.core.dto.project.ProjectViewDTO;
import by.itacademy.taskservice.core.dto.task.ProjectForTaskDTO;
import by.itacademy.taskservice.core.dto.task.TaskCreateDTO;
import by.itacademy.taskservice.core.dto.task.TaskViewDTO;
import by.itacademy.taskservice.core.enums.TaskStatus;
import by.itacademy.taskservice.core.exception.IncorrectDataException;
import by.itacademy.taskservice.core.exception.NotFoundException;
import by.itacademy.taskservice.repository.ITaskRepository;
import by.itacademy.taskservice.repository.specifications.TaskSpecification;
import by.itacademy.taskservice.repository.entity.*;
import by.itacademy.taskservice.service.api.IProjectService;
import by.itacademy.taskservice.service.api.ITaskService;
import by.itacademy.taskservice.service.until.TaskDTOConverter;
import by.itacademy.taskservice.service.until.TaskEntityConverter;
import by.itacademy.taskservice.service.user.api.IImplementerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService implements ITaskService {
    private final ITaskRepository taskRepository;
    private final IProjectService projectService;
    private final IImplementerService implementerService;
    private final TaskEntityConverter entityConverter;
    private final TaskDTOConverter dtoConverter;

    public TaskService(ITaskRepository taskRepository, IProjectService projectService, IImplementerService implementerService, TaskEntityConverter entityConverter, TaskDTOConverter dtoConverter) {
        this.taskRepository = taskRepository;
        this.projectService = projectService;
        this.implementerService = implementerService;
        this.entityConverter = entityConverter;
        this.dtoConverter = dtoConverter;
    }

    @Override
//    @Transactional
    public void save(TaskCreateDTO taskCreateDTO) {
        TaskEntity task = entityConverter.convert(taskCreateDTO);
        ImplementerEntity implementer = implementerService.get(taskCreateDTO.getImplementer().getUuid());
        task.setUuid(UUID.randomUUID());
        task.setImplementer(implementer);
        task.setProject(projectService.getEntity(taskCreateDTO.getProject().getUuid()));
        taskRepository.saveAndFlush(task);
    }

    @Override
    @Transactional(readOnly = true)
    public TaskViewDTO get(UUID uuid) {
        Optional<TaskEntity> task = taskRepository.findById(uuid);
        return dtoConverter.convert(task.orElseThrow(()->new NotFoundException("Данной задачи не существует")));
    }

    @Override
    @Transactional
    public void update(UUID uuid, LocalDateTime dtUpdate, TaskCreateDTO taskCreateDTO) {
        Optional<TaskEntity> task = taskRepository.findById(uuid);
        if(task.orElseThrow(()->new NotFoundException("Данной задачи не существует"))
                .getUuid()
                .equals(uuid)){
            if(task.get().getDtUpdate().isEqual(dtUpdate)){
                TaskEntity taskEdit = entityConverter.convert(taskCreateDTO);
                taskEdit.setUuid(task.get().getUuid());
                taskEdit.setDtUpdate(task.get().getDtUpdate());
                taskEdit.setDtCreate(task.get().getDtCreate());
                ImplementerEntity implementer = implementerService.get(taskCreateDTO.getImplementer().getUuid());
                taskEdit.setImplementer(implementer);
                taskEdit.setProject(projectService.getEntity(taskCreateDTO.getProject().getUuid()));
                taskRepository.save(taskEdit);
            } else {
                throw new IncorrectDataException("Задача была изменена");
            }
        }
    }

    @Override
    @Transactional
    public void updateStatus(UUID uuid, LocalDateTime dtUpdate, TaskStatus status) {
        Optional<TaskEntity> task = taskRepository.findById(uuid);
        if(task.orElseThrow(()->new NotFoundException("Данной задачи не существует"))
                .getUuid()
                .equals(uuid)){
            if(task.get().getDtUpdate().isEqual(dtUpdate)){
                TaskEntity taskEdit = task.get();
                taskEdit.setStatus(status);
                taskRepository.save(taskEdit);
            } else {
                throw new IncorrectDataException("Задача была изменена");
            }
        }
    }

    @Transactional(readOnly = true)
    @Override
    public PageDTO getPage(int page, int size, FilterDTO filterDTO) {
        if(page < 0 || size < 0){
            throw new IncorrectDataException("Не верно введено станица или размер");
        }
        Page<TaskEntity> taskEntities = getFilterTask(filterDTO,size,page);
        List<TaskViewDTO> context = taskEntities
                .getContent()
                .stream()
                .map(s->dtoConverter.convert(s))
                .collect(Collectors.toList());
        return new PageDTO(
                taskEntities.getNumber(),
                taskEntities.getSize(),
                taskEntities.getTotalPages(),
                taskEntities.getTotalElements(),
                taskEntities.isFirst(),
                taskEntities.getNumberOfElements(),
                taskEntities.isLast(),
                context
        );
    }

    @Transactional(readOnly = true)
    public List<ProjectForTaskDTO> getFilteredProjectRefs(FilterDTO filterDTO) {
        if (filterDTO.getProject() == null){
            List<ProjectEntity> userProjects;
            if(filterDTO.getUserRole().equals("ADMIN")){
                userProjects = projectService.findAll();
            }
             userProjects = this.projectService.findByStaff(filterDTO.getUuid());
            return userProjects.stream().map(p -> new ProjectForTaskDTO(p.getId())).toList();
        }
        if(!filterDTO.getUserRole().equals("ADMIN")) {
            List<ProjectForTaskDTO> availableProjects = new ArrayList<>();
            for (ProjectForTaskDTO dto : filterDTO.getProject()) {
                ProjectEntity project = this.projectService.getEntity(dto.getUuid());
                Set<StaffDTO> staff = project.getStaff().stream().map(p->new StaffDTO(p.getUuid())).collect(Collectors.toSet());
                if (staff.contains(filterDTO.getUuid()) || project.getManager().equals(filterDTO.getUuid())) {
                    availableProjects.add(dto);
                }
            }
            return availableProjects;
        }
        return filterDTO.getProject();
    }

    private Page<TaskEntity> getFilterTask(FilterDTO dto, int size, int page){
        Pageable pageable = PageRequest.of(page, size);
        Page<TaskEntity> entities = null;
        if (dto.getUserRole().equals("ADMIN")) {
            if (dto.getProject() == null && dto.getImplementerDTOS() == null && dto.getStatusTask() == null) {
                entities = taskRepository.findAll(pageable);
                return entities;
            }
            if (dto.getProject() != null && dto.getImplementerDTOS() == null && dto.getStatusTask() == null) {
                List<ProjectEntity> projects = projectService.findById(dto.getProject());
                entities = taskRepository.findAllByProjectIn(pageable, projects);
                return entities;
            }
            if (dto.getProject() != null && dto.getImplementerDTOS() != null && dto.getStatusTask() == null) {
                List<ProjectEntity> projects = projectService.findById(dto.getProject());
                List<ImplementerEntity> implementers = implementerService.getAllByList(dto.getImplementerDTOS());
                entities = this.taskRepository.findAllByProjectInAndImplementerIn(pageable, projects, implementers);
                return entities;
            }
            if (dto.getProject() != null && dto.getImplementerDTOS() == null && dto.getStatusTask() != null) {
                List<ProjectEntity> projects = this.projectService.findById(dto.getProject());
                entities = this.taskRepository.findAllByProjectInAndStatusIn(
                        pageable,
                        projects,
                        dto.getStatusTask());
                return entities;
            }
            if (dto.getProject() == null && dto.getImplementerDTOS() != null && dto.getStatusTask() == null) {
                List<ImplementerEntity> implementerEntities = implementerService.getAllByList(dto.getImplementerDTOS());
                entities = this.taskRepository.findAllByImplementerIn(
                        pageable,
                        implementerEntities);
                return entities;
            }
            if (dto.getProject() == null && dto.getImplementerDTOS() != null && dto.getStatusTask() != null) {
                List<ImplementerEntity> implementerEntities = implementerService.getAllByList(dto.getImplementerDTOS());
                entities = this.taskRepository.findAllByImplementerInAndStatusIn(
                        pageable,
                        implementerEntities,
                        dto.getStatusTask());
                return entities;
            }
            if (dto.getProject() == null && dto.getImplementerDTOS() == null && dto.getStatusTask() != null) {
                entities = this.taskRepository.findAllByStatusIn(
                        pageable,
                        dto.getStatusTask());
                return entities;
            }
            if (dto.getProject() != null && dto.getImplementerDTOS() != null && dto.getStatusTask() != null){
                List<ProjectEntity> projects = projectService.findById(dto.getProject());
                List<ImplementerEntity> implementerEntities = implementerService.getAllByList(dto.getImplementerDTOS());
                entities = taskRepository.findAllByProjectInAndImplementerInAndStatusIn(
                        pageable,
                        projects,
                        implementerEntities,
                        dto.getStatusTask());
                return entities;
            }
        } else {
            if (dto.getProject() == null && dto.getImplementerDTOS() == null && dto.getStatusTask() == null) {
                List<ProjectEntity> projectEntities = projectService.findAllByUuid(dto.getUuid());
                entities = taskRepository.findAllByProjectIn(
                        pageable,
                        projectEntities);
                return entities;
            }
            if (dto.getProject() != null && dto.getImplementerDTOS() == null && dto.getStatusTask() == null) {
                entities = taskRepository.findAllByProjectIn(
                        pageable,
                        getProjects(dto.getProject(),dto.getUuid()));
                return entities;
            }
            if (dto.getProject() != null && dto.getImplementerDTOS() != null && dto.getStatusTask() == null) {
                List<ImplementerEntity> implementerEntities = implementerService.getAllByList(dto.getImplementerDTOS());
                entities = taskRepository.findAllByProjectInAndImplementerIn(
                        pageable,
                        getProjects(dto.getProject(),dto.getUuid()),
                        implementerEntities);
                return entities;
            }
            if (dto.getProject() != null && dto.getImplementerDTOS() == null && dto.getStatusTask() != null) {
                entities = this.taskRepository.findAllByProjectInAndStatusIn(
                        pageable,
                        getProjects(dto.getProject(),dto.getUuid()),
                        dto.getStatusTask());
                return entities;
            }
            if (dto.getProject() == null && dto.getImplementerDTOS() != null && dto.getStatusTask() == null) {
                List<ProjectEntity> projectEntities = projectService.findAllByUuid(dto.getUuid());
                List<ImplementerEntity> implementerEntities = implementerService.getAllByList(dto.getImplementerDTOS());
                entities = this.taskRepository.findAllByProjectInAndImplementerIn(
                        pageable,
                        projectEntities,
                        implementerEntities);
                return entities;
            }
            if (dto.getProject() == null && dto.getImplementerDTOS() != null && dto.getStatusTask() != null) {
                List<ProjectEntity> projectEntities = projectService.findAllByUuid(dto.getUuid());
                List<ImplementerEntity> implementerEntities = implementerService.getAllByList(dto.getImplementerDTOS());
                entities = this.taskRepository.findAllByProjectInAndImplementerInAndStatusIn(
                        pageable,
                        projectEntities,
                        implementerEntities,
                        dto.getStatusTask());
                return entities;
            }
            if (dto.getProject() == null && dto.getImplementerDTOS() == null && dto.getStatusTask() != null) {
                List<ProjectEntity> projectEntities = projectService.findAllByUuid(dto.getUuid());
                entities = this.taskRepository.findAllByProjectInAndStatusIn(
                        pageable,
                        projectEntities,
                        dto.getStatusTask());
                return entities;
            }
            if (dto.getProject() != null && dto.getImplementerDTOS() != null && dto.getStatusTask() != null) {
                List<ImplementerEntity> implementerEntities = implementerService.getAllByList(dto.getImplementerDTOS());
                entities = this.taskRepository.findAllByProjectInAndImplementerInAndStatusIn(
                        pageable,
                        getProjects(dto.getProject(),dto.getUuid()),
                        implementerEntities,
                        dto.getStatusTask());
                return entities;
            }
        }
        return entities;
    }

    private List<ProjectEntity> getProjects(List<ProjectForTaskDTO> projectDTOS, UUID userUUid){
        List<ProjectEntity> projectDTO = projectService.findById(projectDTOS);
        List<ProjectEntity> projectUser = projectService.findAllByUuid(userUUid);
        List<ProjectEntity> projectResult = new ArrayList<>();
        for(ProjectEntity project : projectDTO){
            for(ProjectEntity projectEntity : projectUser){
                if(project.equals(projectEntity)){
                    projectResult.add(projectEntity);
                    break;
                }
            }
        }
        return projectResult;
    }

}
