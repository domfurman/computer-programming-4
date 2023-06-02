package pl.dfurman.sales.product;

import pl.dfurman.sales.product.ProductDetails;

import java.util.Optional;

public interface ProductDetailsProvider {
    public Optional<ProductDetails> load(String productId);
}
