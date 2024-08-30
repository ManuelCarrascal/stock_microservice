package com.emazon.stock.ports.application.http.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response DTO for Category")
public class CategoryResponse implements Serializable {
    @Schema(description = "ID of the category", example = "1")
    private Long categoryId;
    @Schema(description = "Name of the category", example = "Electronics")
    private String categoryName;
    @Schema(description = "Description of the category", example = "Electronic devices and accessories")
    private String categoryDescription;
}
