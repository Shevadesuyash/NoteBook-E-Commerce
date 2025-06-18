package com.ecommerce.notebooksite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private Long price;

  @Column(name = "picture_url")
  private String pictureUrl;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_brand_id", referencedColumnName = "Id")
  private Brand brand;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_type_id", referencedColumnName = "Id")
  private Type type;
}
