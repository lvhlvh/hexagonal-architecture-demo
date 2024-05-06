package com.example.application.port.in.product;

import com.example.domain.product.Product;
import java.util.List;

/** Use case: Finding products via a search query. */
public interface FindProductsUseCase {

  List<Product> findByNameOrDescription(String query);
}
