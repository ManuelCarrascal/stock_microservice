package com.emazon.stock.ports.application.http.controller.product;

import com.emazon.stock.domain.api.IBrandServicePort;
import com.emazon.stock.domain.api.ICategoryServicePort;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.util.PaginationUtil;
import com.emazon.stock.ports.application.http.dto.brand.BrandProductResponse;
import com.emazon.stock.ports.application.http.dto.product.ProductResponse;
import com.emazon.stock.ports.application.http.mapper.category.ICategoryResponseMapper;
import org.junit.jupiter.api.Timeout;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.emazon.stock.domain.api.IProductServicePort;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.emazon.stock.domain.model.Product;
import org.springframework.http.MediaType;
import org.mockito.MockitoAnnotations;
import com.emazon.stock.ports.application.http.mapper.product.IProductRequestMapper;
import com.emazon.stock.ports.application.http.mapper.product.IProductResponseMapper;
import com.emazon.stock.ports.application.http.mapper.brand.IBrandResponseMapper;
import org.springframework.test.web.servlet.ResultActions;
import com.emazon.stock.ports.application.http.dto.product.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;

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

@Timeout(value = 5)
@WebMvcTest(ProductRestController.class)
class ProductRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductRequestMapper productRequestMapperMock;

    @MockBean
    private IProductServicePort productServicePortMock;

    @MockBean
    private IProductResponseMapper productResponseMapperMock;

    @MockBean
    private IBrandServicePort brandServicePortMock;

    @MockBean
    private IBrandResponseMapper brandResponseMapperMock;

    @MockBean
    private ICategoryServicePort categoryServicePortMock;

    @MockBean
    private ICategoryResponseMapper categoryResponseMapperMock;

    private AutoCloseable autoCloseableMocks;

    @BeforeEach
    public void beforeTest() {
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void afterTest() throws Exception {
        if (autoCloseableMocks != null) {
            autoCloseableMocks.close();
        }
    }

    @Test
    void saveProductTest() throws Exception {
        // Arrange
        Product product = new Product();
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("Test Product");
        productRequest.setProductDescription("Test Description");
        productRequest.setProductQuantity(10);
        productRequest.setProductPrice(99.99);
        productRequest.setBrandId(1L);
        productRequest.setCategoryIds(List.of(1L, 2L));

        doReturn(product).when(productRequestMapperMock).productRequestToProduct(productRequest);
        doNothing().when(productServicePortMock).saveProduct(product);
        String contentStr = new ObjectMapper().writeValueAsString(productRequest);

        // Act
        ResultActions resultActions = this.mockMvc.perform(post("/products")
                .content(contentStr)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // Assert
        resultActions.andExpect(status().isOk());
    }

    @Test
    void getAllProductsPaginatedTest() throws Exception {
        // Arrange
        Product product = new Product();
        product.setProductId(0L);
        product.setBrandId(0L);
        List<Product> productList = List.of(product);
        Pagination<Product> pagination = new Pagination<>(false, 0, 1, 1L, productList);
        doReturn(pagination).when(productServicePortMock).getAllProductsPaginated(any(PaginationUtil.class));

        ProductResponse productResponse = new ProductResponse(0L, "productName1", "productDescription1", 0, 0.0, new BrandProductResponse(), List.of());
        doReturn(productResponse).when(productResponseMapperMock).productToProductResponse(product);

        Brand brand = new Brand();
        doReturn(brand).when(brandServicePortMock).brandGetById(0L);
        doReturn(new BrandProductResponse()).when(brandResponseMapperMock).brandToBrandProductResponse(brand);

        List<Category> categoryList = List.of();
        doReturn(categoryList).when(categoryServicePortMock).getAllByProduct(0L);
        doReturn(List.of()).when(categoryResponseMapperMock).categoriesToCategoryProductResponses(categoryList);

        // Act
        ResultActions resultActions = this.mockMvc.perform(get("/products")
                .param("size", "1")
                .param("sortBy", "productName")
                .param("page", "0")
                .param("isAscending", "false")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages", is(1)))
                .andExpect(jsonPath("$.ascending", is(false)))
                .andExpect(jsonPath("$.currentPage", is(0)))
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.totalElements", is(1)));
    }
    @SpringBootApplication(scanBasePackageClasses = ProductRestController.class)
    static class ProductRestControllerSapientGeneratedTestConfig {
    }
}