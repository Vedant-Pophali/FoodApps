package com.FoodApp.Controller;

import com.FoodApp.IO.OrderRequest;
import com.FoodApp.IO.OrderResponse;
import com.FoodApp.Service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/create")
    public OrderResponse createOrderWithPayment(@RequestBody OrderRequest orderRequest) {
       OrderResponse orderResponse = orderService.createOrderWithPayment(orderRequest);
       return orderResponse;
    }
}