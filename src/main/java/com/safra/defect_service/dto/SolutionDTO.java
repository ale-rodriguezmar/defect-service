package com.safra.defect_service.dto;

import com.safra.defect_service.entity.Solution;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolutionDTO {
    private Long id;
    private String description;

    public Solution toEntity() {
        Solution solution = new Solution();
        solution.setId(this.id);
        solution.setDescription(this.description);
        return solution;
    }

}
