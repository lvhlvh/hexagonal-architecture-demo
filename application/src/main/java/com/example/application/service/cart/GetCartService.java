package com.example.application.service.cart;

import com.example.application.port.in.cart.GetCartUseCase;
import com.example.application.port.out.cart.CartRepository;
import com.example.domain.cart.Cart;
import com.example.domain.customer.CustomerId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/** Use case implementation: Retrieving a shopping cart. */
@Slf4j
@Service
@RequiredArgsConstructor
class GetCartService implements GetCartUseCase {

  private final CartRepository cartRepository;

  @Override
  public Cart getCart(@NonNull CustomerId customerIdVeryLong) {
    return cartRepository
        .findByCustomerId(customerIdVeryLong)
        .orElseGet(() -> new Cart(customerIdVeryLong));
  }
}
