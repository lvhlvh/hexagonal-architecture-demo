package com.example.adapter.in.rest.cart;

import static com.example.adapter.in.rest.common.ControllerCommons.clientErrorException;

import com.example.application.port.in.cart.AddToCartCommand;
import com.example.application.port.in.cart.AddToCartUseCase;
import com.example.application.port.in.cart.ProductNotFoundException;
import com.example.domain.cart.Cart;
import com.example.domain.cart.NotEnoughItemsInStockException;
import com.example.domain.customer.CustomerId;
import com.example.domain.product.ProductId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
class AddToCartController {

  private final AddToCartUseCase addToCartUseCase;

  @PostMapping("/{customerId}/line-items")
  public CartWebModel addLineItem(
      @PathVariable("customerId") String customerIdString,
      @RequestParam("productId") String productIdString,
      @RequestParam("quantity") int quantity) {
    CustomerId customerId = new CustomerId(Integer.parseInt(customerIdString));
    ProductId productId = new ProductId(productIdString);

    try {
      Cart cart = addToCartUseCase.addToCart(new AddToCartCommand(customerId, productId, quantity));
      return CartWebModel.fromDomainModel(cart);
    } catch (ProductNotFoundException e) {
      throw clientErrorException(HttpStatus.BAD_REQUEST, "The requested product does not exist");
    } catch (NotEnoughItemsInStockException e) {
      throw clientErrorException(
          HttpStatus.BAD_REQUEST, "Only %d items in stock".formatted(e.itemsInStock()));
    }
  }
}
