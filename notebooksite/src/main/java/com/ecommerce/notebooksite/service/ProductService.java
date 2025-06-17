package com.ecommerce.notebooksite.service;

import com.ecommerce.notebooksite.model.BrandResponse;
import com.ecommerce.notebooksite.model.ProductResponse;

import java.util.List;

public interface ProductService {
  List<ProductResponse> getAllProducts();

  ProductResponse getProductById(Integer productId);
}
