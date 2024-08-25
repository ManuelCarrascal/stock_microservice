package com.emazon.stock.ports.application.http.controller.brand;

import com.emazon.stock.domain.api.IBrandServicePort;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.util.PaginationUtil;
import com.emazon.stock.ports.application.http.dto.brand.BrandRequest;
import com.emazon.stock.ports.application.http.dto.brand.BrandResponse;
import com.emazon.stock.ports.application.http.mapper.brand.IBrandRequestMapper;
import com.emazon.stock.ports.application.http.mapper.brand.IBrandResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
@Tag(name = "Brand", description = "Brand API")
public class BrandRestController {
    private final IBrandServicePort brandServicePort;
    private final IBrandRequestMapper brandRequestMapper;
    private final IBrandResponseMapper brandResponseMapper;

    @Operation(summary = "Save a new brand", description = "Creates a new brand in the database")
    @ApiResponses(value={
            @ApiResponse(responseCode = "201", description = "Brand created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input",content = @Content),
            @ApiResponse(responseCode = "409", description = "Brand already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> saveBrand(@Valid @RequestBody BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.brandRequestToBrand(brandRequest);
        brandServicePort.saveBrand(brand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @Operation(summary = "Get all brands paginated", description = "Retrieves a paginated list of brands")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brands retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters", content = @Content)
    })
    @GetMapping
    public ResponseEntity<Pagination<BrandResponse>> getAllBrandsPaginated(
            @Parameter(description = "Page number", example = "1")
            @RequestParam(defaultValue = "0", required = false) int page,
            @Parameter(description = "Page size", example = "10")
            @RequestParam(defaultValue = "1", required = false) int size,
            @Parameter(description = "Category name filter", example = "categoryName")
            @RequestParam(defaultValue = "brandName",required = false) String nameFilter,
            @Parameter(description = "Sort order", example = "true")
            @RequestParam(defaultValue = "true",required = false) boolean isAscending
    ){
        Pagination<Brand> brandPagination = brandServicePort.getAllBrandsPaginated(new PaginationUtil(size,page, nameFilter, isAscending));
        List<Brand> brands = brandPagination.getContent();

        return ResponseEntity.ok(
                new Pagination<>(
                        brandPagination.isAscending(),
                        brandPagination.getCurrentPage(),
                        brandPagination.getTotalPages(),
                        brandPagination.getTotalElements(),
                        brandResponseMapper.brandsToBrandResponses(brands)
                )
        );
    }
}
