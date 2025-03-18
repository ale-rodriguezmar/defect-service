package com.safra.defect_service.entity;

import com.safra.defect_service.dto.SolutionDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    public SolutionDTO toDto() {
        return new SolutionDTO(this.id, this.description);
    }
}
