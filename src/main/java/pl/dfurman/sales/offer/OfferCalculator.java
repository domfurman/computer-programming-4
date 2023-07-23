package pl.dfurman.sales.offer;

import pl.dfurman.sales.cart.Cart;
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

//    public Offer calculateFinalOffer(Cart customerCart, ProductDetailsProvider productDetailsProvider) {
//        if (!DiscountPolicy.discountCanBeApplied(calculateOffer(customerCart, productDetailsProvider))) {
//            return calculateOffer(customerCart, productDetailsProvider);
//        } else {
//
//        }
//    }

//    public Offer calculateOffer(Cart customerCart, ProductDetailsProvider productDetailsProvider) {
//        if (discountCanBeApplied(customerCart)) {
//            return offerWithDiscount(customerCart, productDetailsProvider);
//        } else {
//            return calculateBasicOffer(customerCart,productDetailsProvider);
//        }
//    }
//
//    public Offer offerWithDiscount(Cart customerCart, ProductDetailsProvider productDetailsProvider) {
//        String lowestPriceProductId = getCheapestProduct(customerCart, productDetailsProvider);
//        BigDecimal price = productDetailsProvider.load(lowestPriceProductId).get().getPrice();
//        Offer offer = calculateBasicOffer(customerCart, productDetailsProvider);
//        BigDecimal afterDiscount = offer.getTotal().subtract(price);
//        return Offer.offerSummary(afterDiscount, offer.getItemsCount());
//    }
//    public Offer calculateOffer(Cart customerCart, ProductDetailsProvider productDetailsProvider) {
//        if (customerCart.getProducts().size() % 5 != 0) {
//            BigDecimal totalCost = BigDecimal.ZERO;
//
//            for (String productId : customerCart.getProducts()) {
//                ProductDetails productDetails = productDetailsProvider.load(productId).get();
//                BigDecimal cost = productDetails.getPrice().multiply(customerCart.getProductQuantity(productId));
//                totalCost = totalCost.add(cost);
//            }
//            return Offer.offerSummary(totalCost, customerCart.itemsCount());
//        } else {
//            String lowestPriceProductId = getCheapestProduct(customerCart, productDetailsProvider);
//            BigDecimal discount = productDetailsProvider.load(lowestPriceProductId).get().getPrice();
//            BigDecimal totalCost = BigDecimal.ZERO;
//            for (String productId : customerCart.getProducts()) {
//                ProductDetails productDetails = productDetailsProvider.load(productId).get();
//                BigDecimal cost = productDetails.getPrice().multiply(customerCart.getProductQuantity(productId));
//                totalCost = totalCost.add(cost);
//            }
//            return Offer.offerSummary(totalCost.subtract(discount), customerCart.itemsCount());
//        }
//    }

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
