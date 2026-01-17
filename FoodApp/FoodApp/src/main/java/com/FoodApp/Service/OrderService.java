package com.FoodApp.Service;

import com.FoodApp.IO.OrderRequest;
import com.FoodApp.IO.OrderResponse;

public interface OrderService {
    OrderResponse createOrderWithPayment(OrderRequest request);
}