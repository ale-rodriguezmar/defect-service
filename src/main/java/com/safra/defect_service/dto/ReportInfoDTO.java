package com.safra.defect_service.dto;

import com.safra.defect_service.entity.enums.ReportStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportInfoDTO {

    private long reportId;
    private LocalDateTime createDate;
    private String userName;
    private Long idUser;
    private ReportStatus status;
    private String areaName;
    private String itemCode;
    private String itemName;
    private String causeName;
    private String nameResponsible;
    private String comment;
    private String salesOrManufacturingOrder;
    private List<String> possibleSolutions;

}
