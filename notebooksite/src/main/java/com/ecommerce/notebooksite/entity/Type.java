package com.ecommerce.notebooksite.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Type")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Type {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
  private List<Product> products;
}
