package com.safra.defect_service.controllers;

import com.safra.defect_service.dto.ReportDTO;
import com.safra.defect_service.dto.ReportInfoDTO;
import com.safra.defect_service.entity.Report;
import com.safra.defect_service.entity.Role;
import com.safra.defect_service.service.FileService;
import com.safra.defect_service.service.ReportService;
import com.safra.defect_service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {


    @Autowired
    private ReportService reportService;

    @Autowired
    private FileService fileService;

    @GetMapping("/all")
    public ResponseEntity<List<ReportDTO>> All() {
        return ResponseEntity.ok(reportService.findAll()
                .stream()
                .map(Report::toDto)
                .toList());
    }

    @PostMapping("/save")
    public ResponseEntity<ReportDTO> save(
            @RequestPart("reportDTO") ReportDTO reportDTO,
            @RequestParam("files") List<MultipartFile> files) throws IOException {

        System.out.println(reportDTO.toString());
        System.out.println("Cantidad de archivos recibidos: " + files.size());

        return ResponseEntity.ok(reportService
                .save(
                        reportDTO.toEntity(),
                        reportDTO.getAreaId(),
                        reportDTO.getItemId(),
                        reportDTO.getCauseId(),
                        reportDTO.getUserId(),
                        reportDTO.getResponsibleIdArea(),
                        files)
                .toDto());
    }


    @GetMapping("/report-info")
    public ResponseEntity<ReportInfoDTO> reportInfo(@RequestParam Long reportId) {
        return ResponseEntity.ok(reportService.reportInfo(reportId));
    }

    @GetMapping("/change-status")
    public ResponseEntity<ReportDTO> changeStatusOrder(@RequestParam Long reportId,@RequestParam int status) {
        return ResponseEntity.ok(reportService.changeStatus(reportId,status).toDto());
    }

    @PostMapping("uploadFiles")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> files) throws IOException {
        return ResponseEntity.ok(fileService.uploadFiles(files));
    }
}
