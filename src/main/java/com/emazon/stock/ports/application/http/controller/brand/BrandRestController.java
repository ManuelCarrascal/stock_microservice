package com.emazon.stock.ports.application.http.controller.brand;

import com.emazon.stock.domain.api.IBrandServicePort;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.util.PaginationUtil;
import com.emazon.stock.ports.application.http.dto.brand.BrandRequest;
import com.emazon.stock.ports.application.http.dto.brand.BrandResponse;
import com.emazon.stock.ports.application.http.mapper.brand.IBrandRequestMapper;
import com.emazon.stock.ports.application.http.mapper.brand.IBrandResponseMapper;
import com.emazon.stock.ports.application.http.util.RolePermissionConstants;
import com.emazon.stock.ports.application.http.util.openapi.ResponseCodeConstants;
import com.emazon.stock.ports.application.http.util.openapi.controller.BrandRestControllerConstants;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
@Tag(name = BrandRestControllerConstants.TAG_NAME, description = BrandRestControllerConstants.TAG_DESCRIPTION)
public class BrandRestController {
    private final IBrandServicePort brandServicePort;
    private final IBrandRequestMapper brandRequestMapper;
    private final IBrandResponseMapper brandResponseMapper;

    @Operation(summary = BrandRestControllerConstants.SAVE_BRAND_SUMMARY, description = BrandRestControllerConstants.SAVE_BRAND_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodeConstants.RESPONSE_CODE_201, description = BrandRestControllerConstants.SAVE_BRAND_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodeConstants.RESPONSE_CODE_400, description = BrandRestControllerConstants.SAVE_BRAND_RESPONSE_400_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = ResponseCodeConstants.RESPONSE_CODE_409, description = BrandRestControllerConstants.SAVE_BRAND_RESPONSE_409_DESCRIPTION, content = @Content)
    })
    @PreAuthorize(RolePermissionConstants.ADMIN_ROLE)
    @PostMapping
    public ResponseEntity<Void> saveBrand(
            @Parameter(description = BrandRestControllerConstants.PARAM_BRAND_REQUEST_BODY_DESCRIPTION, required = true)
            @Valid @RequestBody BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.brandRequestToBrand(brandRequest);
        brandServicePort.saveBrand(brand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = BrandRestControllerConstants.GET_ALL_BRANDS_PAGINATED_SUMMARY, description = BrandRestControllerConstants.GET_ALL_BRANDS_PAGINATED_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodeConstants.RESPONSE_CODE_200, description = BrandRestControllerConstants.GET_ALL_BRANDS_PAGINATED_RESPONSE_200_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodeConstants.RESPONSE_CODE_400, description = BrandRestControllerConstants.GET_ALL_BRANDS_PAGINATED_RESPONSE_400_DESCRIPTION, content = @Content)
    })
    @GetMapping
    public ResponseEntity<Pagination<BrandResponse>> getAllBrandsPaginated(
            @Parameter(description = BrandRestControllerConstants.PARAM_PAGE_DESCRIPTION, example = BrandRestControllerConstants.PARAM_PAGE_EXAMPLE)
            @RequestParam(defaultValue = "0", required = false) int page,
            @Parameter(description = BrandRestControllerConstants.PARAM_SIZE_DESCRIPTION, example = BrandRestControllerConstants.PARAM_SIZE_EXAMPLE)
            @RequestParam(defaultValue = "1", required = false) int size,
            @Parameter(description = BrandRestControllerConstants.PARAM_SORT_BY_DESCRIPTION, example = BrandRestControllerConstants.PARAM_SORT_BY_EXAMPLE)
            @RequestParam(defaultValue = "brandName", required = false) String nameFilter,
            @Parameter(description = BrandRestControllerConstants.PARAM_SORT_ORDER_DESCRIPTION, example = BrandRestControllerConstants.PARAM_SORT_ORDER_EXAMPLE)
            @RequestParam(defaultValue = "true", required = false) boolean isAscending
    ) {
        Pagination<Brand> brandPagination = brandServicePort.getAllBrandsPaginated(new PaginationUtil(size, page, nameFilter, isAscending));
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