package com.ecommerce.notebooksite.service;

import com.ecommerce.notebooksite.entity.Product;
import com.ecommerce.notebooksite.model.ProductResponse;
import com.ecommerce.notebooksite.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final BrandService brandService;
  private final TypeService typeService;

  public ProductServiceImpl(ProductRepository ProductRepository, BrandService brandService, TypeService typeService) {
    this.productRepository = ProductRepository;
      this.brandService = brandService;
      this.typeService = typeService;
  }

  @Override
  public List<ProductResponse> getAllProducts() {
    log.info("Fetching all Product");
    List<Product> ProductList = productRepository.findAll();

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
      Product product = productRepository.findById(productId)
              .orElseThrow(()->(new RuntimeException("Product with id "+productId+" is not there")));

      ProductResponse responses = convertToProductResponse(product);

      return responses;
  }

  @Override
  public Page<ProductResponse> getProducts(Pageable pageable) {
    Page<Product> productPage = productRepository.findAll(pageable);

    Page<ProductResponse> responses = productPage.map(this::convertToProductResponse);
    return responses;
  }

  @Override
  public List<ProductResponse> searchProductByName(String keyWord) {
    log.info("search by Key : "+keyWord);
    List<Product> listProduct = productRepository.findByName(keyWord);

    List<ProductResponse> responses = listProduct.stream().map(this::convertToProductResponse).toList();
    return  responses;
  }
}
