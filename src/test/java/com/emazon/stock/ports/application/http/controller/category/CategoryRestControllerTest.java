package com.emazon.stock.ports.application.http.controller.category;

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
import com.emazon.stock.ports.application.http.mapper.category.CategoryRequestMapper;
import org.springframework.test.context.ContextConfiguration;
import com.emazon.stock.domain.api.ICategoryServicePort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import com.emazon.stock.domain.model.Category;
import org.springframework.test.web.servlet.ResultActions;
import com.emazon.stock.ports.application.http.dto.CategoryRequest;
import static org.mockito.Mockito.doNothing;
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
    private CategoryRequestMapper categoryRequestMapperMock;

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

    //Sapient generated method id: ${saveCategoryTest}, hash: E4DE4A2BC99E366F500F06192928C78A
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

    @SpringBootApplication(scanBasePackageClasses = CategoryRestController.class)
    static class CategoryRestControllerSapientGeneratedTestConfig {
    }
}
