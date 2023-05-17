package pl.dfurman.sales;

import java.util.Optional;

public class CartStorage {
    public Optional<Cart> cart;
    public Optional<Cart> load(String customerId) {
        return Optional.of(null);
    }

    public void save(String customerId, Cart cart) {

    }
}
