package pl.dfurman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.dfurman.productcatalog.HashMapProductStorage;
import pl.dfurman.productcatalog.ProductCatalog;
import pl.dfurman.sales.CartStorage;
import pl.dfurman.sales.ProductDetailsProvider;
import pl.dfurman.sales.Sales;

import java.math.BigDecimal;

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
    Sales createSale() {
        return new Sales(new CartStorage(), new ProductDetailsProvider());
    }
}
