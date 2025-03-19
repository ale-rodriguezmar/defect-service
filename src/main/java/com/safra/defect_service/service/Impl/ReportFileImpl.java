package com.safra.defect_service.service.Impl;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.safra.defect_service.repository.ReportRepository;
import com.safra.defect_service.service.ReportFileService;
import com.safra.defect_service.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ReportFileImpl implements ReportFileService {


    private static final String PDF_DIRECTORY = "src/main/resources/pdf/";


    @Autowired
    private ReportRepository reportRepository;


    @Override
    public File GenerateReportPDF(String reportId) throws FileNotFoundException {

        String htmlContent =    """
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Reporte de Defectos</title>
                    <style>
                        body {
                            font-family: Arial, Helvetica, sans-serif;
                            margin: 0;
                            padding: 0;
                            background-color: #f9f9f9;
                            color: #333;
                        }
                       \s
                        .document {
                            width: auto;
                            max-width: 21cm;
                            min-height: 29.7cm;
                            margin: 1cm auto;
                            background: white;
                            padding: 1.5cm;
                            box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
                            box-sizing: border-box;
                        }
                       \s
                        .header {
                            border-bottom: 2px solid #2c3e50;
                            padding-bottom: 20px;
                            margin-bottom: 30px;
                            position: relative;
                        }
                       \s
                        .logo {
                            position: absolute;
                            top: 0;
                            right: 0;
                            width: 80px;
                            height: 80px;
                            background-color: #ddd;
                            display: flex;
                            align-items: center;
                            justify-content: center;
                            border-radius: 5px;
                        }
                       \s
                        h1 {
                            font-size: 24px;
                            color: #2c3e50;
                            margin: 0 0 10px 0;
                            padding-right: 90px; /* Espacio para el logo */
                        }
                       \s
                        .reference {
                            font-size: 14px;
                            color: #7f8c8d;
                        }
                       \s
                        .content {
                            margin-bottom: 30px;
                            overflow-x: auto; /* Permite scroll horizontal si la tabla es muy ancha */
                        }
                       \s
                        table {
                            width: 100%;
                            border-collapse: collapse;
                            margin-top: 20px;
                        }
                       \s
                        th, td {
                            padding: 12px 15px;
                            text-align: left;
                            border-bottom: 1px solid #ddd;
                            word-wrap: break-word; /* Permite que el texto se rompa */
                        }
                       \s
                        th {
                            background-color: #f2f2f2;
                            font-weight: bold;
                            width: 30%;
                        }
                       \s
                        .severity {
                            display: inline-block;
                            padding: 5px 10px;
                            border-radius: 3px;
                            font-weight: bold;
                            text-align: center;
                        }
                       \s
                        .alta {
                            background-color: #e74c3c;
                            color: white;
                        }
                       \s
                        .media {
                            background-color: #f39c12;
                            color: white;
                        }
                       \s
                        .baja {
                            background-color: #3498db;
                            color: white;
                        }
                       \s
                        .status {
                            display: inline-block;
                            padding: 5px 10px;
                            border-radius: 3px;
                            font-weight: bold;
                        }
                       \s
                        .pendiente {
                            background-color: #f1c40f;
                            color: #333;
                        }
                       \s
                        .en-proceso {
                            background-color: #3498db;
                            color: white;
                        }
                       \s
                        .resuelto {
                            background-color: #2ecc71;
                            color: white;
                        }
                       \s
                        .footer {
                            margin-top: 50px;
                            font-size: 12px;
                            color: #7f8c8d;
                            text-align: center;
                            border-top: 1px solid #ddd;
                            padding-top: 20px;
                        }
                       \s
                        @media print {
                            body {
                                background: none;
                            }
                            .document {
                                width: 100%;
                                max-width: none;
                                margin: 0;
                                padding: 1cm;
                                box-shadow: none;
                            }
                            .no-print {
                                display: none;
                            }
                        }
                       \s
                        /* Estilos responsive para pantallas pequeñas */
                        @media screen and (max-width: 768px) {
                            .document {
                                padding: 1cm;
                                margin: 0.5cm;
                            }
                           \s
                            th, td {
                                padding: 8px;
                            }
                           \s
                            h1 {
                                font-size: 20px;
                            }
                           \s
                            .logo {
                                width: 60px;
                                height: 60px;
                            }
                        }
                    </style>
                </head>
                <body>
                    <div class="document">
                        <div class="header">
                            <div class="logo">LOGO</div>
                            <h1>REPORTE DE DEFECTOS</h1>
                            <div class="reference">
                                <strong>ID Reporte:</strong> :reportId <br>
                                <strong>Fecha:</strong> 19/03/2025<br>
                                <strong>Generado por:</strong> Sistema de Gestión de Defectos
                            </div>
                        </div>
                       \s
                        <div class="content">
                            <table>
                                <tr>
                                    <th>Nombre del Defecto</th>
                                    <td>Error en carga de datos</td>
                                </tr>
                                <tr>
                                    <th>Proyecto</th>
                                    <td>Sistema de Gestión de Inventario</td>
                                </tr>
                                <tr>
                                    <th>Módulo</th>
                                    <td>Visualización de Datos</td>
                                </tr>
                                <tr>
                                    <th>Descripción</th>
                                    <td>El sistema no carga los datos correctamente en la vista principal. Los registros aparecen duplicados o incompletos cuando se accede desde el panel de administración.</td>
                                </tr>
                                <tr>
                                    <th>Pasos para reproducir</th>
                                    <td>
                                        1. Iniciar sesión como administrador<br>
                                        2. Navegar al panel de control<br>
                                        3. Seleccionar "Vista de datos"<br>
                                        4. Verificar la carga incorrecta de información
                                    </td>
                                </tr>
                                <tr>
                                    <th>Severidad</th>
                                    <td><span class="severity alta">Alta</span></td>
                                </tr>
                                <tr>
                                    <th>Estado</th>
                                    <td><span class="status pendiente">Pendiente</span></td>
                                </tr>
                                <tr>
                                    <th>Reportado por</th>
                                    <td>Ana Martínez</td>
                                </tr>
                                <tr>
                                    <th>Asignado a</th>
                                    <td>Carlos Rodríguez</td>
                                </tr>
                                <tr>
                                    <th>Fecha de detección</th>
                                    <td>15/03/2025</td>
                                </tr>
                                <tr>
                                    <th>Fecha estimada de solución</th>
                                    <td>22/03/2025</td>
                                </tr>
                            </table>
                        </div>
                       \s
                        <div class="footer">
                            Este documento fue generado automáticamente por el Sistema de Gestión de Defectos.<br>
                            © 2025 Tu Empresa - Todos los derechos reservados
                        </div>
                    </div>
                </body>
                </html>
                """;



        // Verify if directory exists, if not, create it
        File directory = new File(PDF_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }


        // Path for the generated PDF file
        String pdfPath = PDF_DIRECTORY + File.separator + reportId;

        try (FileOutputStream fileOutputStream = new FileOutputStream(pdfPath)) {
            // Convert HTML to PDF and save on the server
            ConverterProperties properties = new ConverterProperties();
            HtmlConverter.convertToPdf(htmlContent, fileOutputStream, properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Create the resource from the generated file
        return new File(pdfPath);

    }
}
