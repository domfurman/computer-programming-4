package pl.dfurman.sales;

import java.util.Optional;

public class Cart {
    int itemsCount;
    public Cart() {}
    public static Cart empty() {
        return new Cart();
    }

    public void add(ProductDetails productDetails) {

    }

    public int itemsCount() {
        return itemsCount++;
    }
}
