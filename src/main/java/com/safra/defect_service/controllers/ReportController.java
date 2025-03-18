package com.safra.defect_service.controllers;

import com.safra.defect_service.dto.ReportDTO;
import com.safra.defect_service.entity.Report;
import com.safra.defect_service.entity.Role;
import com.safra.defect_service.service.ReportService;
import com.safra.defect_service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {


    @Autowired
    ReportService reportService;

    @GetMapping("/all")
    public ResponseEntity<List<ReportDTO>> All() {
        return ResponseEntity.ok(reportService.findAll()
                .stream()
                .map(Report::toDto)
                .toList());
    }

    @PostMapping("/save")
    public ResponseEntity<ReportDTO> save(@RequestBody ReportDTO reportDTO) {


        System.out.println(reportDTO.toString());
        return ResponseEntity.ok(reportService
                .save(
                        reportDTO.toEntity(),
                        reportDTO.getAreaId(),
                        reportDTO.getItemId(),
                        reportDTO.getCauseId(),
                        reportDTO.getUserId(),
                        reportDTO.getResponsibleIdArea())
                .toDto());
    }
}
