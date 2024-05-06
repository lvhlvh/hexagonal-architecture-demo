package com.example.application.port.out.cart;

import com.example.domain.cart.Cart;
import com.example.domain.customer.CustomerId;
import java.util.Optional;

/** Outgoing persistence port for carts. */
public interface CartRepository {

  void save(Cart cart);

  Optional<Cart> findByCustomerId(CustomerId customerId);

  void deleteByCustomerId(CustomerId customerId);
}
