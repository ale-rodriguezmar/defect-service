package com.safra.defect_service.service.Impl;

import com.safra.defect_service.entity.Area;
import com.safra.defect_service.entity.Cause;
import com.safra.defect_service.entity.Solution;
import com.safra.defect_service.repository.AreaRepository;
import com.safra.defect_service.repository.CauseRepository;
import com.safra.defect_service.repository.SolutionRepository;
import com.safra.defect_service.service.CauseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CauseServiceImpl implements CauseService {

    @Autowired
    private CauseRepository causeRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private SolutionRepository solutionRepository;



    @Override
    public List<Cause> findAll() {
        return  causeRepository.findAll();
    }

    @Override
    public Cause save(Cause cause, List<Long> areas, List<Long> solutions) {
        List<Area> areaList = areaRepository.findAllById(areas);
        List<Solution> solutionList = solutionRepository.findAllById(solutions);
        cause.setAreas(areaList);
        cause.setPossibleSolutions(solutionList);
        return causeRepository.save(cause);
    }



}
