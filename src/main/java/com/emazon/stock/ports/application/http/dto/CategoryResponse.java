package com.emazon.stock.ports.application.http.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse implements Serializable {

    private Long categoryId;
    private String categoryName;
    private String categoryDescription;
}
