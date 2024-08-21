package com.emazon.stock.ports.application.http.dto;

import com.emazon.stock.ports.application.http.util.CategoryValidationConstants;
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


    @NotBlank(message = CategoryValidationConstants.DESCRIPTION_REQUIRED_MESSAGE)
    @Size(max = CategoryValidationConstants.NAME_MAX_LENGTH , message = CategoryValidationConstants.NAME_LENGTH_MESSAGE)
    private String categoryName;
    @NotBlank(message = CategoryValidationConstants.DESCRIPTION_REQUIRED_MESSAGE)
    @Size(max = CategoryValidationConstants.DESCRIPTION_MAX_LENGTH, message = CategoryValidationConstants.DESCRIPTION_LENGTH_MESSAGE)
    private String categoryDescription;
}
