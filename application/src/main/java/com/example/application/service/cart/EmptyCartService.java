package com.example.application.service.cart;

import com.example.application.port.in.cart.EmptyCartUseCase;
import com.example.application.port.out.cart.CartRepository;
import com.example.domain.customer.CustomerId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/** Use case implementation: Emptying a shopping cart. */
@Slf4j
@Service
@RequiredArgsConstructor
class EmptyCartService implements EmptyCartUseCase {

  private final CartRepository cartRepository;

  @Override
  public void emptyCart(@NonNull CustomerId customerId) {
    cartRepository.deleteByCustomerId(customerId);
  }
}
