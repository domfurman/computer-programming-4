package pl.dfurman.productcatalog;

import java.util.List;

public interface ProductStorage {
    List<Product> allProducts();

    void add(Product newOne);

    List<Product> allPublishedProducts();

    Product loadById(String productdId);
}
