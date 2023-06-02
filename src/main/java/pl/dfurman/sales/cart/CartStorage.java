package pl.dfurman.sales.cart;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CartStorage {
    //public Optional<Cart> cart;
    Map<String, Cart> carts;

    public CartStorage() {
        this.carts = new HashMap<>();
    }

    public Optional<Cart> load(String customerId) {
        return Optional.ofNullable(carts.get(customerId));
        //return Optional.of(carts.get(customerId));
    }

    public void save(String customerId, Cart cart) {
        carts.put(customerId, cart);
    }
}
