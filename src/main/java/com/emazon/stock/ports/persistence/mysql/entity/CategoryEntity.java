package com.emazon.stock.ports.persistence.mysql.entity;

import com.emazon.stock.ports.persistence.mysql.util.CategoryEntityConstants;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table(name = CategoryEntityConstants.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CategoryEntityConstants.COLUMN_CATEGORY_ID)
    private Long categoryId;

    @Column(name = CategoryEntityConstants.COLUMN_CATEGORY_NAME, nullable = false, unique = true, length = CategoryEntityConstants.LENGTH_CATEGORY_NAME)
    private String categoryName;

    @Column(name = CategoryEntityConstants.COLUMN_CATEGORY_DESCRIPTION, nullable = false, length = CategoryEntityConstants.LENGTH_CATEGORY_DESCRIPTION)
    private String categoryDescription;

    @ManyToMany(mappedBy = CategoryEntityConstants.MAPPED_BY_CATEGORIES, fetch = FetchType.LAZY)
    private List<ProductEntity> products;
}
