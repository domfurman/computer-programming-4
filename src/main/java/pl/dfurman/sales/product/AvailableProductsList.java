package pl.dfurman.sales.product;

import java.util.List;
import java.util.Optional;

public class AvailableProductsList implements ProductDetailsProvider {
    private List<ProductDetails> availableProducts;

    public AvailableProductsList(List<ProductDetails> availableProducts) {
        this.availableProducts = availableProducts;
    }

    @Override
    public Optional<ProductDetails> load(String productId) {
        return availableProducts.stream().filter(p -> p.getId().equals(productId)).findFirst();
    }
}
