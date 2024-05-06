package com.example.application.port.in.cart;

import static com.example.common.Validation.validate;

import com.example.domain.customer.CustomerId;
import com.example.domain.product.ProductId;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AddToCartCommand(
    @NotNull CustomerId customerId, @NotNull ProductId productId, @Min(1L) int quantity) {

  public AddToCartCommand(
      @NotNull CustomerId customerId, @NotNull ProductId productId, @Min(1L) int quantity) {
    this.customerId = customerId;
    this.productId = productId;
    this.quantity = quantity;
    validate(this);
  }
}
