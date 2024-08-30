package com.emazon.stock.ports.persistence.mysql.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@ToString(exclude = "products")
@Entity
@Table(name =CategoryEntity.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryEntity {
    public static final String TABLE_NAME = "category";
    public static final String COLUMN_CATEGORY_ID = "category_id";
    public static final String COLUMN_CATEGORY_NAME = "category_name";
    public static final String COLUMN_CATEGORY_DESCRIPTION = "category_description";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_CATEGORY_ID)
    private Long categoryId;

    @Column(name = COLUMN_CATEGORY_NAME, nullable = false, unique = true, length = 50)
    private String categoryName;

    @Column(name = COLUMN_CATEGORY_DESCRIPTION, nullable = false, length = 90)
    private String categoryDescription;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<ProductEntity> products;
}
