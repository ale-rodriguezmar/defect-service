package com.safra.defect_service.controllers;


import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
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

    private static final String PDF_DIRECTORY = "src/main/resources/pdf/";

    @GetMapping("/generate-download/{filename}")
    public ResponseEntity<Resource> generateAndDownloadPdf(@PathVariable String filename) {
        try {
            // Verify if directory exists, if not, create it
            File directory = new File(PDF_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Dynamically generated HTML content
            String htmlContent = """
            <!DOCTYPE html>
            <html lang="es">
            <head>
                <meta charset="UTF-8">
                <title>Reporte PDF</title>
                <style>
                    body { font-family: Arial, sans-serif; margin: 20px; }
                    h1 { color: navy; }
                    table { width: 100%; border-collapse: collapse; margin-top: 20px; }
                    th, td { border: 1px solid black; padding: 8px; text-align: left; }
                    th { background-color: #f2f2f2; }
                </style>
            </head>
            <body>
                <h1>Reporte Generado :filename</h1>
                <p>Este es un reporte generado dinámicamente en formato PDF.</p>
                <table>
                    <tr>
                        <th>ID</th><th>Nombre</th><th>Valor</th>
                    </tr>
                    <tr>
                        <td>1</td><td>Ejemplo</td><td>100</td>
                    </tr>
                    <tr>
                        <td>2</td><td>Prueba</td><td>200</td>
                    </tr>
                </table>
            </body>
            </html>
            """.replace(":filename",filename);

            // Path for the generated PDF file
            String pdfPath = PDF_DIRECTORY + File.separator + filename;

            try (FileOutputStream fileOutputStream = new FileOutputStream(pdfPath)) {
                // Convert HTML to PDF and save on the server
                ConverterProperties properties = new ConverterProperties();
                HtmlConverter.convertToPdf(htmlContent, fileOutputStream, properties);
            }

            // Create the resource from the generated file
            File file = new File(pdfPath);
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
