package com.safra.defect_service.dto;

import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.safra.defect_service.entity.Role;
import com.safra.defect_service.entity.Permission;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private Long id;
    private String name;
    private List<Long> permissions;

    public Role toEntity() {
        Role role = new Role();
        role.setId(this.id);
        role.setName(this.name);

        return role;
    }
}
