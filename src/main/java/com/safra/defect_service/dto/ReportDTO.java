package com.safra.defect_service.dto;

import com.safra.defect_service.entity.Report;
import com.safra.defect_service.entity.enums.ReportStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {
    private Long id;
    private Long areaId;
    private String status;
    private Long itemId;
    private Long userId;
    private String comment;
    private Long responsibleIdArea;
    private String salesOrManufacturingOrder;
    private String providerSubmissionUrl;
    private Long causeId;

    public Report toEntity() {
        Report report = new Report();
        report.setId(this.id);
        report.setComment(this.comment);
        report.setSalesOrManufacturingOrder(this.salesOrManufacturingOrder);
        report.setProviderSubmissionUrl(this.providerSubmissionUrl);
        return report;
    }


}
