package com.emazon.stock.ports.application.http.controller.category;

import com.emazon.stock.domain.api.ICategoryServicePort;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.util.PaginationUtil;
import com.emazon.stock.ports.application.http.dto.category.CategoryRequest;
import com.emazon.stock.ports.application.http.dto.category.CategoryResponse;
import com.emazon.stock.ports.application.http.mapper.category.ICategoryRequestMapper;
import com.emazon.stock.ports.application.http.mapper.category.ICategoryResponseMapper;
import com.emazon.stock.ports.application.http.util.openapi.ResponseCodeConstants;
import com.emazon.stock.ports.application.http.util.openapi.controller.CategoryRestControllerConstants;
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
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = CategoryRestControllerConstants.TAG_NAME, description = CategoryRestControllerConstants.TAG_DESCRIPTION)
public class CategoryRestController {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    @Operation(summary = CategoryRestControllerConstants.SAVE_CATEGORY_SUMMARY, description = CategoryRestControllerConstants.SAVE_CATEGORY_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodeConstants.RESPONSE_CODE_201, description = CategoryRestControllerConstants.SAVE_CATEGORY_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodeConstants.RESPONSE_CODE_400, description = CategoryRestControllerConstants.SAVE_CATEGORY_RESPONSE_400_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = ResponseCodeConstants.RESPONSE_CODE_409, description = CategoryRestControllerConstants.SAVE_CATEGORY_RESPONSE_409_DESCRIPTION, content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> saveCategory(
            @Parameter(description = CategoryRestControllerConstants.PARAM_CATEGORY_REQUEST_BODY_DESCRIPTION, required = true)
            @Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = categoryRequestMapper.categoryRequestToCategory(categoryRequest);
        categoryServicePort.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = CategoryRestControllerConstants.GET_ALL_CATEGORIES_PAGINATED_SUMMARY, description = CategoryRestControllerConstants.GET_ALL_CATEGORIES_PAGINATED_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodeConstants.RESPONSE_CODE_200, description = CategoryRestControllerConstants.GET_ALL_CATEGORIES_PAGINATED_RESPONSE_200_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodeConstants.RESPONSE_CODE_400, description = CategoryRestControllerConstants.GET_ALL_CATEGORIES_PAGINATED_RESPONSE_400_DESCRIPTION, content = @Content)
    })
    @GetMapping
    public ResponseEntity<Pagination<CategoryResponse>> getAllCategoriesPaginated(
            @Parameter(description = CategoryRestControllerConstants.PARAM_PAGE_DESCRIPTION, example = CategoryRestControllerConstants.PARAM_PAGE_EXAMPLE)
            @RequestParam(defaultValue = "0", required = false) int page,
            @Parameter(description = CategoryRestControllerConstants.PARAM_SIZE_DESCRIPTION, example = CategoryRestControllerConstants.PARAM_SIZE_EXAMPLE)
            @RequestParam(defaultValue = "1", required = false) int size,
            @Parameter(description = CategoryRestControllerConstants.PARAM_SORT_BY_DESCRIPTION, example = CategoryRestControllerConstants.PARAM_SORT_BY_EXAMPLE)
            @RequestParam(defaultValue = "categoryName", required = false) String sortBy,
            @Parameter(description = CategoryRestControllerConstants.PARAM_SORT_ORDER_DESCRIPTION, example = CategoryRestControllerConstants.PARAM_SORT_ORDER_EXAMPLE)
            @RequestParam(defaultValue = "true", required = false) boolean isAscending
    ) {
        Pagination<Category> categoryPagination = categoryServicePort.getAllCategoriesPaginated(new PaginationUtil(size, page, sortBy, isAscending));
        List<Category> categories = categoryPagination.getContent();

        return ResponseEntity.ok(
                new Pagination<>(
                        categoryPagination.isAscending(),
                        categoryPagination.getCurrentPage(),
                        categoryPagination.getTotalPages(),
                        categoryPagination.getTotalElements(),
                        categoryResponseMapper.categoriesToCategoryResponses(categories)
                )
        );
    }
}
