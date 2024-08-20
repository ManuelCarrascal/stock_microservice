package com.emazon.stock.ports.application.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse implements Serializable {

    private Long categoryId;
    private String categoryName;
    private String categoryDescription;
}
