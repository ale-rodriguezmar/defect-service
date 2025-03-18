package com.safra.defect_service.service;

import com.safra.defect_service.entity.Provider;
import com.safra.defect_service.entity.Role;

import java.util.List;

public interface ProviderService {


    List<Provider> findAll();

    Provider save(Provider provider);
}
