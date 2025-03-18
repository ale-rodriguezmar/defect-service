package com.safra.defect_service.service;

import com.safra.defect_service.entity.Area;

import java.util.List;

public interface AreaService {

    List<Area> findAll();

    List<Area> saveAll(List<Area> areaList);


}
