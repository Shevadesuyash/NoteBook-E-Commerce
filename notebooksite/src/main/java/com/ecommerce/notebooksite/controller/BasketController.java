package com.ecommerce.notebooksite.controller;

import com.ecommerce.notebooksite.entity.Basket;
import com.ecommerce.notebooksite.entity.BasketItem;
import com.ecommerce.notebooksite.model.BasketItemResponse;
import com.ecommerce.notebooksite.model.BasketResponse;
import com.ecommerce.notebooksite.service.BasketService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/baskets")
public class BasketController {
  private final BasketService basketService;

  public BasketController(BasketService basketService) {
    this.basketService = basketService;
  }

  @GetMapping()
  public List<BasketResponse> getAllBaskets() {
    return basketService.getAllBasket();
  }

  @GetMapping("/{basketId}")
  public BasketResponse getBasketById(@PathVariable String basketId) {
    return basketService.getBasketById(basketId);
  }

  @DeleteMapping("/{basketId}")
  public void deleteById(@PathVariable String basketId) {
    basketService.deleteBasketById(basketId);
  }

  @PostMapping()
  public ResponseEntity<BasketResponse> createBasket(@RequestBody BasketResponse basketResponse) {
    Basket basket = convertToBasketEntity(basketResponse);
    BasketResponse createdBasket = basketService.createBasket(basket);
    return new ResponseEntity<>(createdBasket, HttpStatus.CREATED);
  }

  private Basket convertToBasketEntity(BasketResponse basketResponse) {
    Basket basket = new Basket();
    basket.setId(basketResponse.getId());
    basket.setItems(mapBasketItemResponseToEntity(basketResponse.getItems()));

    return basket;
  }

  private List<BasketItem> mapBasketItemResponseToEntity(List<BasketItemResponse> items) {
    return items.stream().map(this::convertToBasketItemEntity).collect(Collectors.toList());
  }

  private BasketItem convertToBasketItemEntity(BasketItemResponse basketItemResponse) {
    BasketItem basketItem = new BasketItem();
    basketItem.setId(basketItemResponse.getId());
    basketItem.setName(basketItemResponse.getName());
    basketItem.setDescription(basketItemResponse.getDescription());
    basketItem.setPrice(basketItemResponse.getPrice());
    basketItem.setPictureUrl(basketItemResponse.getPictureUrl());
    basketItem.setQuantity(basketItemResponse.getQuantity());
    basketItem.setProductBrand(basketItemResponse.getProductBrand());
    basketItem.setProductType(basketItemResponse.getProductType());
    return basketItem;
  }
}
