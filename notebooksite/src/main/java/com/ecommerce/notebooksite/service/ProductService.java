package com.ecommerce.notebooksite.service;

import com.ecommerce.notebooksite.model.BrandResponse;
import com.ecommerce.notebooksite.model.ProductResponse;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
