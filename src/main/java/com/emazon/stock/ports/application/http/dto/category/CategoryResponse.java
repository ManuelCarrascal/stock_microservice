package com.emazon.stock.ports.application.http.dto.category;

import com.emazon.stock.ports.application.http.util.openapi.category.CategoryResponseConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = CategoryResponseConstants.CATEGORY_RESPONSE_DESCRIPTION)
public class CategoryResponse implements Serializable {
    @Schema(description = CategoryResponseConstants.CATEGORY_ID_DESCRIPTION, example = CategoryResponseConstants.CATEGORY_ID_EXAMPLE)
    private Long categoryId;

    @Schema(description = CategoryResponseConstants.CATEGORY_NAME_DESCRIPTION, example = CategoryResponseConstants.CATEGORY_NAME_EXAMPLE)
    private String categoryName;

    @Schema(description = CategoryResponseConstants.CATEGORY_DESCRIPTION_DESCRIPTION, example = CategoryResponseConstants.CATEGORY_DESCRIPTION_EXAMPLE)
    private String categoryDescription;
}