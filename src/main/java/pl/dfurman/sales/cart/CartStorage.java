package pl.dfurman.sales.cart;

import java.util.HashMap;
import java.util.Optional;

public class CartStorage {

    HashMap<String, Cart> carts;

    public CartStorage() {
        this.carts = new HashMap<>();
    }

    public Optional<Cart> load(String customerId) {
        return Optional.ofNullable(carts.get(customerId));

    }

    public void save(String customerId, Cart customerCart) {
        carts.put(customerId, customerCart);
    }
}
