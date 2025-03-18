package com.safra.defect_service.controllers;


import com.safra.defect_service.dto.PermissionDTO;
import com.safra.defect_service.entity.Permission;
import com.safra.defect_service.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {


    @Autowired
    private PermissionService permissionService;

    @GetMapping("/all")
    public ResponseEntity<List<PermissionDTO>> All() {
        return ResponseEntity.ok(permissionService.findAll()
                .stream()
                .map(Permission::toDto)
                .toList());
    }

    @PostMapping("/save-all")
    public ResponseEntity<List<PermissionDTO>> saveAll(@RequestBody List<PermissionDTO> PermissionDTOList) {
        return ResponseEntity.ok(permissionService.saveAll(PermissionDTOList
                        .stream()
                        .map(PermissionDTO::toEntity)
                        .toList())
                .stream()
                .map(Permission::toDto)
                .toList());
    }
}
