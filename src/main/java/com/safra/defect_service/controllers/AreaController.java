package com.safra.defect_service.controllers;


import com.safra.defect_service.dto.AreaDTO;
import com.safra.defect_service.dto.CauseDTO;
import com.safra.defect_service.entity.Area;
import com.safra.defect_service.entity.Cause;
import com.safra.defect_service.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/area")
public class AreaController {


    @Autowired
    private AreaService areaService;

    @GetMapping("/all")
    public ResponseEntity<List<AreaDTO>> All() {
        return ResponseEntity.ok(areaService.findAll()
                .stream()
                .map(Area::toDto)
                .toList());
    }

    @PostMapping("/save-all")
    public ResponseEntity<List<AreaDTO>> saveAll(@RequestBody List<AreaDTO> areaDTOList) {
        return ResponseEntity.ok(areaService.saveAll(areaDTOList
                        .stream()
                        .map(AreaDTO::toEntity)
                        .toList())
                .stream()
                .map(Area::toDto)
                .toList());
    }
}
