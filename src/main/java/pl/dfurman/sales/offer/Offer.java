package pl.dfurman.sales.offer;

import pl.dfurman.sales.cart.Cart;

import java.math.BigDecimal;

public class Offer {
    BigDecimal total;
    Integer itemsCount;

    public Offer() {
        BigDecimal total = BigDecimal.ZERO;
        Integer itemsCount = 0;
    }

    //public Offer calculate(Cart cart) {
    //    BigDecimal amount = cart.getProducts().stream().map(product -> product.)
    //}

    public BigDecimal getTotal() {
        return total;
    }

    public Integer getItemsCount() {
        return itemsCount;
    }
}
