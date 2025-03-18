package com.safra.defect_service.entity;

import com.safra.defect_service.dto.PermissionDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();

    public PermissionDTO toDto() {
        return new PermissionDTO(
                this.id,
                this.name,
                this.roles != null ?
                        this.roles.stream().map(Role::getId).collect(Collectors.toSet()) :
                        new HashSet<>()
        );
    }
}
