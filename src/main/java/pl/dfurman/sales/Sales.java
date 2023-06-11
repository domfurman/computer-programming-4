package pl.dfurman.sales;

import pl.dfurman.payu.*;
import pl.dfurman.sales.cart.Cart;
import pl.dfurman.sales.cart.CartStorage;
import pl.dfurman.sales.offer.Offer;
import pl.dfurman.sales.offer.OfferCalculator;
import pl.dfurman.sales.product.NoSuchProductException;
import pl.dfurman.sales.product.ProductDetails;
import pl.dfurman.sales.product.ProductDetailsProvider;

import java.util.Optional;

public class Sales {
    private CartStorage cartStorage;
    private ProductDetailsProvider productDetailsProvider;
    private PayU payu;
    //private ProductDetails productDetails;
    private OfferCalculator offerCalculator;

    public Sales(CartStorage cartStorage, ProductDetailsProvider productDetailsProvider, PayU payu, OfferCalculator offerCalculator) {
        this.cartStorage = cartStorage;
        this.productDetailsProvider = productDetailsProvider;
        this.payu = payu;
        this.offerCalculator = offerCalculator;
    }

    public void addToCart(String customerId, String productId) {
        Cart cart = loadForCustomer(customerId)
                .orElse(Cart.empty());


        ProductDetails product = loadDetailsForProduct(productId)
                .orElseThrow(() -> new NoSuchProductException());   //NoSuchProductException was thrown
        cart.addProduct(product.getId());
        cartStorage.save(customerId, cart);
    }

    //NullPointerException was thrown here
    private Optional<ProductDetails> loadDetailsForProduct(String productId) {
        return productDetailsProvider.load(productId);

    }

    private Optional<Cart> loadForCustomer(String customerId) {
        return cartStorage.load(customerId);
    }

    public Offer getCurrentOffer(String currentCustomer) {
        Cart customerCart = loadForCustomer(currentCustomer)
                .orElse(Cart.empty());
        return offerCalculator.calculateOffer(customerCart, productDetailsProvider);
    }

    public int itemsAmount(String customerId) {
        Cart cart = loadForCustomer(customerId)
                .orElse(Cart.empty());
        return  cart.itemsCount();
    }

//    public ReservationData acceptOffer(String customerId, AcceptOfferRequest request) {
//        Offer offer = this.getCurrentOffer(customerId);
//
//        OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
//        orderCreateRequest.setBuyer(
//                new Buyer()
//                        .setFirstName("Dominik")
//                        .setLastName("F")
//                        .setEmail("dominik.doe@example.com")
//        );
//        orderCreateRequest.setProducts(Arrays.asList(
//                new Product()
//                    .setName("NAME")
//                    .setUnitPrice(21000)
//                    .setQuantity(2)
//        ));
//
//        OrderCreateResponse response = payu.handle(orderCreateRequest);
//
//        return new ReservationData(response.getRedirectUri());
//
//        //Reservation reservation = Reservation.from(offer);
//
//        //String paymentUrl = paymentGateway.register();
//
//        //reservation.registerPayment(paymentUrl);
//
//        //reservationStorage.save(reservation);
//
//        //return null;
//    }
}
