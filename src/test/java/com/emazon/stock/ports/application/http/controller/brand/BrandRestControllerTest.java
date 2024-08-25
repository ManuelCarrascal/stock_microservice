package com.emazon.stock.ports.application.http.controller.brand;

import org.junit.jupiter.api.Timeout;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.emazon.stock.domain.model.Brand;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.emazon.stock.domain.api.IBrandServicePort;
import org.springframework.http.MediaType;
import org.mockito.MockitoAnnotations;
import com.emazon.stock.ports.application.http.mapper.brand.IBrandRequestMapper;
import com.emazon.stock.ports.application.http.dto.brand.BrandRequest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.doReturn;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
@WebMvcTest()
@ContextConfiguration(classes = BrandRestController.class)
class BrandRestControllerTest {

    private MockMvc mockMvc;

    @MockBean(name = "brandRequestMapper")
    private IBrandRequestMapper brandRequestMapperMock;

    @MockBean(name = "brandServicePort")
    private IBrandServicePort brandServicePortMock;

    private AutoCloseable autoCloseableMocks;

    @BeforeEach()
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new BrandRestController(brandServicePortMock, brandRequestMapperMock)).build();
    }

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
    void saveBrandTest() throws Exception {
        // Arrange
        BrandRequest brandRequest = new BrandRequest("Samsumg", "Samsumg description"); // Example request data
        Brand brand = new Brand(); // Mocked Brand object

        doReturn(brand).when(brandRequestMapperMock).brandRequestToBrand(brandRequest);
        doNothing().when(brandServicePortMock).saveBrand(brand);

        String contentStr = new ObjectMapper().writeValueAsString(brandRequest);

        // Act
        ResultActions resultActions = this.mockMvc.perform(post("/brands")
                .content(contentStr)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // Assert
        resultActions.andExpect(status().isCreated());
    }
    @SpringBootApplication(scanBasePackageClasses = BrandRestController.class)
    static class BrandRestControllerSapientGeneratedTestConfig {
    }
}
