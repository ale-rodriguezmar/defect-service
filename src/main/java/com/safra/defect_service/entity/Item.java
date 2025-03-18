package com.safra.defect_service.entity;

import com.safra.defect_service.dto.ItemDTO;
import com.safra.defect_service.entity.enums.ItemStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne  // Cambiado de @OneToOne a @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)  // Se asegura que tenga clave foránea
    private Area area;

    private String name;
    private Double price;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @ManyToOne  // Cambiado de @OneToOne a @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false) // Se asegura que tenga clave foránea
    private Provider provider;

    private String itemCode;

    public ItemDTO toDto() {
        return new ItemDTO(
                this.id,
                this.area != null ? this.area.getName() : "",
                this.name,
                this.price,
                this.status != null ? this.status.name() : "",
                this.provider != null ? this.provider.getName() : "",
                this.itemCode
        );
    }
}
