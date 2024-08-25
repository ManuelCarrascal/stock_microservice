package com.emazon.stock.ports.application.http.dto.brand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response DTO for Brand")
public class BrandResponse implements Serializable {
    @Schema(description = "ID of the brand", example = "1")
    private Long brandId;
    @Schema(description = "Name of the brand", example = "Samsung")
    private String brandName;
    @Schema(description = "Description of the brand", example = "Equipment manufacturer technology company")
    private String brandDescription;
}
