package pl.dfurman.sales;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Reservation {

    @Id
    private String id;
    private BigDecimal total;
    private String paymentId;

    Reservation() {}

    public Reservation(String id, BigDecimal total, String paymentId) {
        this.id = id;
        this.total = total;
        this.paymentId = paymentId;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getPaymentId() {
        return paymentId;
    }
}
