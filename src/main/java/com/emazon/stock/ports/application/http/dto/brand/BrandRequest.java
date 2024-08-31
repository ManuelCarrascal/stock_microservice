package com.emazon.stock.ports.application.http.dto.brand;

import com.emazon.stock.ports.application.http.util.BrandValidationConstants;
import com.emazon.stock.ports.application.http.util.openapi.brand.BrandRequestConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = BrandRequestConstants.BRAND_REQUEST_DESCRIPTION)
public class BrandRequest {
    @NotBlank(message = BrandValidationConstants.NAME_REQUIRED_MESSAGE)
    @Size(min = BrandValidationConstants.MIN_LENGTH_BRAND, max = BrandValidationConstants.NAME_MAX_LENGTH, message = BrandValidationConstants.NAME_LENGTH_MESSAGE)
    @Schema(description = BrandRequestConstants.BRAND_NAME_DESCRIPTION, example = BrandRequestConstants.BRAND_NAME_EXAMPLE, requiredMode = Schema.RequiredMode.REQUIRED)
    private String brandName;

    @NotBlank(message = BrandValidationConstants.DESCRIPTION_REQUIRED_MESSAGE)
    @Size(min = BrandValidationConstants.MIN_LENGTH_BRAND, max = BrandValidationConstants.DESCRIPTION_MAX_LENGTH, message = BrandValidationConstants.DESCRIPTION_LENGTH_MESSAGE)
    @Schema(description = BrandRequestConstants.BRAND_DESCRIPTION_DESCRIPTION, example = BrandRequestConstants.BRAND_DESCRIPTION_EXAMPLE, requiredMode = Schema.RequiredMode.REQUIRED)
    private String brandDescription;
}
