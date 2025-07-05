package com.ecommerce.notebooksite.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketItemResponse {
  private Integer id;
  private String name;
  private Long price;
  private String description;
  private String pictureUrl;
  private String productBrand;
  private String productType;

  private Integer quantity;
}
