package pl.dfurman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.dfurman.productcatalog.HashMapProductStorage;
import pl.dfurman.productcatalog.ProductCatalog;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    ProductCatalog createMyProductCatalog() {
        return new ProductCatalog(new HashMapProductStorage());
    }
}
