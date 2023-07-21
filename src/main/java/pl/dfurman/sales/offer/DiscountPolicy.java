package pl.dfurman.sales.offer;

import pl.dfurman.sales.cart.Cart;
import pl.dfurman.sales.product.ProductDetails;
import pl.dfurman.sales.product.ProductDetailsProvider;

import java.math.BigDecimal;

public class DiscountPolicy {
    static int discountPolicyThreshold = 3;

    public static void setDiscountPolicyThreshold(int newDiscountPolicyThreshold) {
        discountPolicyThreshold = newDiscountPolicyThreshold;
    }

//    public DiscountPolicy(int discountPolicyThreshold) {
//        this.discountPolicyThreshold = discountPolicyThreshold;
//    }

    public static int getDiscountPolicyThreshold() {
        return discountPolicyThreshold;
    }



    public static boolean discountCanBeApplied(Cart customerCart) {
        return customerCart.getProducts().size() % getDiscountPolicyThreshold() == 0;
    }

    public String getCheapestProduct(Cart customerCart, ProductDetailsProvider productDetailsProvider) {
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
        return lowestPriceProductId;
    }
}