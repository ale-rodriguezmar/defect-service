package com.safra.defect_service.entity;

import com.safra.defect_service.dto.CauseDTO;
import com.safra.defect_service.entity.Area;
import com.safra.defect_service.entity.Solution;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cause {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "cause_area",
            joinColumns = @JoinColumn(name = "cause_id"),
            inverseJoinColumns = @JoinColumn(name = "area_id")
    )
    private List<Area> areas;

    @ManyToMany
    @JoinTable(
            name = "cause_solution",
            joinColumns = @JoinColumn(name = "cause_id"),
            inverseJoinColumns = @JoinColumn(name = "solution_id")
    )
    private List<Solution> possibleSolutions;

    public CauseDTO toDto() {
        return new CauseDTO(
                this.id,
                this.name,
                this.areas != null ? this.areas.stream().map(Area::getId).toList() : null,
                this.possibleSolutions != null ? this.possibleSolutions.stream().map(Solution::getId).toList() : null
        );
    }
}
