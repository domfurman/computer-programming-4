package pl.dfurman.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.dfurman.sales.cart.Cart;
import pl.dfurman.sales.cart.CartStorage;
import pl.dfurman.sales.product.AlwaysMissingProductDetailsProvider;
import pl.dfurman.sales.product.ProductDetails;
import pl.dfurman.sales.product.ProductDetailsProvider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CollectingProductsTest {

    CartStorage cartStorage;
    List<ProductDetails> productDetails;
    ProductDetailsProvider productDetailsProvider;

    @BeforeEach
    void setup() {
        cartStorage = new CartStorage();
        productDetailsProvider = new AlwaysMissingProductDetailsProvider();
        productDetails = new ArrayList<>();
    }

    @Test
    void itAllowsToAddProductToCart() {

        //Arrange
        Sales sales = thereIsSalesModule();
        String customer = thereIsCustomer("Dominik");
        String productId = thereIsProduct("puzzle", BigDecimal.valueOf(15));
        //Act
        sales.addToCart(customer, productId);

        //Assert
        assertCustomerCartContainsNProducts(customer, 1);
        //assertEquals(1, sales.itemsAmount(customer));
    }

    private void assertCustomerCartContainsNProducts(String customerId, int productsCount) {
        Cart customerCart = cartStorage.load(customerId).get();
        assert customerCart.itemsCount() == productsCount;
    }

    private String thereIsProduct(String name, BigDecimal price) {
        productDetails.add(
                new ProductDetails(UUID.randomUUID().toString(), name, price)
        );
        return UUID.randomUUID().toString();
    }

    private String thereIsCustomer(String id) {
        return id;
    }

    private Sales thereIsSalesModule() {
        return new Sales(cartStorage, productDetailsProvider);
    }
}
