package com.example.reportservice.repository.api;

import com.example.reportservice.repository.entity.ReportEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IReportRepository extends CrudRepository<ReportEntity, UUID> {
    Page<ReportEntity> findAll(Pageable pageable);
}
