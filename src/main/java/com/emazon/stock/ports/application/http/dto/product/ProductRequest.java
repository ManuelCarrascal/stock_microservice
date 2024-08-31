package com.emazon.stock.ports.application.http.dto.product;

import com.emazon.stock.ports.application.http.util.ProductValidationConstants;
import com.emazon.stock.ports.application.http.util.openapi.product.ProductRequestConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = ProductRequestConstants.PRODUCT_REQUEST_DESCRIPTION)
public class ProductRequest implements Serializable {
    @Schema(description = ProductRequestConstants.PRODUCT_NAME_DESCRIPTION, example = ProductRequestConstants.PRODUCT_NAME_EXAMPLE)
    @NotBlank(message = ProductValidationConstants.PRODUCT_NAME_REQUIRED_MESSAGE)
    private String productName;

    @Schema(description = ProductRequestConstants.PRODUCT_DESCRIPTION_DESCRIPTION, example = ProductRequestConstants.PRODUCT_DESCRIPTION_EXAMPLE)
    @NotBlank(message = ProductValidationConstants.PRODUCT_DESCRIPTION_REQUIRED_MESSAGE)
    private String productDescription;

    @Schema(description = ProductRequestConstants.PRODUCT_QUANTITY_DESCRIPTION, example = ProductRequestConstants.PRODUCT_QUANTITY_EXAMPLE)
    @NotNull(message = ProductValidationConstants.PRODUCT_QUANTITY_REQUIRED_MESSAGE)
    @Min(value = ProductValidationConstants.PRODUCT_QUANTITY_MIN_VALUE, message = ProductValidationConstants.PRODUCT_QUANTITY_MIN_MESSAGE)
    @Max(value = ProductValidationConstants.PRODUCT_QUANTITY_MAX_VALUE, message = ProductValidationConstants.PRODUCT_QUANTITY_MAX_MESSAGE)
    private Integer productQuantity;

    @Schema(description = ProductRequestConstants.PRODUCT_PRICE_DESCRIPTION, example = ProductRequestConstants.PRODUCT_PRICE_EXAMPLE)
    @NotNull(message = ProductValidationConstants.PRODUCT_PRICE_REQUIRED_MESSAGE)
    @Min(value = ProductValidationConstants.PRODUCT_PRICE_MIN_VALUE, message = ProductValidationConstants.PRODUCT_PRICE_MIN_MESSAGE)
    private Double productPrice;

    @Schema(description = ProductRequestConstants.BRAND_ID_DESCRIPTION, example = ProductRequestConstants.BRAND_ID_EXAMPLE)
    @NotNull(message = ProductValidationConstants.PRODUCT_BRAND_REQUIRED_MESSAGE)
    @Positive(message = ProductValidationConstants.PRODUCT_BRAND_ID_POSITIVE_MESSAGE)
    private Long brandId;

    @Schema(description = ProductRequestConstants.CATEGORY_IDS_DESCRIPTION, example = ProductRequestConstants.CATEGORY_IDS_EXAMPLE)
    @NotNull(message = ProductValidationConstants.PRODUCT_CATEGORY_REQUIRED_MESSAGE)
    @Size(min = ProductValidationConstants.PRODUCT_CATEGORY_MIN_SIZE, max = ProductValidationConstants.PRODUCT_CATEGORY_MAX_SIZE, message = ProductValidationConstants.PRODUCT_CATEGORY_SIZE_MESSAGE)
    @UniqueElements(message = ProductValidationConstants.PRODUCT_CATEGORY_UNIQUE_MESSAGE)
    private List<Long> categoryIds;
}
