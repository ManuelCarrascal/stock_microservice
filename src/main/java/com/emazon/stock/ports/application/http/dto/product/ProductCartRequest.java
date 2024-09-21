package com.emazon.stock.ports.application.http.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCartRequest {
    private List<Long> productIds;
    private String categoryName;
    private String brandName;

}
