package pl.dfurman.sales.offer;

import pl.dfurman.sales.cart.Cart;
import pl.dfurman.sales.product.ProductDetails;
import pl.dfurman.sales.product.ProductDetailsProvider;

import java.math.BigDecimal;

public class EveryNthItemDiscountPolicy {
    private final int quantityThreshold;

    public EveryNthItemDiscountPolicy(int quantityThreshold) {
        this.quantityThreshold = quantityThreshold;
    }

    public int getQuantityThreshold() {
        return quantityThreshold;
    }

    public boolean discountCanBeApplied(Cart customerCart, ProductDetailsProvider productDetailsProvider) {
        Offer offer = OfferCalculator.calculateOffer(customerCart, productDetailsProvider);
        return offer.getItemsCount() % getQuantityThreshold() == 0;
    }

    public Offer applyDiscount(Cart customerCart, ProductDetailsProvider productDetailsProvider) {
        Offer offer = OfferCalculator.calculateOffer(customerCart, productDetailsProvider);
        if (!discountCanBeApplied(customerCart, productDetailsProvider)) {
            return offer;
        } else {
            BigDecimal val = getCheapestProduct(customerCart, productDetailsProvider);
            return new Offer(offer.getTotal().subtract(val), offer.getItemsCount());
        }
    }

    public BigDecimal getCheapestProduct(Cart customerCart, ProductDetailsProvider productDetailsProvider) {
        String id = customerCart.getProducts().get(0);
        BigDecimal lowestPrice = productDetailsProvider.load(id).get().getPrice();
        String lowestPriceProductId = productDetailsProvider.load(id).get().getId();
        for (String productId : customerCart.getProducts()) {
            ProductDetails productDetails = productDetailsProvider.load(productId).get();
            BigDecimal price = productDetails.getPrice();
            if (lowestPrice.compareTo(price) == 1) {
                lowestPrice = price;
                lowestPriceProductId = productId;
            }
        }
        return lowestPrice;
    }
}
