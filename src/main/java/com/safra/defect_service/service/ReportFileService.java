package com.safra.defect_service.service;

import java.io.File;
import java.io.FileNotFoundException;

public interface ReportFileService {


    File GenerateReportPDF(String reportId) throws FileNotFoundException;
}
