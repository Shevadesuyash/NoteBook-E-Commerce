package com.ecommerce.notebooksite.service;

import com.ecommerce.notebooksite.entity.Basket;
import com.ecommerce.notebooksite.entity.BasketItem;
import com.ecommerce.notebooksite.model.BasketItemResponse;
import com.ecommerce.notebooksite.model.BasketResponse;
import com.ecommerce.notebooksite.repository.BasketRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class BasketServiceImpl implements BasketService {

  private final BasketRepository basketRepository;

  public BasketServiceImpl(BasketRepository basketRepository) {
    this.basketRepository = basketRepository;
  }

  @Override
  public List<BasketResponse> getAllBasket() {
    log.info("fetching all basket ");
    List<Basket> basketList = (List<Basket>) basketRepository.findAll();

    List<BasketResponse> responses =
        basketList.stream().map(this::convertToBasketResponse).collect(Collectors.toList());
    return responses;
  }

  @Override
  public BasketResponse getBasketById(String basketId) {
    log.info("fetching  basket by id : " + basketId);
    Optional<Basket> basketOptional = basketRepository.findById(basketId);
    if (basketOptional.isPresent()) {
      Basket basket = basketOptional.get();
      return convertToBasketResponse(basket);
    } else {
      return null;
    }
  }

  @Override
  public void deleteBasketById(String basketId) {
    log.info("deleting basket by id : " + basketId);
    basketRepository.deleteById(basketId);
  }

  @Override
  public BasketResponse createBasket(Basket basket) {
    log.info("creating basket ");
    Basket savedBasket = basketRepository.save(basket);
    return convertToBasketResponse(savedBasket);
  }

  private BasketResponse convertToBasketResponse(Basket basket) {
    if (basket == null) return null;

    List<BasketItemResponse> itemResponses =
        basket.getItems().stream()
            .map(this::convertToBasketItemResponse)
            .collect(Collectors.toList());

    return BasketResponse.builder().id(basket.getId()).items(itemResponses).build();
  }

  private BasketItemResponse convertToBasketItemResponse(BasketItem basketItem) {
    return BasketItemResponse.builder()
        .id(basketItem.getId())
        .name(basketItem.getName())
        .description(basketItem.getDescription())
        .price(basketItem.getPrice())
        .productType(basketItem.getProductType())
        .pictureUrl(basketItem.getPictureUrl())
        .productBrand(basketItem.getProductBrand())
        .quantity(basketItem.getQuantity())
        .build();
  }
}
