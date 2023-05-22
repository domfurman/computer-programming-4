package pl.dfurman.sales;

import pl.dfurman.productcatalog.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
    private List<ProductDetails> items;
    int itemsCount;
    public Cart() {
        this.items = new ArrayList<>();
    }
    public static Cart empty() {
        return new Cart();
    }

    public void add(ProductDetails productDetails) {
        items.add(productDetails);
    }

    public int itemsCount() {
        return items.size();
    }
}
