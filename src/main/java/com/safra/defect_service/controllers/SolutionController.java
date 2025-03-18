package com.safra.defect_service.controllers;

import com.safra.defect_service.dto.CauseDTO;
import com.safra.defect_service.dto.SolutionDTO;
import com.safra.defect_service.entity.Cause;
import com.safra.defect_service.entity.Solution;
import com.safra.defect_service.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solution")
public class SolutionController {

    @Autowired
    private SolutionService solutionService;

    @GetMapping("/all")
        public ResponseEntity<List<SolutionDTO>> all() {
        return ResponseEntity.ok(solutionService.findAll()
                .stream()
                .map(Solution::toDto)
                .toList());
    }

    @PostMapping("/save-all")
    public ResponseEntity<List<SolutionDTO>> saveAll(@RequestBody List<SolutionDTO> solutionDTOList) {
        return ResponseEntity.ok(solutionService.saveAll(solutionDTOList
                        .stream()
                        .map(SolutionDTO::toEntity)
                        .toList())
                .stream()
                .map(Solution::toDto)
                .toList());
    }

}
