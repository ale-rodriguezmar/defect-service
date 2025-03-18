package com.safra.defect_service.dto;

import com.safra.defect_service.entity.Area;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AreaDTO {
    private Long id;
    private String name;
    private Set<CauseDTO> cuaseDTOList;

    public Area toEntity() {
        Area area = new Area();
        area.setId(this.id);
        area.setName(this.name);
        return area;
    }
}
