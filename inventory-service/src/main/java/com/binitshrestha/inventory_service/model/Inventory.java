package com.binitshrestha.inventory_service.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="t_inventory")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private Integer quantity;
}
