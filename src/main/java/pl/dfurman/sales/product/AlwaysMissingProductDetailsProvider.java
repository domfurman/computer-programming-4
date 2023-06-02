package pl.dfurman.sales.product;

import java.util.Optional;

public class AlwaysMissingProductDetailsProvider implements ProductDetailsProvider {
    @Override
    public Optional<ProductDetails> load(String productId) {
        //return productId;
        return Optional.empty();
        //return null;
    }
}
