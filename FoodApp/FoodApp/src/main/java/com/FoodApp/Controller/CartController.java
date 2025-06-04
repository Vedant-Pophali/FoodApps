package com.FoodApp.Controller;
import com.FoodApp.Service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<?> addToCart(@RequestBody Map<String, String> request) {
        String foodId = request.get("foodId");

        if (foodId == null || foodId.isEmpty()) {
            return ResponseEntity.badRequest().body("Food ID is required");
        }

        cartService.addToCart(foodId);
        return ResponseEntity.ok("Added to cart");
    }
}