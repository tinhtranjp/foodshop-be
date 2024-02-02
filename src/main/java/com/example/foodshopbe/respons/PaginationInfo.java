package com.example.foodshopbe.respons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class PaginationInfo {
    private int total;
    private int page;
    private int limit;

    public PaginationInfo(int total, int page, int limit) {
        this.total = total;
        this.page = page;
        this.limit = limit;
    }
}
