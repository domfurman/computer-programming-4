package pl.dfurman.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;


public class CollectingProductsTest {

    CartStorage cartStorage;
    ProductDetailsProvider productDetailsProvider;

    @BeforeEach
    void setup() {
        cartStorage = new CartStorage();
        productDetailsProvider = new ProductDetailsProvider();
    }

    @Test
    void itAllowsToAddProductToCart() {

        //Arrange
        Sales sales = thereIsSalesModule();
        String customerId = thereIsCustomer("Kuba");
        String productId = thereIsProduct();
        //Act
        sales.addToCart(customerId, productId);

        //Assert
        assertCustomerCartContainsNProducts(customerId, 1);
    }

    private void assertCustomerCartContainsNProducts(String customerId, int productsCount) {
        Cart customerCart = cartStorage.load(customerId).get();
        assert customerCart.itemsCount() == productsCount;
    }

    private String thereIsProduct() {
        return UUID.randomUUID().toString();
    }

    private String thereIsCustomer(String id) {
        return id;
    }

    private Sales thereIsSalesModule() {
        return new Sales(cartStorage, productDetailsProvider);
    }
}
