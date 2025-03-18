package com.safra.defect_service.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvidenceDTO {
    private Long id;
    private List<String> urls;
    private Long reportId;
}
