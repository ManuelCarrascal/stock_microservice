package com.emazon.stock.ports.application.http.dto.product;

import com.emazon.stock.ports.application.http.util.ProductValidationConstants;
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
@Schema(description = "Request DTO for Product")
public class ProductRequest implements Serializable {
    @Schema(description = "Name of the product", example = "Diadema")
    @NotBlank(message = ProductValidationConstants.PRODUCT_NAME_REQUIRED_MESSAGE)
    private String productName;

    @Schema(description = "Description of the product", example = "Diadema con sonido envolvente")
    @NotBlank(message = ProductValidationConstants.PRODUCT_DESCRIPTION_REQUIRED_MESSAGE)
    private String productDescription;

    @Schema(description = "Quantity of the product", example = "5")
    @NotNull(message = ProductValidationConstants.PRODUCT_QUANTITY_REQUIRED_MESSAGE)
    @Min(value = ProductValidationConstants.PRODUCT_QUANTITY_MIN_VALUE, message = ProductValidationConstants.PRODUCT_QUANTITY_MIN_MESSAGE)
    @Max(value = ProductValidationConstants.PRODUCT_QUANTITY_MAX_VALUE, message = ProductValidationConstants.PRODUCT_QUANTITY_MAX_MESSAGE)
    private Integer productQuantity;

    @Schema(description = "Price of the product", example = "1000")
    @NotNull(message = ProductValidationConstants.PRODUCT_PRICE_REQUIRED_MESSAGE)
    @Min(value = ProductValidationConstants.PRODUCT_PRICE_MIN_VALUE, message = ProductValidationConstants.PRODUCT_PRICE_MIN_MESSAGE)
    private Double productPrice;

    @Schema(description = "ID of the brand", example = "11")
    @NotNull(message = ProductValidationConstants.PRODUCT_BRAND_REQUIRED_MESSAGE)
    @Positive(message = ProductValidationConstants.PRODUCT_BRAND_ID_POSITIVE_MESSAGE)
    private Long brandId;

    @Schema(description = "List of category IDs", example = "[1, 2, 3]")
    @NotNull(message = ProductValidationConstants.PRODUCT_CATEGORY_REQUIRED_MESSAGE)
    @Size(min = ProductValidationConstants.PRODUCT_CATEGORY_MIN_SIZE, max = ProductValidationConstants.PRODUCT_CATEGORY_MAX_SIZE, message = ProductValidationConstants.PRODUCT_CATEGORY_SIZE_MESSAGE)
    @UniqueElements(message = ProductValidationConstants.PRODUCT_CATEGORY_UNIQUE_MESSAGE)
    private List<Long> categoryIds;
}
