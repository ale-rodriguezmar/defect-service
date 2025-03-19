package com.safra.defect_service.service;

import com.safra.defect_service.dto.ReportDTO;
import com.safra.defect_service.dto.ReportInfoDTO;
import com.safra.defect_service.entity.Provider;
import com.safra.defect_service.entity.Report;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReportService {

    List<Report> findAll();

    Report save(Report report,Long areaId,Long itemId,Long causeId,Long userId,Long responsibleId,List<MultipartFile> files) throws IOException;

    ReportInfoDTO reportInfo(Long reportId);

    Report changeStatus( Long reportId,  int status);

}
