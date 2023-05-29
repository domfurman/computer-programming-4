package pl.dfurman.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public class CollectingProductsTest {

    CartStorage cartStorage;
    List<ProductDetails> productDetails;
    ProductDetailsProvider productDetailsProvider;

    @BeforeEach
    void setup() {
        cartStorage = new CartStorage();
        productDetails = new ArrayList<>();
    }

    @Test
    void itAllowsToAddProductToCart() {

        //Arrange
        Sales sales = thereIsSalesModule();
        String customerId = thereIsCustomer("Dominik");
        String productId = thereIsProduct("puzzle", BigDecimal.valueOf(15));
        //Act
        sales.addToCart(customerId, productId);

        //Assert
        assertCustomerCartContainsNProducts(customerId, 1);
        //assertEquals(1, sales.itemsAmount(customerId));
    }

    private void assertCustomerCartContainsNProducts(String customerId, int productsCount) {
        Cart customerCart = cartStorage.load(customerId).get();
        assert customerCart.itemsCount() == productsCount;
    }

    private String thereIsProduct(String name, BigDecimal price) {
        productDetails.add(
                new ProductDetails(UUID.randomUUID().toString(), name, price)
        );
        return name;
    }

    private String thereIsCustomer(String id) {
        return id;
    }

    private Sales thereIsSalesModule() {
        return new Sales(cartStorage, productDetailsProvider);
    }
}
