package com.emazon.stock.ports.application.http.controller.brand;

import com.emazon.stock.domain.api.IBrandServicePort;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.ports.application.http.dto.brand.BrandRequest;
import com.emazon.stock.ports.application.http.mapper.brand.IBrandRequestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
@Tag(name = "Brand", description = "Brand API")
public class BrandRestController {
    private final IBrandServicePort brandServicePort;
    private final IBrandRequestMapper brandRequestMapper;

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
}
