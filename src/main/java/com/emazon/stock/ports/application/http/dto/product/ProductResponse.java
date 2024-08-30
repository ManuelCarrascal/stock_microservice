package com.emazon.stock.ports.application.http.dto.product;

import com.emazon.stock.ports.application.http.dto.category.CategoryProductResponse;
import com.emazon.stock.ports.application.http.dto.brand.BrandProductResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response DTO for Product")
public class ProductResponse {
    @Schema(description = "ID of the product", example = "1")
    private Long productId;
    @Schema(description = "Name of the product", example = "Diadema")
    private String productName;
    @Schema(description = "Description of the product", example = "Diadema con sonido envolvente")
    private String productDescription;
    @Schema(description = "Quantity of the product", example = "5")
    private Integer productQuantity;
    @Schema(description = "Price of the product", example = "1000")
    private Double productPrice;
    @Schema(description = "Brand details of the product")
    private BrandProductResponse brand;
    @Schema(description = "List of categories associated with the product")
    private List<CategoryProductResponse> categories;

}
