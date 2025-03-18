package com.safra.defect_service.service.Impl;

import com.safra.defect_service.entity.Role;
import com.safra.defect_service.repository.PermissionRepository;
import com.safra.defect_service.repository.RoleRepository;
import com.safra.defect_service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }



    @Override
    public Role save(Role role, List<Long> listIds) {


        System.out.println(listIds);
        role.setPermissions(permissionRepository.findAllById(listIds));
        return roleRepository.save(role);
    }
}
