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
