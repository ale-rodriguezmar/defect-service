package com.safra.defect_service.controllers;


import com.safra.defect_service.dto.RoleDTO;
import com.safra.defect_service.entity.Area;
import com.safra.defect_service.entity.Role;
import com.safra.defect_service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    
    
    @Autowired
    RoleService roleService;

    @GetMapping("/all")
    public ResponseEntity<List<RoleDTO>> All() {
        return ResponseEntity.ok(roleService.findAll()
                .stream()
                .map(Role::toDto)
                .toList());
    }

    @PostMapping("/save")
    public ResponseEntity<RoleDTO> save(@RequestBody RoleDTO RoleDTO) {
        return ResponseEntity.ok(roleService
                .save(RoleDTO.toEntity(),RoleDTO.getPermissions())
                .toDto());
    }
    
}
