package com.example.application.port.out.product;

import com.example.domain.product.Product;
import com.example.domain.product.ProductId;
import java.util.List;
import java.util.Optional;

/** Outgoing persistence port for products. */
public interface ProductRepository {

  void save(Product product);

  Optional<Product> findById(ProductId productId);

  List<Product> findByNameOrDescription(String query);
}
