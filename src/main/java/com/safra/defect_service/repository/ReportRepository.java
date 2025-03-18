package com.safra.defect_service.repository;

import com.safra.defect_service.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Long> {
}
