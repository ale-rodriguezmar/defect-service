package com.safra.defect_service.service;

import com.safra.defect_service.entity.Provider;
import com.safra.defect_service.entity.Report;

import java.util.List;

public interface ReportService {

    List<Report> findAll();

    Report save(Report report,Long areaId,Long itemId,Long causeId,Long userId,Long responsibleId);


}
