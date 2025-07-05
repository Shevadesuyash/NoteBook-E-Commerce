package com.ecommerce.notebooksite.service;

import com.ecommerce.notebooksite.model.ProductResponse;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
  List<ProductResponse> getAllProducts();

  ProductResponse getProductById(Integer productId);

  Page<ProductResponse> getProducts(Pageable pageable);

  List<ProductResponse> searchProductByName(String keyWord);

  List<ProductResponse> searchProductByBrandTypeAndName(Integer brandId, Integer typeId, String keyword);

  List<ProductResponse> searchProductByBrandAndType(Integer brandId, Integer typeId);

  List<ProductResponse> searchProductByBrand(Integer brandId);

  List<ProductResponse> searchProductByType(Integer typeId);
}
