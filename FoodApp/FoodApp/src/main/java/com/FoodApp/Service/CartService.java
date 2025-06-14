package com.FoodApp.Service;

import com.FoodApp.IO.CartRequest;
import com.FoodApp.IO.CartResponse;

public interface CartService  {
    CartResponse addToCart(CartRequest request);
    CartResponse getCart();
    void clearCart();
    CartResponse removeFromCart(CartRequest request);
}