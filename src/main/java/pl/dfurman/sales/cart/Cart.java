package pl.dfurman.sales.cart;

import pl.dfurman.sales.product.ProductDetails;

import java.math.BigDecimal;
import java.util.*;

public class Cart {

//    List<String> products;
    private HashMap<String, Integer> products;


    public Cart() {
        this.products = new HashMap<>();
    }

    public static Cart empty() {
        return new Cart();
    }

    public void addProduct(String productId) {
        if (!isProductInCart(productId)) {
            addToCart(productId);
        } else {
            increaseQuantity(productId);
        }
    }

    public int itemsCount() {
        int size = 0;
        for (String key : products.keySet()) {
            size += products.get(key);
        }
        return size;
    }

    public List<String> getProducts() {
        List<String> productsList = new ArrayList<>();
        for (String key : products.keySet()) {
            productsList.add(key);
        }
        return productsList;
    }

    public boolean isProductInCart(String productId) {
        return products.containsKey(productId);
    }

    public void addToCart(String productId) {
        products.put(productId, 1);
    }

    public void increaseQuantity(String productId) {
        products.put(productId, products.get(productId) + 1);
    }

    public BigDecimal getProductQuantity(String productId) {
        return BigDecimal.valueOf(products.get(productId));
    }
}
