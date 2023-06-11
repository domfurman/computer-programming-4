package pl.dfurman.sales.offer;

import pl.dfurman.sales.cart.Cart;
import pl.dfurman.sales.cart.CartStorage;
import pl.dfurman.sales.product.ProductCatalogProductDetailsProvider;
import pl.dfurman.sales.product.ProductDetails;
import pl.dfurman.sales.product.ProductDetailsProvider;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class OfferCalculator {
    //Cart cart;
//    ProductDetailsProvider productDetailsProvider;
//
//    public OfferCalculator(ProductDetailsProvider productDetailsProvider) {
//        this.productDetailsProvider = productDetailsProvider;
//    }

    public Offer calculateOffer(Cart customerCart, ProductDetailsProvider productDetailsProvider) {
        BigDecimal totalCost = BigDecimal.valueOf(0);
        //BigDecimal amount = customerCart.getProducts().stream().map(product -> product.)
        //BigDecimal amount = BigDecimal.ZERO;
        //Cart customerCart = cartStorage.load(currentCustomer).get();
        int itemsAmount = customerCart.itemsCount();
        //int itemsAmount = 2;
        String cartProductId = customerCart.getProducts().get(0);
        ProductDetails productDetails = productDetailsProvider.load(cartProductId).get();
        totalCost.add(productDetails.getPrice());
        // part1 wyekstraktowac cene z produktu
        // part 2 przeiterowac przez koszyk, wyekstraktowac ceny i zsumowac do zmiennej total
        // part 3 zrobic kod bardziej czytelnym(?)
        return Offer.offerSummary(totalCost, itemsAmount);
    }
}
