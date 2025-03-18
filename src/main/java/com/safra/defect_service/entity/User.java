package com.safra.defect_service.entity;

import com.safra.defect_service.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles; // Renombrado de "roleEntities" a "roles"

    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports;

    @ManyToOne // Corregido de @OneToOne a @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    public UserDTO toDto() {
        return new UserDTO(
                this.id,
                this.username,
                this.email,
                this.area != null ? this.area.getName() : null,
                this.roles != null ? this.roles.stream().map(Role::getName).collect(Collectors.toSet()) : new HashSet<>()
        );
    }
}
