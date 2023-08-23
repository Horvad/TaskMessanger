package com.example.reportservice.service.util.convertors;

import com.example.reportservice.core.DTO.ReportParamsDTO;
import com.example.reportservice.core.enums.ReportType;
import com.example.reportservice.repository.entity.ReportEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class EntityConvertor implements Converter<ReportParamsDTO, ReportEntity> {
    @Override
    public ReportEntity convert(ReportParamsDTO source) {
        return new ReportEntity(
                UUID.fromString(source.getUser()),
                source.getFrom(),
                source.getTo()
        );
    }

    public String createDescription(ReportType reportType,
                                    LocalDate dateFrom,
                                    LocalDate dateTo){
        String description = "";
        switch (reportType){
            case JOURNAL_AUDIT -> {
                description = "Журнал аудита за:"+dateFrom.toString()+" - "+dateTo.toString();
            }
        }
        return description;
    }
}
