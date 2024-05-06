package com.example.application.service.cart;

import com.example.application.port.in.cart.AddToCartCommand;
import com.example.application.port.in.cart.AddToCartUseCase;
import com.example.application.port.in.cart.ProductNotFoundException;
import com.example.application.port.out.cart.CartRepository;
import com.example.application.port.out.product.ProductRepository;
import com.example.domain.cart.Cart;
import com.example.domain.cart.NotEnoughItemsInStockException;
import com.example.domain.product.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/** Use case implementation: Adding a product to a shopping cart. */
@Slf4j
@Service
@RequiredArgsConstructor
class AddToCartService implements AddToCartUseCase {

  private final CartRepository cartRepository;
  private final ProductRepository productRepository;

  @Override
  public Cart addToCart(AddToCartCommand addToCartCommand)
      throws ProductNotFoundException, NotEnoughItemsInStockException {
    Product product =
        productRepository
            .findById(addToCartCommand.productId())
            .orElseThrow(ProductNotFoundException::new);

    Cart cart =
        cartRepository
            .findByCustomerId(addToCartCommand.customerId())
            .orElseGet(() -> new Cart(addToCartCommand.customerId()));

    cart.addProduct(product, addToCartCommand.quantity());

    cartRepository.save(cart);

    return cart;
  }
}
