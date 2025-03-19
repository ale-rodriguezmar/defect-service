package com.safra.defect_service.service.Impl;

import com.safra.defect_service.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {



    private static final String UPLOAD_DIR = "uploads";

    @Override
    public List<String> uploadFiles(List<MultipartFile> files) throws IOException {
        List<String> routesFile = new ArrayList<>();
        Path rootPath = Paths.get(System.getProperty("user.dir"), "uploads");
        Files.createDirectories(rootPath);
        Path batchDir = rootPath.resolve(UUID.randomUUID().toString());
        Files.createDirectories(batchDir);

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path filePath = batchDir.resolve(fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                routesFile.add(filePath.toString());
            }
        }

        return routesFile;
    }

}
