package com.safra.defect_service.dto;

import com.safra.defect_service.entity.Provider;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProviderDTO {
    private Long id;
    private String name;
    private String email;

    public Provider toEntity() {
        Provider provider = new Provider();
        provider.setId(this.getId());
        provider.setName(this.getName());
        provider.setEmail(this.getEmail());
        return provider;
    }
}
