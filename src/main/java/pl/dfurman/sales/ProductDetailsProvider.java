package pl.dfurman.sales;

import java.util.Optional;

public class ProductDetailsProvider {
    public Optional<ProductDetails> load(String productId) {
        //return productId;
        return Optional.empty();
        //return null;
    }
}
