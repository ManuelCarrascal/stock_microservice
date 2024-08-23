package com.emazon.stock.ports.application.http.controller.category;

import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.util.PaginationUtil;
import com.emazon.stock.ports.application.http.dto.CategoryResponse;
import com.emazon.stock.ports.application.http.mapper.category.ICategoryRequestMapper;
import com.emazon.stock.ports.application.http.mapper.category.ICategoryResponseMapper;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import com.emazon.stock.domain.api.ICategoryServicePort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import com.emazon.stock.domain.model.Category;
import org.springframework.test.web.servlet.ResultActions;
import com.emazon.stock.ports.application.http.dto.CategoryRequest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.doReturn;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
@WebMvcTest()
@ContextConfiguration(classes = CategoryRestController.class)
class CategoryRestControllerTest {

    @Autowired()
    private MockMvc mockMvc;

    @MockBean(name = "categoryRequestMapper")
    private ICategoryRequestMapper categoryRequestMapperMock;

    @MockBean(name = "categoryResponseMapper")
    private ICategoryResponseMapper categoryResponseMapperMock;


    @MockBean(name = "categoryServicePort")
    private ICategoryServicePort categoryServicePortMock;

    private AutoCloseable autoCloseableMocks;

    @BeforeEach()
    public void beforeTest() {
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach()
    public void afterTest() throws Exception {
        if (autoCloseableMocks != null)
            autoCloseableMocks.close();
    }

    @Test
    void saveCategoryTest() throws Exception {
        // Arrange
        CategoryRequest categoryRequest = new CategoryRequest("Electronics", "All electronic items");

        String contentStr = new ObjectMapper().writeValueAsString(categoryRequest);

        Category category = new Category();
        doReturn(category).when(categoryRequestMapperMock).categoryRequestToCategory(categoryRequest);
        doNothing().when(categoryServicePortMock).saveCategory(category);

        // Act
        ResultActions resultActions = this.mockMvc.perform(post("/categories")
                .content(contentStr)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // Assert
        resultActions.andExpect(status().isCreated());
    }

    @Test
    void getAllCategoriesPaginatedTest() throws Exception {
        // Arrange
        List<Category> categoryList = new ArrayList<>();
        Pagination<Category> pagination = new Pagination<>(false, 0, 0, 0L, categoryList);
        doReturn(pagination).when(categoryServicePortMock).getAllCategoriesPaginated(any(PaginationUtil.class));
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        doReturn(categoryResponseList).when(categoryResponseMapperMock).categoriesToCategoryResponses(categoryList);

        // Act
        ResultActions resultActions = this.mockMvc.perform(get("/categories")
                .param("size", String.valueOf(0))
                .param("page", String.valueOf(0))
                .param("nameFilter", "nameFilter1")
                .param("isAscending", String.valueOf(false))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages", is(0)))
                .andExpect(jsonPath("$.ascending", is(false)))
                .andExpect(jsonPath("$.currentPage", is(0)))
                .andExpect(jsonPath("$.content", hasSize(0)))
                .andExpect(jsonPath("$.totalElements", is(0)));  // Cambiado para comparar con un Integer en lugar de Long
    }

    @SpringBootApplication(scanBasePackageClasses = CategoryRestController.class)
    static class CategoryRestControllerSapientGeneratedTestConfig {
    }
}
