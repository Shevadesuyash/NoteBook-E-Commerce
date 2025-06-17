package com.ecommerce.notebooksite.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
  private Integer id;
  private String name;
  private String description;
  private Long price;
  private String pictureUrl;
  private String productType;
  private String productBrand;
}
