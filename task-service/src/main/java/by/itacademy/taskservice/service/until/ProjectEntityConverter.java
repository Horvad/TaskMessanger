package by.itacademy.taskservice.service.until;

import by.itacademy.taskservice.core.dto.project.ProjectCreateDTO;
import by.itacademy.taskservice.repository.entity.ProjectEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class ProjectEntityConverter implements Converter<ProjectCreateDTO, ProjectEntity> {
    @Override
    public ProjectEntity convert(ProjectCreateDTO source) {
        return new ProjectEntity(
                source.getName(),
                source.getDescription(),
                source.getStatus()
        );
    }
}
