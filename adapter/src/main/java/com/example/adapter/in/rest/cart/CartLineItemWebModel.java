package com.example.adapter.in.rest.cart;

import com.example.domain.cart.CartLineItem;
import com.example.domain.money.Money;
import com.example.domain.product.Product;

/**
 * Model class for returning a shopping cart line item via REST API.
 *
 * @author Sven Woltmann
 */
record CartLineItemWebModel(String productId, String productName, Money price, int quantity) {

  public static CartLineItemWebModel fromDomainModel(CartLineItem lineItem) {
    Product product = lineItem.product();
    return new CartLineItemWebModel(
        product.id().value(), product.name(), product.price(), lineItem.quantity());
  }
}
