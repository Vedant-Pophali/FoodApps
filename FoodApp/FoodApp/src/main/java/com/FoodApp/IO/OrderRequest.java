package com.FoodApp.IO;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class OrderRequest {
    private List<OrderItem> orderedItem;
    private String userAddress;
    private double amount;
    private String email;
    private String phoneNumber;
    private String orderStatus;
}