package com.example.domain.product;

import com.example.domain.money.Money;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/** A product listed in the shop. */
@Data
@Accessors(fluent = true)
@AllArgsConstructor
public class Product {

  private final ProductId id;
  private String name;
  private String description;
  private Money price;
  private int itemsInStock;
}
