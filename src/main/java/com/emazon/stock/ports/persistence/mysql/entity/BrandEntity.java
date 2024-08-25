package com.emazon.stock.ports.persistence.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "brand")
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long brandId;

    @Column(name= "brand_name" ,nullable = false, unique = true, length = 50)
    private String brandName;

    @Column(name = "brand_description",nullable = false, length = 120)
    private String brandDescription;

}
