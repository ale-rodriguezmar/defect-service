package com.safra.defect_service.entity;

import com.safra.defect_service.dto.EvidenceDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Evidence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<String> urls;

    @ManyToOne
    private Report report;

    public EvidenceDTO toDto() {
        return new EvidenceDTO(
                this.id,
                this.urls,
                this.report != null ? this.report.getId() : null
        );
    }

}