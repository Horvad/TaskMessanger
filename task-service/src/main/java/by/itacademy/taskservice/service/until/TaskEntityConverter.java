package by.itacademy.taskservice.service.until;

import by.itacademy.taskservice.core.dto.task.TaskCreateDTO;
import by.itacademy.taskservice.repository.entity.TaskEntity;
import by.itacademy.taskservice.service.api.IProjectService;
import by.itacademy.taskservice.service.user.api.IImplementerService;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class TaskEntityConverter implements Converter<TaskCreateDTO, TaskEntity> {
    private final IImplementerService iImplementerService;
    private final IProjectService projectService;

    public TaskEntityConverter(IImplementerService iImplementerService, IProjectService projectService) {
        this.iImplementerService = iImplementerService;
        this.projectService = projectService;
    }

    @Override
    public TaskEntity convert(TaskCreateDTO source) {
        TaskEntity task = new TaskEntity();
        task.setTitle(source.getTitle());
        task.setDescription(source.getDescription());
        task.setStatus(source.getStatus());
        return task;
    }
}
