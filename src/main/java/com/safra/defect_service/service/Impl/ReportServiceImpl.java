package com.safra.defect_service.service.Impl;

import com.safra.defect_service.entity.Report;
import com.safra.defect_service.entity.enums.ReportStatus;
import com.safra.defect_service.repository.*;
import com.safra.defect_service.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public Report save(Report report, Long areaId, Long itemId, Long causeId, Long userId, Long responsibleId) {

        report.setArea(areaRepository.findById(areaId).orElseThrow());
        report.setItem(itemId != null ? itemRepository.findById(itemId).orElse(null) : null);
        report.setCause(causeRepository.findById(causeId).orElseThrow());
        report.setUser(userRepository.findById(userId).orElseThrow());
        report.setResponsible(areaRepository.findById(responsibleId).orElseThrow());
        report.setEvidence(null);
        report.setStatus(ReportStatus.PENDING);

        return reportRepository.save(report);
    }
}
