package com.safra.defect_service.service;

import com.safra.defect_service.dto.CauseDTO;
import com.safra.defect_service.entity.Cause;
import lombok.extern.java.Log;

import java.util.List;

public interface CauseService {

    List<Cause> findAll();

    Cause save(Cause cause, List<Long> areas, List<Long> solutions);

}
