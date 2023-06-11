package pl.dfurman.sales.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AvailableProductsList implements ProductDetailsProvider {
    private List<ProductDetails> availableProducts;

    public AvailableProductsList() {
        this.availableProducts = new ArrayList<>();
    }

    @Override
    public Optional<ProductDetails> load(String productId) {
        return availableProducts.stream().filter(p -> p.getId().equals(productId)).findFirst();
    }

    public void add(ProductDetails productDetails) {
        this.availableProducts.add(productDetails);
    }
}
