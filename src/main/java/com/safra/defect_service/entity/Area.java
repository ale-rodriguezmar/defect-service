package com.safra.defect_service.entity;

import com.safra.defect_service.dto.AreaDTO;
import com.safra.defect_service.dto.CauseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports;

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

    @ManyToMany(mappedBy = "areas")
    private Set<Cause> causes;

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users;

    public AreaDTO toDto() {

        return new AreaDTO(
                this.id,
                this.name,
                causes.stream()
                        .map(Cause::toDto)
                        .collect(Collectors.toSet())
        );
    }
}
