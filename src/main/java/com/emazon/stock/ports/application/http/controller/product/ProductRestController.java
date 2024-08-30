package com.emazon.stock.ports.application.http.controller.product;

import com.emazon.stock.domain.api.IBrandServicePort;
import com.emazon.stock.domain.api.ICategoryServicePort;
import com.emazon.stock.domain.api.IProductServicePort;
import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.util.PaginationUtil;
import com.emazon.stock.ports.application.http.dto.product.ProductRequest;
import com.emazon.stock.ports.application.http.dto.product.ProductResponse;
import com.emazon.stock.ports.application.http.mapper.brand.IBrandResponseMapper;
import com.emazon.stock.ports.application.http.mapper.category.ICategoryResponseMapper;
import com.emazon.stock.ports.application.http.mapper.product.IProductRequestMapper;
import com.emazon.stock.ports.application.http.mapper.product.IProductResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Product", description = "Product API")
public class ProductRestController {
    private final IProductServicePort productServicePort;
    private final IProductRequestMapper productRequestMapper;
    private final IProductResponseMapper productResponseMapper;
    private final IBrandResponseMapper brandResponseMapper;
    private final IBrandServicePort brandServicePort;
    private final ICategoryResponseMapper categoryResponseMapper;
    private final ICategoryServicePort categoryServicePort;

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
    @Operation(summary = "Get all products paginated", description = "Retrieves a paginated list of products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters", content = @Content)
    })
    @GetMapping
    public ResponseEntity<Pagination<ProductResponse>> getAllProductsPaginated(
            @Parameter(description = "Page number", example = "1")
            @RequestParam(defaultValue = "0", required = false) int page,
            @Parameter(description = "Page size", example = "10")
            @RequestParam(defaultValue = "1", required = false) int size,
            @Parameter(description = "Sort by", example = "productName")
            @RequestParam(defaultValue = "productName", required = false) String sortBy,
            @Parameter(description = "Sort order", example = "true")
            @RequestParam(defaultValue = "true", required = false) boolean isAscending
    ) {
        Pagination<Product> productPagination = productServicePort.getAllProductsPaginated(new PaginationUtil(size, page, sortBy, isAscending));
        List<Product> products = productPagination.getContent();
        List<ProductResponse> productResponses = products.stream().map(
                product -> {
                    ProductResponse productResponse = productResponseMapper.productToProductResponse(product);
                    productResponse.setBrand(brandResponseMapper.brandToBrandProductResponse(brandServicePort.brandGetById(product.getBrandId())));
                    productResponse.setCategories(categoryResponseMapper.categoriesToCategoryProductResponses(categoryServicePort.getAllByProduct(product.getProductId())));
                    return productResponse;
                }

        ).toList();
        return ResponseEntity.ok(
                new Pagination<>(
                        productPagination.isAscending(),
                        productPagination.getCurrentPage(),
                        productPagination.getTotalPages(),
                        productPagination.getTotalElements(),
                        productResponses)
        );
    }

}
