package com.example.foodshopbe.respons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductListResponse {
    private List<ProductResponse> products;
    private PaginationInfo pagination;

}
