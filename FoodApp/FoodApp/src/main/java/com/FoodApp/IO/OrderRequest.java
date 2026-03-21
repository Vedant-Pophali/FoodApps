package com.FoodApp.IO;

import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.Valid;

import java.util.List;
@Data
@Builder
public class OrderRequest {
    
    @NotBlank(message = "Address is required")
    private String userAddress;
    
    @Min(value = 1, message = "Amount must be greater than 0")
    private double amount;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    
    private String orderStatus;
    
    @NotEmpty(message = "Order items cannot be empty")
    @Valid
    private List<OrderItem> orderedItems;  // <--- This must exist and be populated!
}