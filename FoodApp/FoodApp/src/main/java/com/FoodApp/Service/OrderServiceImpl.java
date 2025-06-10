package com.FoodApp.Service;

import com.FoodApp.Entity.OrderEntity;
import com.FoodApp.IO.OrderRequest;
import com.FoodApp.IO.OrderResponse;
import com.FoodApp.Repository.OrderRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class OrderServiceImpl implements OrderService {

    @Value("${razorpay_key}")
    private String RAZORPAY_KEY;

    @Value("${razorpay_secret}")
    private String RAZORPAY_SECRET;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;
    @Override
    public OrderResponse createOrderWithPayment(OrderRequest request) {
        try {
            OrderEntity newOrder = convertToEntity(request);
            newOrder = orderRepository.save(newOrder);

            RazorpayClient razorpayClient = new RazorpayClient(RAZORPAY_KEY, RAZORPAY_SECRET);

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", newOrder.getAmount() * 100); // Amount in paise
            orderRequest.put("currency", "INR");
            orderRequest.put("payment_capture", 1);

            Order razorpayOrder = razorpayClient.orders.create(orderRequest);
            newOrder.setRazorpayOrderId(razorpayOrder.get("id"));

            String loggedInUserId = userService.findByUserId(); // Ensure this returns the current user
            newOrder.setUserId(loggedInUserId);

            newOrder = orderRepository.save(newOrder);

            return convertToResponse(newOrder);

        } catch (RazorpayException e) {
            throw new RuntimeException("Failed to create Razorpay order", e);
        }
    }

    private OrderResponse convertToResponse(OrderEntity newOrder) {
        return OrderResponse.builder()
                .id(newOrder.getId())
                .amount(newOrder.getAmount())
                .userAddress(newOrder.getUserAddress())
                .userId(newOrder.getUserId())
                .razorpayOrderId(newOrder.getRazorpayOrderId())
                .paymentStatus(newOrder.getPaymentStatus())
                .orderStatus(newOrder.getOrderStatus())
                .email(newOrder.getEmail())
                .phoneNumber(newOrder.getPhoneNumber())
                .build();
    }

    private OrderEntity convertToEntity(OrderRequest request) {
        return OrderEntity.builder()
                .userAddress(request.getUserAddress())
                .amount(request.getAmount())
                .orderedItems(request.getOrderedItem())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .orderStatus(request.getOrderStatus())
                .build();
    }
}
