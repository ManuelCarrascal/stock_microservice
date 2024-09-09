package com.emazon.stock.ports.application.http.dto.brand;

import com.emazon.stock.ports.application.http.util.openapi.brand.BrandResponseConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = BrandResponseConstants.BRAND_RESPONSE_DESCRIPTION)
public class BrandResponse implements Serializable {
    @Schema(description = BrandResponseConstants.BRAND_ID_DESCRIPTION, example = BrandResponseConstants.BRAND_ID_EXAMPLE)
    private Long brandId;

    @Schema(description = BrandResponseConstants.BRAND_NAME_DESCRIPTION, example = BrandResponseConstants.BRAND_NAME_EXAMPLE)
    private String brandName;

    @Schema(description = BrandResponseConstants.BRAND_DESCRIPTION_DESCRIPTION, example = BrandResponseConstants.BRAND_DESCRIPTION_EXAMPLE)
    private String brandDescription;
}
