package pl.dfurman.sales.offer;

import pl.dfurman.sales.cart.Cart;

import java.math.BigDecimal;

public class OfferCalculator {
    Cart cart;

    public Offer calculateOffer(Cart cart) {
        BigDecimal amount = cart.getProducts().stream().map(product -> product.)
    }
}
