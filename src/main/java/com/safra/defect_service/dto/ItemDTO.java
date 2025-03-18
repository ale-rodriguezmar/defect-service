package com.safra.defect_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private Long id;
    private String areaName;
    private String name;
    private Double price;
    private String status;
    private String providerName;
    private String itemCode;
}
