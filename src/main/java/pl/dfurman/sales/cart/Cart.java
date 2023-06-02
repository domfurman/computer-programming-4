package pl.dfurman.sales.cart;

import pl.dfurman.sales.ProductDetails;

import java.util.*;

public class Cart {

    List<String> products;
    int itemsCount;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public static Cart empty() {
        return new Cart();
    }

    public void add(ProductDetails productDetails) {
        products.add(productDetails.getProductId());
    }

    public int itemsCount() {
        return products.size();
    }
}
