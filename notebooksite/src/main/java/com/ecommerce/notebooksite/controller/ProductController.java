package com.ecommerce.notebooksite.controller;

import com.ecommerce.notebooksite.model.BrandResponse;
import com.ecommerce.notebooksite.model.ProductResponse;
import com.ecommerce.notebooksite.model.TypeResponse;
import com.ecommerce.notebooksite.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final TypeService typeService;
    private final BrandService brandService;

    public ProductController(ProductService productService, TypeService typeService, BrandService brandService) {
        this.productService = productService;
        this.typeService = typeService;
        this.brandService = brandService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Integer productId){
        ProductResponse productResponse = productService.getProductById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/getProduct")
    public ResponseEntity<List<ProductResponse>> getProduct(){
        List<ProductResponse> responses = productService.getAllProducts();
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }

    @GetMapping("/getBrand")
    public ResponseEntity<List<BrandResponse>> getBrand(){
        List<BrandResponse> responses = brandService.getAllBrands();
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }

    @GetMapping("/getType")
    public ResponseEntity<List<TypeResponse>> getType(){
        List<TypeResponse> responses = typeService.getAllType();
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }


}
