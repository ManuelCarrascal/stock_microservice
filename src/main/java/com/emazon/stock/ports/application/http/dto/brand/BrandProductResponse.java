package com.emazon.stock.ports.application.http.dto.brand;

import com.emazon.stock.ports.application.http.util.openapi.brand.BrandProductResponseConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = BrandProductResponseConstants.BRAND_PRODUCT_RESPONSE_DESCRIPTION)
public class BrandProductResponse {
    @Schema(description = BrandProductResponseConstants.BRAND_NAME_DESCRIPTION, example = BrandProductResponseConstants.BRAND_NAME_EXAMPLE)
    private String brandName;
}
