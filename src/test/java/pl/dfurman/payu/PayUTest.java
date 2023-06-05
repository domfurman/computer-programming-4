package pl.dfurman.payu;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class PayUTest {

    @Test
    void creatingNewPaymentOrder() {
        OrderCreateRequest request = thereIsExampleOrderCreateRequest();
        PayU payu = thereIsPayU();
        OrderCreateResponse response = payu.handle(request);

        assertNotNull(response.getRedirectUri());
        assertNotNull(response.getOrderId());
    }

    private void assertNotNull(Object redirectUri) {

    }

    private PayU thereIsPayU() {
        return new PayU(new RestTemplate());
    }

    private OrderCreateRequest thereIsExampleOrderCreateRequest() {
        OrderCreateRequest request = new OrderCreateRequest();
        request.setNotifyUrl("https://your.eshop.com/notify")
                .setCustomerIp("127.0.0.1")
                .setMerchantPosId("300746")
                .setDescription("RTV market")
                .setCurrencyCode("PLN")
                .setTotalAmount(21000)
                .setBuyer(new Buyer()
                        .setEmail("dominik.doe@example.com")
                        .setFirstName("Dominik")
                        .setLastName("F")
                        .setPhone("654111654")
                        .setLanguage("pl")
                )
                .setProducts(Arrays.asList(
                        new Product()
                            .setName("Serwisik")
                            .setUnitPrice(21000)
                            .setQuantity(1)
                ));
        return  request;
    }
}
