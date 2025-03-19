package com.safra.defect_service.entity;

import com.safra.defect_service.dto.ReportDTO;
import com.safra.defect_service.entity.enums.ReportStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Report extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    @ManyToOne
    @JoinColumn(name = "area_id") // Asegura que se pueda insertar null
    private Area area;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cause_id")
    private Cause cause;

    @ManyToOne
    @JoinColumn(name = "responsible_id")
    private Area responsible;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    private List<Evidence> evidence = new ArrayList<>();

    private LocalDateTime createDate;

    private String comment;

    private String salesOrManufacturingOrder;

    private String providerSubmissionUrl;

    public ReportDTO toDto() {
        return new ReportDTO(
                this.id,
                this.area != null ? this.area.getId() : null,
                this.status != null ? this.status.name() : null,
                this.item != null ? this.item.getId() : null,
                this.user != null ? this.user.getId() : null,
                this.comment,
                this.responsible != null ? this.responsible.getId() : null,
                this.salesOrManufacturingOrder,
                this.providerSubmissionUrl,
                this.cause != null ? this.cause.getId() : null
        );
    }
}

