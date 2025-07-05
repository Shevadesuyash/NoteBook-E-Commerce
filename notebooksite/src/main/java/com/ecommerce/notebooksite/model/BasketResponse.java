package com.ecommerce.notebooksite.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketResponse {
    private String id;
    private List<BasketItemResponse> items;
}
