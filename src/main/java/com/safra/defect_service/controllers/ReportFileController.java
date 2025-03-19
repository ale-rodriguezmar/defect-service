package com.safra.defect_service.controllers;


import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.safra.defect_service.service.FileService;
import com.safra.defect_service.service.ReportFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;

@RestController
@RequestMapping("/report-file")
public class ReportFileController {


    @Autowired
    private ReportFileService  reportFileService;


    @GetMapping("/generate-download/{filename}")
    public ResponseEntity<Resource> generateAndDownloadPdf(@PathVariable String filename) {
        try {

            File file = reportFileService.GenerateReportPDF(filename);

            Resource resource = new FileSystemResource(file);

            // Configure the response to force download
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(file.length())
                    .body(resource);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
