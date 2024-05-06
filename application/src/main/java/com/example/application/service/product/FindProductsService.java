package com.example.application.service.product;

import com.example.application.port.in.product.FindProductsUseCase;
import com.example.application.port.out.product.ProductRepository;
import com.example.domain.product.Product;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/** Use case implementation: Finding products via a search query. */
@Slf4j
@Service
@RequiredArgsConstructor
class FindProductsService implements FindProductsUseCase {

  private final ProductRepository productRepository;

  @Override
  public List<Product> findByNameOrDescription(String query) {
    Objects.requireNonNull(query, "'query' must not be null");
    if (query.length() < 2) {
      throw new IllegalArgumentException("'query' must be at least two characters long");
    }

    return productRepository.findByNameOrDescription(query);
  }
}
