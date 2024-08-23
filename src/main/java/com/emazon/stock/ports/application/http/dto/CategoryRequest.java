package com.emazon.stock.ports.application.http.dto;

import com.emazon.stock.ports.application.http.util.CategoryValidationConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request DTO for Category")
public class CategoryRequest implements Serializable {
    @NotBlank(message = CategoryValidationConstants.NAME_REQUIRED_MESSAGE)
    @Size(min =CategoryValidationConstants.MIN_LENGTH_CATEGORY, max = CategoryValidationConstants.NAME_MAX_LENGTH , message = CategoryValidationConstants.NAME_LENGTH_MESSAGE)
    @Schema(description = "Name of the category", example = "Electronics", requiredMode = Schema.RequiredMode.REQUIRED)
    private String categoryName;
    @NotBlank(message = CategoryValidationConstants.DESCRIPTION_REQUIRED_MESSAGE)
    @Size(min= CategoryValidationConstants.MIN_LENGTH_CATEGORY, max = CategoryValidationConstants.DESCRIPTION_MAX_LENGTH, message = CategoryValidationConstants.DESCRIPTION_LENGTH_MESSAGE)
    @Schema(description = "Description of the category", example = "Electronic devices and accessories",  requiredMode = Schema.RequiredMode.REQUIRED)
    private String categoryDescription;
}