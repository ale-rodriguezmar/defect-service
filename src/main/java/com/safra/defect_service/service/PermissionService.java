package com.safra.defect_service.service;

import com.safra.defect_service.entity.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findAll();

    List<Permission> saveAll(List<Permission> PermissionList);
}

