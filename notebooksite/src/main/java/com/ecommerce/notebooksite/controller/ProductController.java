package com.ecommerce.notebooksite.controller;

import com.ecommerce.notebooksite.model.BrandResponse;
import com.ecommerce.notebooksite.model.ProductResponse;
import com.ecommerce.notebooksite.model.TypeResponse;
import com.ecommerce.notebooksite.service.*;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api")
public class ProductController {

  private final ProductService productService;
  private final TypeService typeService;
  private final BrandService brandService;

  public ProductController(
      ProductService productService, TypeService typeService, BrandService brandService) {
    this.productService = productService;
    this.typeService = typeService;
    this.brandService = brandService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Integer productId) {
    ProductResponse productResponse = productService.getProductById(productId);
    return new ResponseEntity<>(productResponse, HttpStatus.OK);
  }

  @GetMapping("/getProduct")
  public ResponseEntity<List<ProductResponse>> getProduct() {
    List<ProductResponse> responses = productService.getAllProducts();
    return new ResponseEntity<>(responses, HttpStatus.OK);
  }

  @GetMapping("/getProducts")
  public ResponseEntity<Page<ProductResponse>> getProducts(
      @PageableDefault(size = 10) Pageable pageable,
      @RequestParam(name = "keyword", required = false) String keyword,
      @RequestParam(name = "brandId", required = false) Integer brandId,
      @RequestParam(name = "typeId", required = false) Integer typeId,
      @RequestParam(name = "sort", defaultValue = "name") String sort,
      @RequestParam(name = "order", defaultValue = "asc") String order) {

    Page<ProductResponse> responses;

    Sort.Direction direction = "asc".equalsIgnoreCase(order) ? Sort.Direction.ASC : Sort.Direction.DESC;
    Sort sorting = Sort.by(direction, sort);

    log.info("keyWord : "+keyword);
    log.info("brandId : "+brandId);
      log.info("typeId : "+typeId);
      log.info("sort : "+sort);
      log.info("order : "+order);



    if (brandId != null && typeId != null && keyword != null && !keyword.isEmpty()) {
      List<ProductResponse> productResponsesList =
          productService.searchProductByBrandTypeAndName(brandId, typeId, keyword);
      responses = new PageImpl<>(productResponsesList, pageable, productResponsesList.size());
    } else if (brandId != null && typeId != null) {
      List<ProductResponse> productResponsesList =
          productService.searchProductByBrandAndType(brandId, typeId);
      responses = new PageImpl<>(productResponsesList, pageable, productResponsesList.size());
    } else if (brandId != null) {
      List<ProductResponse> productResponsesList = productService.searchProductByBrand(brandId);
      responses = new PageImpl<>(productResponsesList, pageable, productResponsesList.size());
    } else if (typeId != null) {
      List<ProductResponse> productResponsesList = productService.searchProductByType(typeId);
      responses = new PageImpl<>(productResponsesList, pageable, productResponsesList.size());
    } else if (keyword != null && !keyword.isEmpty()) {
      List<ProductResponse> productResponsesList = productService.searchProductByName(keyword);
      responses = new PageImpl<>(productResponsesList, pageable, productResponsesList.size());
    } else {
      responses = productService.getProducts(
              PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sorting));
    }

    return new ResponseEntity<>(responses, HttpStatus.OK);
  }

  @GetMapping("/getBrand")
  public ResponseEntity<List<BrandResponse>> getBrand() {
    List<BrandResponse> responses = brandService.getAllBrands();
    return new ResponseEntity<>(responses, HttpStatus.OK);
  }

  @GetMapping("/getType")
  public ResponseEntity<List<TypeResponse>> getType() {
    List<TypeResponse> responses = typeService.getAllType();
    return new ResponseEntity<>(responses, HttpStatus.OK);
  }

  @GetMapping("/search/{key}")
  public ResponseEntity<List<ProductResponse>> searchProduct(@PathVariable("key") String key) {
    List<ProductResponse> responses = productService.searchProductByName(key);
    return new ResponseEntity<>(responses, HttpStatus.OK);
  }
}
