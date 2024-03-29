package pl.dfurman.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import pl.dfurman.payu.PayU;
import pl.dfurman.sales.cart.Cart;
import pl.dfurman.sales.cart.CartStorage;
import pl.dfurman.sales.offer.ValueDiscountPolicy;
import pl.dfurman.sales.offer.Offer;
import pl.dfurman.sales.offer.OfferCalculator;
import pl.dfurman.sales.product.AvailableProductsList;
import pl.dfurman.sales.product.ProductDetails;
import pl.dfurman.sales.product.ProductDetailsProvider;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public class OfferCalculationTest {
    CartStorage cartStorage;
    AvailableProductsList availableProducts;
    ProductDetailsProvider productDetailsProvider;

    @BeforeEach
    void setup() {
        cartStorage = new CartStorage();
        availableProducts = new AvailableProductsList();
    }

    @Test
    void itCalculatesOffer() {
        Sales sales = thereIsSalesModule();
        String customer = thereIsCustomer("TypicalUser");
        String product1 = thereIsProduct("Prod1", BigDecimal.valueOf(10));
        String product2 = thereIsProduct("Prod2", BigDecimal.valueOf(11));


        sales.addToCart(customer, product1);
        sales.addToCart(customer, product2);

        assertEquals(BigDecimal.valueOf(21), sales.getCurrentOffer(customer).getTotal());
    }

/*    @Test
    void itExtractsCheapestProduct() {
        Sales sales = thereIsSalesModule();
        //sales.get();
        String customer = thereIsCustomer("TypicalUser");
        String product1 = thereIsProduct("Prod1", BigDecimal.valueOf(100));
        String product2 = thereIsProduct("Prod2", BigDecimal.valueOf(10));
        String product3 = thereIsProduct("Prod3", BigDecimal.valueOf(1));

        sales.addToCart(customer, product1);
        sales.addToCart(customer, product2);
        sales.addToCart(customer, product3);

        Offer offer = sales.getCurrentOffer(customer);
        Cart customerCart = sales.loadForCustomer(customer)
                .orElse(Cart.empty());

        String chid = OfferCalculator.getCheapestProduct(customerCart, sales.getProductDetailsProvider());

        assert chid.equals(product3);
    }*/

    @Test
    void itAppliesDiscount() {
        Sales sales = thereIsSalesModule();
        String customer = thereIsCustomer("TypicalUser");
        String product1 = thereIsProduct("Prod1", BigDecimal.valueOf(100));
        String product2 = thereIsProduct("Prod2", BigDecimal.valueOf(50));
        String product3 = thereIsProduct("Prod3", BigDecimal.valueOf(30)); //180

        sales.addToCart(customer, product1);
        sales.addToCart(customer, product2);
        sales.addToCart(customer, product3);

        if (sales.getDiscountType() == "value") {
            assertEquals(BigDecimal.valueOf(170), sales.getCurrentOffer(customer).getTotal());
        } else {
            assertEquals(BigDecimal.valueOf(150), sales.getCurrentOffer(customer).getTotal());
        }

    }


    private String thereIsProduct(String name, BigDecimal price) {
        String id = UUID.randomUUID().toString();
        ProductDetails productDetails = new ProductDetails(id, name, price);
        availableProducts.add(productDetails);
        return id;
    }

    private String thereIsCustomer(String id) {
        return id;
    }

    private Sales thereIsSalesModule() {
        return new Sales(cartStorage, availableProducts, new PayU(new RestTemplate()), new OfferCalculator());
    }
}
