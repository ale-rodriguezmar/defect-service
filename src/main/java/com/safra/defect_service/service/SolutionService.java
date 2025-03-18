package com.safra.defect_service.service;

import com.safra.defect_service.entity.Area;
import com.safra.defect_service.entity.Solution;

import java.util.List;

public interface SolutionService {

    List<Solution> findAll();

    List<Solution> saveAll(List<Solution> solutionList);
}
