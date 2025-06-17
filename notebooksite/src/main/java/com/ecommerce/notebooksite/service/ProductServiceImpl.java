package com.ecommerce.notebooksite.service;

import com.ecommerce.notebooksite.entity.Product;
import com.ecommerce.notebooksite.model.ProductResponse;
import com.ecommerce.notebooksite.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProductServiceImpl implements ProductService {

  private final ProductRepository ProductRepository;

  public ProductServiceImpl(ProductRepository ProductRepository) {
    this.ProductRepository = ProductRepository;
  }

  @Override
  public List<ProductResponse> getAllProducts() {
    log.info("Fetching all Product");
    List<Product> ProductList = ProductRepository.findAll();

    List<ProductResponse> responses =
        ProductList.stream().map(this::convertToProductResponse).collect(Collectors.toList());

    return responses;
  }

  private ProductResponse convertToProductResponse(Product product) {
    return ProductResponse.builder().id(product.getId()).name(product.getName())
            .description(product.getDescription()).pictureUrl(product.getPictureUrl())
            .price(product.getPrice()).productBrand(product.getBrand().getName())
            .productType(product.getType().getName()).build();
  }

  @Override
  public ProductResponse getProductById(Integer productId) {
      log.info("Fetching  Product by id : "+productId);
      Product product = ProductRepository.findById(productId)
              .orElseThrow(()->(new RuntimeException("Product with id "+productId+" is not there")));

      ProductResponse responses = convertToProductResponse(product);

      return responses;
  }
}
