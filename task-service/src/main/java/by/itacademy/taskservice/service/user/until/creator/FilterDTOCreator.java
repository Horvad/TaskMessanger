package by.itacademy.taskservice.service.user.until.creator;

import by.itacademy.taskservice.core.dto.other.FilterDTO;
import by.itacademy.taskservice.core.dto.other.ImplementerDTO;
import by.itacademy.taskservice.core.dto.task.ProjectForTaskDTO;
import by.itacademy.taskservice.core.enums.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FilterDTOCreator {
    public FilterDTO createFilterDTO(List<UUID> implementer, List<UUID> projects, List<TaskStatus> statuses){
        FilterDTO filterDTO = new FilterDTO();
        if(implementer==null||implementer.size()==0){
            filterDTO.setImplementerDTOS(null);
        } else {
            ArrayList<ImplementerDTO> implementerDTOS = new ArrayList<>();
            for(UUID uuid : implementer){
                implementerDTOS.add(new ImplementerDTO(uuid));
            }
            filterDTO.setImplementerDTOS(implementerDTOS);
        }
        if(projects==null||projects.size()==0){
            filterDTO.setProject(null);
        } else {
            ArrayList<ProjectForTaskDTO> projectDTO = new ArrayList<>();
            for(UUID uuid : projects){
                projectDTO.add(new ProjectForTaskDTO(uuid));
            }
            filterDTO.setProject(projectDTO);
        }
        if(statuses==null||statuses.size()==0){
            filterDTO.setUserRole(null);
        } else {
            List<TaskStatus> statusList = new ArrayList<>();
            for(TaskStatus status : statuses){
                statusList.add(status);
            }
            filterDTO.setStatusTask(statusList);
        }
        return filterDTO;
    }
}
