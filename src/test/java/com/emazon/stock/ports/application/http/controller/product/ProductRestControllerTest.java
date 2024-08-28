package com.emazon.stock.ports.application.http.controller.product;

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
import org.springframework.test.web.servlet.ResultActions;
import com.emazon.stock.ports.application.http.dto.product.ProductRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.doReturn;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
@WebMvcTest(ProductRestController.class)
@ContextConfiguration(classes = ProductRestControllerTest.ProductRestControllerSapientGeneratedTestConfig.class)
class ProductRestControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private IProductRequestMapper productRequestMapperMock;

    @MockBean
    private IProductServicePort productServicePortMock;

    private AutoCloseable autoCloseableMocks;

    @BeforeEach
    public void beforeTest() {
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new ProductRestController(productServicePortMock, productRequestMapperMock)).build();
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
        product.setProductId(1L);
        product.setProductName("Diadema");

        ProductRequest productRequest = new ProductRequest(
                "Diadema",
                "Diadema con sonido envolvente",
                5,
                1000.0,
                11L,
                List.of(1L, 2L, 3L)
        );

        doReturn(product).when(productRequestMapperMock).productRequestToProduct(productRequest);
        doNothing().when(productServicePortMock).saveProduct(product);

        String contentStr = new ObjectMapper().writeValueAsString(productRequest);

        ResultActions resultActions = this.mockMvc.perform(post("/products")
                .content(contentStr)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk());
    }

    @SpringBootApplication(scanBasePackageClasses = ProductRestController.class)
    static class ProductRestControllerSapientGeneratedTestConfig {
    }
}