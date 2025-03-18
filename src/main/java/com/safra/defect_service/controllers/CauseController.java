package com.safra.defect_service.controllers;


import com.safra.defect_service.dto.AreaDTO;
import com.safra.defect_service.dto.CauseDTO;
import com.safra.defect_service.entity.Area;
import com.safra.defect_service.entity.Cause;
import com.safra.defect_service.entity.User;
import com.safra.defect_service.service.CauseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cause")
public class CauseController {

    @Autowired
    private CauseService causeService;


    @GetMapping("/all")
    public ResponseEntity<List<CauseDTO>> all() {
        return ResponseEntity.ok(causeService.findAll()
                .stream()
                .map(Cause::toDto)
                .toList());
    }

    @PostMapping("/save")
    public ResponseEntity<CauseDTO> save(@RequestBody CauseDTO causeDTO) {
        return ResponseEntity.ok(causeService.save(causeDTO.toEntity(),causeDTO.getAreas(),causeDTO.getPossibleSolutions()).toDto());
    }

}
