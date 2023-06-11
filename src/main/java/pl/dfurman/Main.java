package pl.dfurman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import pl.dfurman.payu.PayU;
import pl.dfurman.productcatalog.HashMapProductStorage;
import pl.dfurman.productcatalog.Product;
import pl.dfurman.productcatalog.ProductCatalog;
import pl.dfurman.sales.*;
import pl.dfurman.sales.cart.CartStorage;
import pl.dfurman.sales.offer.OfferCalculator;
import pl.dfurman.sales.product.ProductCatalogProductDetailsProvider;
import pl.dfurman.sales.product.ProductDetails;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    ProductCatalog createMyProductCatalog() {
        ProductCatalog productCatalog = new ProductCatalog(new HashMapProductStorage());
        String product1 = productCatalog.addProduct("How to think less", "psychological");
        productCatalog.changePrice(product1, BigDecimal.valueOf(100));
        productCatalog.assignImage(product1, "foo/niece/image.jpg");
        productCatalog.publish(product1);

        String product2 = productCatalog.addProduct("Song of Ice and Fire", "fantasy");
        productCatalog.changePrice(product2, BigDecimal.valueOf(150));
        productCatalog.assignImage(product2, "foo/niece/image.jpg");
        productCatalog.publish(product2);

        return productCatalog;
    }

    @Bean
    Sales createSalesViaObjects(ProductCatalog catalog) {
        return new Sales(new CartStorage(),
                new ProductCatalogProductDetailsProvider(catalog),
                new PayU(new RestTemplate()),
                new OfferCalculator());
    }

    Sales createSalesViaLambda(ProductCatalog catalog) {
        return new Sales(
                new CartStorage(),
                (String productId) -> {
                    Product product = catalog.loadById(productId);
                    if (product == null) {
                        return Optional.empty();
                    }
                    return Optional.of(new ProductDetails(
                            product.getId(),
                            product.getName(),
                            product.getPrice()
                    ));},
                new PayU(new RestTemplate()),
                new OfferCalculator());
    }
}