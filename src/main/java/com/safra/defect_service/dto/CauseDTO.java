package com.safra.defect_service.dto;

import com.safra.defect_service.entity.Cause;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CauseDTO {
    private Long id;
    private String name;
    private List<Long> areas;
    private List<Long> possibleSolutions;

    public Cause toEntity() {
        Cause cause = new Cause();
        cause.setId(this.id);
        cause.setName(this.name);
        return cause;
    }

}
