package com.example.application.service.cart

import com.example.application.port.in.cart.AddToCartCommand
import com.example.application.port.out.cart.CartRepository
import com.example.application.port.out.product.ProductRepository
import jakarta.validation.ConstraintViolationException
import spock.lang.Specification
import spock.lang.Subject

@Subject([AddToCartService])
class AddToCartServiceSpec extends Specification {

    CartRepository cartRepository = Mock()
    ProductRepository productRepository = Mock()

    @Subject
    AddToCartService addToCartService = new AddToCartService(cartRepository, productRepository)

    def testAddToCart() {
        when:
        addToCartService.addToCart(new AddToCartCommand(null, null, 0))

        then:
        thrown(ConstraintViolationException)
    }


}
