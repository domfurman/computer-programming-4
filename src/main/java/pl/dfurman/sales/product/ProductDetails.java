package pl.dfurman.sales.product;

import pl.dfurman.productcatalog.ProductCatalog;
import java.math.BigDecimal;


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
