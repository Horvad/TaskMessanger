package by.itacademy.taskservice.service.api;

import by.itacademy.taskservice.core.dto.PageDTO;
import by.itacademy.taskservice.core.dto.other.FilterDTO;
import by.itacademy.taskservice.core.dto.other.ImplementerDTO;
import by.itacademy.taskservice.core.dto.task.ProjectForTaskDTO;
import by.itacademy.taskservice.core.dto.task.TaskCreateDTO;
import by.itacademy.taskservice.core.dto.task.TaskViewDTO;
import by.itacademy.taskservice.core.enums.TaskStatus;
import by.itacademy.taskservice.repository.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ITaskService {
    void save(TaskCreateDTO taskCreateDTO);
    PageDTO getPage(int page, int size, FilterDTO filterDTO);
    TaskViewDTO get(UUID uuid);
    void update(UUID uuid, LocalDateTime dtUpdate, TaskCreateDTO taskCreateDTO);
    void updateStatus(UUID uuid, LocalDateTime dtUpdate, TaskStatus status);
}
