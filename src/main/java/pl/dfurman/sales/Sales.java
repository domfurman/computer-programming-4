package pl.dfurman.sales;

import java.util.Optional;

public class Sales {
    private CartStorage cartStorage;
    private ProductCatalogProductDetailsProvider productCatalogProductDetailsProvider;

    public Sales(CartStorage cartStorage, ProductCatalogProductDetailsProvider productCatalogProductDetailsProvider) {
        this.cartStorage = cartStorage;
        this.productCatalogProductDetailsProvider = productCatalogProductDetailsProvider;
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
        return productCatalogProductDetailsProvider.load(productId)
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

    public ReservationData acceptOffer(String customerId, AcceptOfferRequest request) {
        Offer offer = this.getCurrentOffer(customerId);

        Reservation reservation = Reservation.of(offer);

        String paymentUrl = paymentGateway.register();

        reservation.registerPayment(paymentUrl);

        reservationStorage.save(reservation);

        return new
    }
}
