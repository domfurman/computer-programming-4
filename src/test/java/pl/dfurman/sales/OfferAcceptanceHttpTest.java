package pl.dfurman.sales;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import pl.dfurman.productcatalog.Product;
import pl.dfurman.productcatalog.ProductCatalog;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OfferAcceptanceHttpTest {

    @Autowired
    ProductCatalog catalog;

    @Autowired
    TestRestTemplate http;

    @Test
    void itAllowsToAcceptTheOffer() {
        //Arrange
        String productId = thereIsExampleProduct();
        //addToCart
        http.postForEntity(String.format("/api/add-to-cart/%s",productId), null, Object.class);
        http.postForEntity(String.format("/api/add-to-cart/%s",productId), null, Object.class);

        //Act
        AcceptOfferRequest request = new AcceptOfferRequest("Dominik", "dominik@example.com");
        http.postForEntity(String.format("/api/accept-offer",productId), request, ReservationData.class);

        //Assert
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody().getPaymentId());
        assertNotNull(response.getBody().getPaymentUrl());
    }

    private String thereIsExampleProduct() {
        return catalog.allPublishedProducts()
                .stream()
                .findFirst()
                .map(Product::getId)
                .get();
    }
}
