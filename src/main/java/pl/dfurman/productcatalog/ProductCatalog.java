package pl.dfurman.productcatalog;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ProductCatalog {

    //Biznes

    //Tech
    HashMapProductStorage productStorage;


    public ProductCatalog(ListProductStorage listProductStorage) {
        this.productStorage = new HashMapProductStorage();;
    }

    public List<Product> allProducts() {
        return productStorage.allProducts();
    }


    public String addProduct(String name, String desc) {
        Product newOne = new Product(
                UUID.randomUUID(),
                name,
                desc
        );
        productStorage.add(newOne);
        return newOne.getId();
    }

    public Product loadById(String productId) {
        return products.get(productId);
    }

    public List<Product> allPublishedCatalogs() {
        return Collections.emptyList();
    }

    public void changePrice(String productId, BigDecimal newPrice) {
        Product loaded = this.loadById(productId);
        loaded.changePrice(newPrice);
    }

    public void publish(String productId) {
        Product loaded = this.loadById(productId);

        if (loaded.getPrice() == null) {
            throw new ProductCantBePublishedException();
        }

        if (loaded.getImageKey() == null) {
            throw new ProductCantBePublishedException();
        }

        loaded.setOnline();
    }

    public void assignImage(String productId, String path) {

    }

    public List<Product> allPublishedProducts() {
        return Collections.emptyList();
    }
}
