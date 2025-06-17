package com.ecommerce.notebooksite.repository;

import com.ecommerce.notebooksite.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {}
