package pl.dfurman.sales;

import pl.dfurman.productcatalog.Product;

import java.util.*;

public class Cart {
    Map<String, ProductDetails> items;
    int itemsCount;
    public Cart() {
        this.items = new HashMap<>();
    }
    public static Cart empty() {
        return new Cart();
    }

    public void add(ProductDetails productDetails) {
        items.put(productDetails.getProductId(), productDetails);
    }

    public int itemsCount() {
        return items.size();
    }
}
