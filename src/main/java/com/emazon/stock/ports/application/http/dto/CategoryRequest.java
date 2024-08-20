package com.emazon.stock.ports.application.http.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest implements Serializable {

    private static final int NAME_MAX_LENGTH = 50;
    private static final int DESCRIPTION_MAX_LENGTH = 90;

    @NotBlank(message = "{category.name.required-message}")
    @Size(max = NAME_MAX_LENGTH , message = "{category.name.length-message}")
    private String categoryName;
    @NotBlank(message = "{category.description.required-message}")
    @Size(max = DESCRIPTION_MAX_LENGTH, message = "{category.description.length-message}")
    private String categoryDescription;
}
