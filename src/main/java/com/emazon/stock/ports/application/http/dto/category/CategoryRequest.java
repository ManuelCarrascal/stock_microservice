package com.emazon.stock.ports.application.http.dto.category;

import com.emazon.stock.ports.application.http.util.CategoryValidationConstants;
import com.emazon.stock.ports.application.http.util.openapi.category.CategoryRequestConstants;
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
@Schema(description = CategoryRequestConstants.CATEGORY_REQUEST_DESCRIPTION)
public class CategoryRequest implements Serializable {
    @NotBlank(message = CategoryValidationConstants.NAME_REQUIRED_MESSAGE)
    @Size(min = CategoryValidationConstants.MIN_LENGTH_CATEGORY, max = CategoryValidationConstants.NAME_MAX_LENGTH, message = CategoryValidationConstants.NAME_LENGTH_MESSAGE)
    @Schema(description = CategoryRequestConstants.CATEGORY_NAME_DESCRIPTION, example = CategoryRequestConstants.CATEGORY_NAME_EXAMPLE, requiredMode = Schema.RequiredMode.REQUIRED)
    private String categoryName;

    @NotBlank(message = CategoryValidationConstants.DESCRIPTION_REQUIRED_MESSAGE)
    @Size(min = CategoryValidationConstants.MIN_LENGTH_CATEGORY, max = CategoryValidationConstants.DESCRIPTION_MAX_LENGTH, message = CategoryValidationConstants.DESCRIPTION_LENGTH_MESSAGE)
    @Schema(description = CategoryRequestConstants.CATEGORY_DESCRIPTION_DESCRIPTION, example = CategoryRequestConstants.CATEGORY_DESCRIPTION_EXAMPLE, requiredMode = Schema.RequiredMode.REQUIRED)
    private String categoryDescription;
}