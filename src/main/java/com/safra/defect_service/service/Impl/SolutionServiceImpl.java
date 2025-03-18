package com.safra.defect_service.service.Impl;

import com.safra.defect_service.entity.Solution;
import com.safra.defect_service.repository.SolutionRepository;
import com.safra.defect_service.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolutionServiceImpl implements SolutionService {

    @Autowired
    private SolutionRepository solutionRepository;

    @Override
    public List<Solution> findAll() {
        return solutionRepository.findAll();
    }

    @Override
    public List<Solution> saveAll(List<Solution> solutionList) {
        return solutionRepository.saveAll(solutionList);
    }
}
