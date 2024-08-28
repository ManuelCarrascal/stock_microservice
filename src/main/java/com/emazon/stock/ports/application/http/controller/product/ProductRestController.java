package com.emazon.stock.ports.application.http.controller.product;

import com.emazon.stock.domain.api.IProductServicePort;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.ports.application.http.dto.product.ProductRequest;
import com.emazon.stock.ports.application.http.mapper.product.IProductRequestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor

public class ProductRestController {
    private final IProductServicePort productServicePort;
    private final IProductRequestMapper productRequestMapper;

    @Operation(summary = "Save a new product", description = "Creates a new product in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public void saveProduct(
            @Parameter(description = "Product request body", required = true)
            @Valid
            @RequestBody ProductRequest productRequest
    ) {
        Product product = productRequestMapper.productRequestToProduct(productRequest);
        productServicePort.saveProduct(product);
    }

}
