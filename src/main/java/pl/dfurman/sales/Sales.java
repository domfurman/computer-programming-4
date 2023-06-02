package pl.dfurman.sales;

import pl.dfurman.sales.cart.Cart;
import pl.dfurman.sales.cart.CartStorage;
import pl.dfurman.sales.offer.Offer;
import pl.dfurman.sales.product.NoSuchProductException;
import pl.dfurman.sales.product.ProductCatalogProductDetailsProvider;
import pl.dfurman.sales.product.ProductDetails;
import pl.dfurman.sales.product.ProductDetailsProvider;
import pl.dfurman.sales.reservation.Reservation;

import java.util.Optional;

public class Sales {
    private CartStorage cartStorage;
    private ProductDetailsProvider productDetailsProvider;

    public Sales(CartStorage cartStorage, ProductDetailsProvider productDetailsProvider) {
        this.cartStorage = cartStorage;
        this.productDetailsProvider = productDetailsProvider;
    }

    public void addToCart(String customerId, String productId) {
        Cart cart = loadForCustomer(customerId)
                .orElse(Cart.empty());

        ProductDetails product = loadDetailsForProduct(productId);
        //        .orElseThrow(() -> new NoSuchProductException());
        cart.add(product);
        cartStorage.save(customerId, cart);
    }

    private ProductDetails loadDetailsForProduct(String productId) {
        return productDetailsProvider.load(productId)
                .orElseThrow(() -> new NoSuchProductException());
    }

    private Optional<Cart> loadForCustomer(String customerId) {
        return cartStorage.load(customerId);
    }

    public Offer getCurrentOffer(String currentCustomer) {
        return new Offer();
    }

    public int itemsAmount(String customerId) {
        Cart cart = loadForCustomer(customerId)
                .orElse(Cart.empty());
        return  cart.itemsCount();
    }

    public PaymentData acceptOffer(String customerId, AcceptOffer request) {
        Offer offer = this.getCurrentOffer(customerId);

        Reservation reservation = Reservation.from(offer);

        //String paymentUrl = paymentGateway.register();

        //reservation.registerPayment(paymentUrl);

        reservationStorage.save(reservation);

        return null;
    }
}
