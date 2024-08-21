package com.emazon.stock.ports.application.http.controller.category;

import com.emazon.stock.domain.api.ICategoryServicePort;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.ports.application.http.dto.CategoryRequest;
import com.emazon.stock.ports.application.http.dto.CategoryResponse;
import com.emazon.stock.ports.application.http.mapper.category.ICategoryRequestMapper;
import com.emazon.stock.ports.application.http.mapper.category.ICategoryResponseMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor

public class CategoryRestController {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    @PostMapping
    public ResponseEntity<Void> saveCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = categoryRequestMapper.categoryRequestToCategory(categoryRequest);
        categoryServicePort.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categoryResponses = categoryResponseMapper.categoriesToCategoryResponses(categoryServicePort.getAllCategories());
        return ResponseEntity.ok(categoryResponses);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Pagination<CategoryResponse>> getAllCategoriesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size,
            @RequestParam(defaultValue = "desc") String sortDirection
    ) {
        Pagination<Category> categoryPagination = categoryServicePort.getAllCategoriesPaginated(page, size, sortDirection);
        List<CategoryResponse> categoryResponses = categoryResponseMapper.categoriesToCategoryResponses(categoryPagination.getItems());

        Pagination<CategoryResponse> paginationResponse = new Pagination<>(
                categoryResponses,
                categoryPagination.getCurrentPage(),
                categoryPagination.getPageSize(),
                categoryPagination.getTotalItems(),
                categoryPagination.getTotalPages(),
                categoryPagination.getSortDirection()

        );

        return ResponseEntity.ok(paginationResponse);
    }
}
