package com.safra.defect_service.controllers;

import com.safra.defect_service.dto.ProviderDTO;
import com.safra.defect_service.entity.Provider;
import com.safra.defect_service.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider")
public class ProviderController {
    
    
    @Autowired
    private ProviderService providerService;

    @GetMapping("/all")
    public ResponseEntity<List<ProviderDTO>> All() {
        return ResponseEntity.ok(providerService.findAll()
                .stream()
                .map(Provider::toDto)
                .toList());
    }

    @PostMapping("/save")
    public ResponseEntity<ProviderDTO> save(@RequestBody ProviderDTO ProviderDTO) {
        return ResponseEntity.ok(providerService
                .save(ProviderDTO.toEntity())
                .toDto());
    }
    
    
}
