package by.itacademy.taskservice.service.until;

import by.itacademy.taskservice.core.dto.task.ProjectForTaskDTO;
import by.itacademy.taskservice.core.dto.task.TaskViewDTO;
import by.itacademy.taskservice.repository.entity.TaskEntity;
import by.itacademy.taskservice.service.user.until.ImplementerDTOConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class TaskDTOConverter implements Converter<TaskEntity, TaskViewDTO> {
    private final ImplementerDTOConverter implementerDTOConverter;

    public TaskDTOConverter(ImplementerDTOConverter implementerDTOConverter) {
        this.implementerDTOConverter = implementerDTOConverter;
    }

    @Override
    public TaskViewDTO convert(TaskEntity source) {
        return new TaskViewDTO(
                source.getUuid(),
                source.getDtCreate(),
                source.getDtUpdate(),
                new ProjectForTaskDTO(source.getProject().getId()),
                source.getTitle(),
                source.getDescription(),
                source.getStatus(),
                implementerDTOConverter.convert(source.getImplementer())
        );
    }
}
