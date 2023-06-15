package pl.dfurman.sales.payment;

public class PaymentData {
    String paymentUrl;
    String paymentId;

    public PaymentData(String paymentUrl, String paymentId) {
        this.paymentUrl = paymentUrl;
        this.paymentId = paymentId;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public String getPaymentId() {
        return paymentId;
    }
}
