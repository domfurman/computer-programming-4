package pl.dfurman.sales.offer;

import pl.dfurman.sales.cart.Cart;
import pl.dfurman.sales.product.ProductDetailsProvider;

import java.math.BigDecimal;

public class ValueDiscountPolicy {
    private final BigDecimal discountThreshold;
    private final BigDecimal discountValue;

    public ValueDiscountPolicy(BigDecimal discountThreshold, BigDecimal discountValue) {
        this.discountThreshold = discountThreshold;
        this.discountValue = discountValue;
    }

    public BigDecimal getDiscountPolicyThreshold() {
        return this.discountThreshold;
    }

    public boolean discountCanBeApplied(Cart customerCart, ProductDetailsProvider productDetailsProvider) {
        Offer offer = OfferCalculator.calculateOffer(customerCart, productDetailsProvider);
        return offer.getTotal().compareTo(getDiscountPolicyThreshold()) == 1;
    }

    public Offer applyDiscount(Cart customerCart, ProductDetailsProvider productDetailsProvider) {
        Offer offer = OfferCalculator.calculateOffer(customerCart, productDetailsProvider);
        if (!discountCanBeApplied(customerCart, productDetailsProvider)) {
            return offer;
        } else {
            return new Offer(offer.getTotal().subtract(discountValue), offer.getItemsCount());
        }
    }

}
