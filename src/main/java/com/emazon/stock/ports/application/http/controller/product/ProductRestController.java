package com.emazon.stock.ports.application.http.controller.product;

import com.emazon.stock.domain.api.IBrandServicePort;
import com.emazon.stock.domain.api.ICategoryServicePort;
import com.emazon.stock.domain.api.IProductServicePort;
import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.util.PaginationUtil;
import com.emazon.stock.ports.application.http.dto.product.ProductQuantityRequest;
import com.emazon.stock.ports.application.http.dto.product.ProductRequest;
import com.emazon.stock.ports.application.http.dto.product.ProductResponse;
import com.emazon.stock.ports.application.http.mapper.brand.IBrandResponseMapper;
import com.emazon.stock.ports.application.http.mapper.category.ICategoryResponseMapper;
import com.emazon.stock.ports.application.http.mapper.product.IProductRequestMapper;
import com.emazon.stock.ports.application.http.mapper.product.IProductResponseMapper;
import com.emazon.stock.ports.application.http.util.openapi.ResponseCodeConstants;
import com.emazon.stock.ports.application.http.util.openapi.controller.ProductRestControllerConstants;
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
@Tag(name = ProductRestControllerConstants.TAG_NAME, description = ProductRestControllerConstants.TAG_DESCRIPTION)
public class ProductRestController {
    private final IProductServicePort productServicePort;
    private final IProductRequestMapper productRequestMapper;
    private final IProductResponseMapper productResponseMapper;
    private final IBrandResponseMapper brandResponseMapper;
    private final IBrandServicePort brandServicePort;
    private final ICategoryResponseMapper categoryResponseMapper;
    private final ICategoryServicePort categoryServicePort;


    @Operation(summary = ProductRestControllerConstants.SAVE_PRODUCT_SUMMARY, description = ProductRestControllerConstants.SAVE_PRODUCT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodeConstants.RESPONSE_CODE_201, description = ProductRestControllerConstants.SAVE_PRODUCT_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodeConstants.RESPONSE_CODE_400, description = ProductRestControllerConstants.SAVE_PRODUCT_RESPONSE_400_DESCRIPTION, content = @Content)
    })
    @PostMapping
    public void saveProduct(
            @Parameter(description = ProductRestControllerConstants.PARAM_PRODUCT_REQUEST_BODY_DESCRIPTION, required = true)
            @Valid
            @RequestBody ProductRequest productRequest
    ) {
        Product product = productRequestMapper.productRequestToProduct(productRequest);
        productServicePort.saveProduct(product);
    }

    @Operation(summary = ProductRestControllerConstants.GET_ALL_PRODUCTS_PAGINATED_SUMMARY, description = ProductRestControllerConstants.GET_ALL_PRODUCTS_PAGINATED_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodeConstants.RESPONSE_CODE_200, description = ProductRestControllerConstants.GET_ALL_PRODUCTS_PAGINATED_RESPONSE_200_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodeConstants.RESPONSE_CODE_400, description = ProductRestControllerConstants.GET_ALL_PRODUCTS_PAGINATED_RESPONSE_400_DESCRIPTION, content = @Content)
    })
    @GetMapping
    public ResponseEntity<Pagination<ProductResponse>> getAllProductsPaginated(
            @Parameter(description = ProductRestControllerConstants.PARAM_PAGE_DESCRIPTION, example = ProductRestControllerConstants.PARAM_PAGE_EXAMPLE)
            @RequestParam(defaultValue = ProductRestControllerConstants.DEFAULT_PAGE, required = false) int page,
            @Parameter(description = ProductRestControllerConstants.PARAM_SIZE_DESCRIPTION, example = ProductRestControllerConstants.PARAM_SIZE_EXAMPLE)
            @RequestParam(defaultValue = ProductRestControllerConstants.DEFAULT_SIZE, required = false) int size,
            @Parameter(description = ProductRestControllerConstants.PARAM_SORT_BY_DESCRIPTION, example = ProductRestControllerConstants.PARAM_SORT_BY_EXAMPLE)
            @RequestParam(defaultValue =ProductRestControllerConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @Parameter(description = ProductRestControllerConstants.PARAM_SORT_ORDER_DESCRIPTION, example = ProductRestControllerConstants.PARAM_SORT_ORDER_EXAMPLE)
            @RequestParam(defaultValue = ProductRestControllerConstants.DEFAULT_SORT_ORDER, required = false) boolean isAscending
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
    @PatchMapping("/{productId}")
    public void updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductQuantityRequest productQuantityRequest
            ) {
        Product product = productRequestMapper.productQuantityRequestToProduct(productQuantityRequest);
        product.setProductId(productId);
        productServicePort.updateProduct(product);

    }

    @GetMapping("/{productId}")
    public ResponseEntity<Boolean> getProductById(
            @PathVariable Long productId
    ) {
        productServicePort.getProductById(productId);
        return ResponseEntity.ok(true);

    }

}
