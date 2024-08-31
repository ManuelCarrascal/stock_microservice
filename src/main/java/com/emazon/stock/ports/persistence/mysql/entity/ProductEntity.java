package com.emazon.stock.ports.persistence.mysql.entity;

import com.emazon.stock.ports.persistence.mysql.util.ProductEntityConstants;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = ProductEntityConstants.TABLE_NAME)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = ProductEntityConstants.COLUMN_PRODUCT_ID)
    private Long productId;
    @Column(name = ProductEntityConstants.COLUMN_PRODUCT_NAME, nullable = false, length =  ProductEntityConstants.LENGTH_PRODUCT_NAME)
    private String productName;
    @Column(name =ProductEntityConstants.COLUMN_PRODUCT_DESCRIPTION, nullable = false, length = ProductEntityConstants.LENGTH_PRODUCT_DESCRIPTION)
    private String productDescription;
    @Column(name = ProductEntityConstants.COLUMN_PRODUCT_QUANTITY, nullable = false)
    private Integer productQuantity;
    @Column(name = ProductEntityConstants.COLUMN_PRODUCT_PRICE, nullable = false)
    private Double productPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ProductEntityConstants.JOIN_COLUMN_BRAND_ID)
    private BrandEntity brand;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name =ProductEntityConstants.JOIN_TABLE_PRODUCT_CATEGORY,
            joinColumns = @JoinColumn(name = ProductEntityConstants.JOIN_COLUMN_PRODUCT_ID),
            inverseJoinColumns = @JoinColumn(name = ProductEntityConstants.JOIN_COLUMN_CATEGORY_ID)
    )
    private List<CategoryEntity> categories ;

}
