package com.safra.defect_service.service.Impl;

import com.safra.defect_service.dto.ReportDTO;
import com.safra.defect_service.dto.ReportInfoDTO;
import com.safra.defect_service.entity.Evidence;
import com.safra.defect_service.entity.Report;
import com.safra.defect_service.entity.Solution;
import com.safra.defect_service.entity.enums.ReportStatus;
import com.safra.defect_service.repository.*;
import com.safra.defect_service.service.FileService;
import com.safra.defect_service.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CauseRepository causeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileService fileService;
    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public Report save(Report report, Long areaId, Long itemId, Long causeId, Long userId, Long responsibleId,List<MultipartFile> files) throws IOException {

        report.setArea(areaRepository.findById(areaId).orElseThrow());
        report.setItem(itemId != null ? itemRepository.findById(itemId).orElse(null) : null);
        report.setCause(causeRepository.findById(causeId).orElseThrow());
        report.setUser(userRepository.findById(userId).orElseThrow());
        report.setResponsible(areaRepository.findById(responsibleId).orElseThrow());
        report.setEvidence(null);
        report.setStatus(ReportStatus.PENDING);
        report.setCreateDate(LocalDateTime.now(ZoneId.of("America/Bogota")));
        // insert evidence
        var listEvidenceUrl = fileService.uploadFiles(files);
        Evidence evidence = new Evidence();
        evidence.setUrls(listEvidenceUrl);
        evidence.setReport(report);
        if(report.getEvidence() == null){
            report.setEvidence(List.of(evidence));
        } else{
            report.getEvidence().add(evidence);
        }

        return reportRepository.save(report);
    }

    @Override
    public ReportInfoDTO reportInfo(Long reportId) {

        var report = reportRepository.findById(reportId).orElseThrow();
        ReportInfoDTO reportInfoDTO = new ReportInfoDTO();

        reportInfoDTO.setReportId(reportId);
        reportInfoDTO.setCreateDate(report.getCreateDate());

        if (report.getUser() != null) {
            reportInfoDTO.setIdUser(report.getUser().getId());
            reportInfoDTO.setUserName(report.getUser().getUsername());
        }

        reportInfoDTO.setStatus(report.getStatus());

        if (report.getArea() != null) {
            reportInfoDTO.setAreaName(report.getArea().getName());
        }

        if (report.getItem() != null) {
            reportInfoDTO.setItemCode(report.getItem().getItemCode());
            reportInfoDTO.setItemName(report.getItem().getName());
        }

        if (report.getCause() != null) {
            reportInfoDTO.setCauseName(report.getCause().getName());
            if (report.getCause().getPossibleSolutions() != null) {
                reportInfoDTO.setPossibleSolutions(report.getCause().getPossibleSolutions()
                        .stream()
                        .map(Solution::getDescription)
                        .collect(Collectors.toList()));
            }
        }

        if (report.getResponsible() != null) {
            reportInfoDTO.setNameResponsible(report.getResponsible().getName());
        }

        reportInfoDTO.setComment(report.getComment());
        reportInfoDTO.setSalesOrManufacturingOrder(report.getSalesOrManufacturingOrder());

        return reportInfoDTO;
    }

    @Override
    public Report changeStatus(Long reportId, int status) {
        var report = reportRepository.findById(reportId).orElseThrow();
        ReportStatus newStatus = ReportStatus.values()[status];
        report.setStatus(newStatus);
        return reportRepository.save(report);
    }
}
