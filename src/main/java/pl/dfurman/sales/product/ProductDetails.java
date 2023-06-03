package pl.dfurman.sales.product;

import pl.dfurman.productcatalog.Product;
import pl.dfurman.productcatalog.ProductCatalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProductDetails {

    String id;
    String name;
    BigDecimal price;
    ProductCatalog productCatalog;
    public ProductDetails(String productId, String name, BigDecimal price) {
        this.id = productId;
        this.name = name;
        this.price = price;
    }

    //public Optional<ProductDetails> load(String productId) {
    //    //Optional<Product> product = productCatalog.allProducts().stream().filter(p -> p.getId().equals(productId)).findFirst();
    //    Product product = productCatalog.loadById(productId);
//
    //    if (product == null) {
    //        return Optional.empty();
    //    }
    //    return Optional.of(new ProductDetails(
    //            product.getId(),
    //            product.getName(),
    //            product.getPrice()
    //    ));
    //}

    //public Optional<Product> findProductById(String id) {
    //    return productCatalog.allProducts().stream().filter(p -> p.getId().equals(id)).findFirst();
    //}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
