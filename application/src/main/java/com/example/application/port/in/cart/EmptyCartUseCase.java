package com.example.application.port.in.cart;

import com.example.domain.customer.CustomerId;

/** Use case: Emptying a shopping cart. */
public interface EmptyCartUseCase {

  void emptyCart(CustomerId customerId);
}
