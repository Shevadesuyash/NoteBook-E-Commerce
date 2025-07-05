package com.ecommerce.notebooksite.repository;

import com.ecommerce.notebooksite.entity.Basket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends CrudRepository<Basket, String> {}
