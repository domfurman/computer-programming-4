package pl.dfurman.sales.offer;

import pl.dfurman.sales.cart.Cart;
import pl.dfurman.sales.cart.CartStorage;
import pl.dfurman.sales.product.ProductCatalogProductDetailsProvider;
import pl.dfurman.sales.product.ProductDetails;
import pl.dfurman.sales.product.ProductDetailsProvider;

import java.math.BigDecimal;

public class OfferCalculator {

    public Offer calculateOffer(Cart customerCart, ProductDetailsProvider productDetailsProvider) {
        BigDecimal totalCost = BigDecimal.ZERO;

        for (String productId : customerCart.getProducts()) {
            ProductDetails productDetails = productDetailsProvider.load(productId).get();
            BigDecimal cost = productDetails.getPrice().multiply(customerCart.getProductQuantity(productId));
            totalCost = totalCost.add(cost);
        }
        return Offer.offerSummary(totalCost, customerCart.itemsCount());
    }
}
