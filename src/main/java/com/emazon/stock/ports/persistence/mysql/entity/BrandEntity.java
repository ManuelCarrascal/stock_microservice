package com.emazon.stock.ports.persistence.mysql.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = BrandEntity.TABLE_NAME)
public class BrandEntity {
    public static final String TABLE_NAME = "brand";
    public static final String COLUMN_BRAND_ID = "brand_id";
    public static final String COLUMN_BRAND_NAME = "brand_name";
    public static final String COLUMN_BRAND_DESCRIPTION = "brand_description";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_BRAND_ID)
    private Long brandId;

    @Column(name= COLUMN_BRAND_NAME ,nullable = false, unique = true, length = 50)
    private String brandName;

    @Column(name = COLUMN_BRAND_DESCRIPTION,nullable = false, length = 120)
    private String brandDescription;
    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    private List<ProductEntity> products;
}
