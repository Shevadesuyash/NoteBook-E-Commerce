package com.ecommerce.notebooksite.entity;



import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("Basket")
@NoArgsConstructor
public class Basket {

  @Id
  private String id;
  private List<BasketItem> items =new ArrayList<>();

  public Basket(String id){
    this.id =id;
  }
}
