package com.safra.defect_service.service.Impl;

import com.safra.defect_service.entity.Permission;
import com.safra.defect_service.repository.PermissionRepository;
import com.safra.defect_service.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public List<Permission> saveAll(List<Permission> permissionList) {
        return permissionRepository.saveAll(permissionList);
    }
}
