package com.safra.defect_service.entity;

import com.safra.defect_service.dto.ProviderDTO;
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
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "provider")
    private List<Item> items;

    public ProviderDTO toDto() {
        return new ProviderDTO(
                this.id,
                this.name,
                this.email
        );
    }

}