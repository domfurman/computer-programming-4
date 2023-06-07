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

    public BigDecimal getTotal() {
        return total;
    }

    public Integer getItemsCount() {
        return itemsCount;
    }
}
