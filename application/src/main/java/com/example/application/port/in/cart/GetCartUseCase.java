package com.example.application.port.in.cart;

import com.example.domain.cart.Cart;
import com.example.domain.customer.CustomerId;

/** Use case: Retrieving a shopping cart. */
public interface GetCartUseCase {

  Cart getCart(CustomerId customerId);
}
