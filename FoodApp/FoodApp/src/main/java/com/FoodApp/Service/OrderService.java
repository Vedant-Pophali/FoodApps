package com.FoodApp.Service;

import com.FoodApp.IO.OrderRequest;
import com.FoodApp.IO.OrderResponse;
import java.util.List;
import java.util.Map;

public interface OrderService {
    OrderResponse createOrderWithPayment(OrderRequest request);
    void verifyPayment(Map<String,String> paymentData,String status);
    List<OrderResponse> getUserOrders();
    void removeOrder(String orderId);
    List<OrderResponse> getOrdersOfAllUsers();
    void updateOrderStatus(String orderId,String status);
}