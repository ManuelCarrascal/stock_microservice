package com.emazon.stock.ports.persistence.mysql.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = ProductEntity.TABLE_NAME)
public class ProductEntity {
    public static final String TABLE_NAME = "product";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_PRODUCT_NAME = "product_name";
    public static final String COLUMN_PRODUCT_DESCRIPTION = "product_description";
    public static final String COLUMN_PRODUCT_QUANTITY = "product_quantity";
    public static final String COLUMN_PRODUCT_PRICE = "product_price";
    public static final String JOIN_COLUMN_BRAND_ID = "brand_id";
    public static final String JOIN_TABLE_PRODUCT_CATEGORY = "product_category";
    public static final String JOIN_COLUMN_PRODUCT_ID = "product_id";
    public static final String JOIN_COLUMN_CATEGORY_ID = "category_id";
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = COLUMN_PRODUCT_ID)
    private Long productId;
    @Column(name = COLUMN_PRODUCT_NAME, nullable = false, length = 60)
    private String productName;
    @Column(name =COLUMN_PRODUCT_DESCRIPTION, nullable = false, length = 90)
    private String productDescription;
    @Column(name = COLUMN_PRODUCT_QUANTITY, nullable = false)
    private Integer productQuantity;
    @Column(name = COLUMN_PRODUCT_PRICE, nullable = false)
    private Double productPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = JOIN_COLUMN_BRAND_ID)
    private BrandEntity brand;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name =JOIN_TABLE_PRODUCT_CATEGORY,
            joinColumns = @JoinColumn(name = JOIN_COLUMN_PRODUCT_ID),
            inverseJoinColumns = @JoinColumn(name = JOIN_COLUMN_CATEGORY_ID)
    )
    private List<CategoryEntity> categories ;

}
