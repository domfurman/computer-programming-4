package pl.dfurman.productcatalog;

import java.util.*;

public class ProductCatalog {

    private ArrayList<Product> products;

    public ProductCatalog() {
        this.products = new ArrayList<>();;
    }

    public List<Product> allProducts() {
        return Collections.emptyList();
    }


    public String addProduct(String name, String desc) {
        Product newOne = new Product(
                UUID.randomUUID(),
                name,
                desc
        );
        products.add(newOne);
        return newOne.getId();
    }
}
