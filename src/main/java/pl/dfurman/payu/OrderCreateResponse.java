package pl.dfurman.payu;

public class OrderCreateResponse {

    String redirectUri;
    String orderId;

    public OrderCreateResponse setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
        return this;
    }

    public OrderCreateResponse setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public Object getRedirectUri() {
        return null;
    }

    public Object getOrderId() {
        return null;
    }
}
