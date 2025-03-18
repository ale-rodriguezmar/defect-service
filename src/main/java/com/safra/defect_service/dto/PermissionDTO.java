package com.safra.defect_service.dto;

import com.safra.defect_service.entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDTO {
    private Long id;
    private String name;
    private Set<Long> roleId;

    public Permission toEntity() {
        return new Permission(
                this.id,
                this.name,
                null
        );

    }
}
