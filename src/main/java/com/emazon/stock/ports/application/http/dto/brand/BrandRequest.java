package com.emazon.stock.ports.application.http.dto.brand;

import com.emazon.stock.ports.application.http.util.BrandValidationConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request DTO for Brand")
public class BrandRequest {
    @NotBlank( message = BrandValidationConstants.NAME_REQUIRED_MESSAGE)
    @Size(min = BrandValidationConstants.MIN_LENGTH_BRAND, max = BrandValidationConstants.NAME_MAX_LENGTH, message = BrandValidationConstants.NAME_LENGTH_MESSAGE)
    @Schema(description = "Name of the brand", example = "Samsung", requiredMode = Schema.RequiredMode.REQUIRED)
    private String brandName;
    @NotBlank(message = BrandValidationConstants.DESCRIPTION_REQUIRED_MESSAGE)
    @Size(min = BrandValidationConstants.MIN_LENGTH_BRAND, max = BrandValidationConstants.DESCRIPTION_MAX_LENGTH ,message = BrandValidationConstants.DESCRIPTION_LENGTH_MESSAGE)
    @Schema(description = "Description of the brand", example = "Samsung Electronics is a South Korean multinational electronics company", requiredMode = Schema.RequiredMode.REQUIRED)
    private String brandDescription;
}
