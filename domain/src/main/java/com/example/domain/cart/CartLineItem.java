package com.example.domain.cart;

import com.example.domain.money.Money;
import com.example.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/** A shopping cart line item with a product and quantity. */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class CartLineItem {

  private final Product product;
  private int quantity;

  public void increaseQuantityBy(int augend, int itemsInStock)
      throws NotEnoughItemsInStockException {
    if (augend < 1) {
      throw new IllegalArgumentException("You must add at least one item");
    }

    int newQuantity = quantity + augend;
    if (itemsInStock < newQuantity) {
      throw new NotEnoughItemsInStockException(
          "Product %s has less items in stock (%d) than the requested total quantity (%d)"
              .formatted(product.id(), product.itemsInStock(), newQuantity),
          product.itemsInStock());
    }

    this.quantity = newQuantity;
  }

  public Money subTotal() {
    return product.price().multiply(quantity);
  }
}
