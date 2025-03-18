package com.safra.defect_service.service.Impl;

import com.safra.defect_service.entity.Area;
import com.safra.defect_service.repository.AreaRepository;
import com.safra.defect_service.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    AreaRepository areaRepository;

    @Override
    public List<Area> findAll() {
        return areaRepository.findAll();
    }

    @Override
    public List<Area> saveAll(List<Area> areaList) {
        return areaRepository.saveAll(areaList);
    }
}
