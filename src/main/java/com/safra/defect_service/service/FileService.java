package com.safra.defect_service.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

    List<String> uploadFiles(List<MultipartFile> files) throws IOException;
}
