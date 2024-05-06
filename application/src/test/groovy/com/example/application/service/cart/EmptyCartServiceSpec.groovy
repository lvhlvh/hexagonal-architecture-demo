package com.example.application.service.cart

import com.example.application.port.out.cart.CartRepository
import spock.lang.Specification
import spock.lang.Subject

@Subject([EmptyCartService])
class EmptyCartServiceSpec extends Specification {

    CartRepository cartRepository = Mock()

    @Subject
    EmptyCartService emptyCartService = new EmptyCartService(cartRepository)

    def testEmptyCart() {
        when:
        emptyCartService.emptyCart(null)

        then:
        thrown(NullPointerException)
    }
}
