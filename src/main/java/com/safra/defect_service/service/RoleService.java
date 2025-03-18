package com.safra.defect_service.service;

import com.safra.defect_service.entity.Role;

import java.util.List;

public interface RoleService {


    List<Role> findAll();

    Role save(Role role, List<Long> listIds);
}
