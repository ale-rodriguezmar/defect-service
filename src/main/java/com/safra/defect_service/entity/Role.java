package com.safra.defect_service.entity;

import com.safra.defect_service.dto.RoleDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<Permission> permissions = new ArrayList<>();

    public RoleDTO toDto() {
        return new RoleDTO(
                this.id,
                this.name,
                this.permissions!= null ?
                        this.permissions.stream().map(Permission::getId).collect(Collectors.toList()) :
                        new ArrayList<>()
        );
    }
}
