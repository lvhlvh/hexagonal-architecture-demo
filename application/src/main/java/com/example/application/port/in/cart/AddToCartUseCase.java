package com.example.application.port.in.cart;

import com.example.domain.cart.Cart;
import com.example.domain.cart.NotEnoughItemsInStockException;

/** Use case: Adding a product to a shopping cart. */
public interface AddToCartUseCase {

  Cart addToCart(AddToCartCommand addToCartCommand)
      throws ProductNotFoundException, NotEnoughItemsInStockException;
}
