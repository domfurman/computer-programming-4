package pl.dfurman.sales;

import org.springframework.web.bind.annotation.*;
import pl.dfurman.sales.offer.Offer;
import pl.dfurman.sales.reservation.AcceptOfferRequest;
import pl.dfurman.sales.reservation.ReservationData;

@RestController
public class SalesController {
    Sales sales;

    public SalesController(Sales sales) {
        this.sales = sales;
    }

    @GetMapping("/api/get-current-offer")
    public Offer getCurrentOffer() {
        return sales.getCurrentOffer(getCurrentCustomer());
    }

    @PostMapping("/api/add-to-cart/{productId}")
    public void addToCart(@PathVariable String productId) {
        sales.addToCart(getCurrentCustomer(), productId);
    }

    private String getCurrentCustomer() {
        return "Dominik";
    }

    @PostMapping("/api/accept-offer")
    public ReservationData acceptOffer(@RequestBody AcceptOfferRequest request) {
        return sales.acceptOffer(getCurrentCustomer(), request);
    }
}
