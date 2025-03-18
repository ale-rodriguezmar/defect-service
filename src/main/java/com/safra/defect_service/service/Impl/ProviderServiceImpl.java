package com.safra.defect_service.service.Impl;

import com.safra.defect_service.entity.Provider;
import com.safra.defect_service.repository.ProviderRepository;
import com.safra.defect_service.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public List<Provider> findAll() {
        return providerRepository.findAll();
    }

    @Override
    public Provider save(Provider provider) {
        return providerRepository.save(provider);
    }
}
