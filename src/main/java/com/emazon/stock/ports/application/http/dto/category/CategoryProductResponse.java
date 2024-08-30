package com.emazon.stock.ports.application.http.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryProductResponse implements Serializable {
    private Long categoryId;
    private String categoryName;
}
