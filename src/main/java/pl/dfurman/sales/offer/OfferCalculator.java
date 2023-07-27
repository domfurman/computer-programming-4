package pl.dfurman.sales.offer;

import pl.dfurman.sales.cart.Cart;
import pl.dfurman.sales.product.ProductDetails;
import pl.dfurman.sales.product.ProductDetailsProvider;

import java.math.BigDecimal;



public class OfferCalculator {

    public static Offer calculateOffer(Cart customerCart, ProductDetailsProvider productDetailsProvider) {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (String productId : customerCart.getProducts()) {
            ProductDetails productDetails = productDetailsProvider.load(productId).get();
            BigDecimal cost = productDetails.getPrice().multiply(customerCart.getProductQuantity(productId));
            totalCost = totalCost.add(cost);
        }
        return Offer.offerSummary(totalCost, customerCart.itemsCount());
    }

    public Offer calculateDiscountOffer(Cart customerCart, ProductDetailsProvider productDetailsProvider, ValueDiscountPolicy valueDiscountPolicy) {
        Offer offer = calculateOffer(customerCart, productDetailsProvider);
        offer = valueDiscountPolicy.applyDiscount(customerCart, productDetailsProvider);
        return offer;
    }

/*    public Offer calculateFinalOffer(Cart customerCart, ProductDetailsProvider productDetailsProvider) {
        if (!ValueDiscountPolicy.discountCanBeApplied(calculateOffer(customerCart, productDetailsProvider))) {
            return calculateOffer(customerCart, productDetailsProvider);
        } else {
            Offer basicOffer = calculateOffer(customerCart, productDetailsProvider);
            String cheapestProductId = getCheapestProduct(customerCart, productDetailsProvider);
            BigDecimal price = productDetailsProvider.load(cheapestProductId).get().getPrice();
            BigDecimal totalWithDiscount = basicOffer.getTotal().subtract(price);
            return Offer.offerSummary(totalWithDiscount, customerCart.itemsCount());
        }
    }*/

//
//    public Offer offerWithDiscount(Cart customerCart, ProductDetailsProvider productDetailsProvider) {
//        String lowestPriceProductId = getCheapestProduct(customerCart, productDetailsProvider);
//        BigDecimal price = productDetailsProvider.load(lowestPriceProductId).get().getPrice();
//        Offer offer = calculateBasicOffer(customerCart, productDetailsProvider);
//        BigDecimal afterDiscount = offer.getTotal().subtract(price);
//        return Offer.offerSummary(afterDiscount, offer.getItemsCount());
//    }







}
