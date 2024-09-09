package com.emazon.stock.ports.persistence.mysql.entity;

import com.emazon.stock.ports.persistence.mysql.util.BrandEntityConstants;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = BrandEntityConstants.TABLE_NAME)

public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = BrandEntityConstants.COLUMN_BRAND_ID)
    private Long brandId;

    @Column(name = BrandEntityConstants.COLUMN_BRAND_NAME, nullable = false, unique = true, length = BrandEntityConstants.LENGTH_BRAND_NAME)
    private String brandName;

    @Column(name = BrandEntityConstants.COLUMN_BRAND_DESCRIPTION, nullable = false, length = BrandEntityConstants.LENGTH_BRAND_DESCRIPTION)
    private String brandDescription;

    @OneToMany(mappedBy = BrandEntityConstants.MAPPED_BY_BRAND, fetch = FetchType.LAZY)
    private List<ProductEntity> products;
}
