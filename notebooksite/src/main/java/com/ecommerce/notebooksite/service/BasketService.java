package com.ecommerce.notebooksite.service;

import com.ecommerce.notebooksite.entity.Basket;
import com.ecommerce.notebooksite.model.BasketResponse;
import java.util.List;

public interface BasketService {
  List<BasketResponse> getAllBasket();

  BasketResponse getBasketById(String BasketId);

  void deleteBasketById(String BasketId);

  BasketResponse createBasket(Basket basket);
}
