package com.example.reportservice.service.util.convertors;

import com.example.reportservice.core.DTO.ReportParamsDTO;
import com.example.reportservice.core.DTO.ReportViewDTO;
import com.example.reportservice.repository.entity.ReportEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class DTOConvertor implements Converter<ReportEntity, ReportViewDTO> {
    @Override
    public ReportViewDTO convert(ReportEntity source) {
        return new ReportViewDTO(
                source.getUuid(),
                source.getDtCreate(),
                source.getDtUpdate(),
                source.getType(),
                source.getDescription(),
                new ReportParamsDTO(source.getUuidCreate().toString(),
                        source.getDateFrom(),
                        source.getDateTo())
        );
    }
}
