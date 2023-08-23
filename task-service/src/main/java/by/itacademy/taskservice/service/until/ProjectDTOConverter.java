package by.itacademy.taskservice.service.until;

import by.itacademy.taskservice.core.dto.other.StaffDTO;
import by.itacademy.taskservice.core.dto.project.ProjectViewDTO;
import by.itacademy.taskservice.repository.entity.ProjectEntity;
import by.itacademy.taskservice.repository.entity.StaffEntity;
import by.itacademy.taskservice.service.user.until.ManagerDTOConverter;
import by.itacademy.taskservice.service.user.until.StaffDTOConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectDTOConverter implements Converter<ProjectEntity, ProjectViewDTO> {
    private final ManagerDTOConverter managerConverter;
    private final StaffDTOConverter staffConverter;

    public ProjectDTOConverter(ManagerDTOConverter managerConverter,
                               StaffDTOConverter staffConverter) {
        this.managerConverter = managerConverter;
        this.staffConverter = staffConverter;
    }

    @Override
    public ProjectViewDTO convert(ProjectEntity source) {
        List<StaffDTO> staffList = new ArrayList<>();
        for(StaffEntity staff : source.getStaff()){
            staffList.add(staffConverter.convert(staff));
        }
        return new ProjectViewDTO(
                source.getId(),
                source.getDtCreate(),
                source.getDtUpdate(),
                source.getName(),
                source.getDescription(),
                managerConverter.convert(source.getManager()),
                staffList,
                source.getStatus()
        );
    }
}
