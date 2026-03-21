package com.FoodApp.Controller;
import com.FoodApp.IO.CartRequest;
import com.FoodApp.IO.CartResponse;
import com.FoodApp.Service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;
@AllArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    @PostMapping
    public CartResponse addToCart(@Valid @RequestBody CartRequest request) {
        String foodId = request.getFoodId();
        if (foodId == null || foodId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "FoodID Not Found");
        }
        return cartService.addToCart(request);
    }
    @GetMapping
    public CartResponse getCart() {
        return cartService.getCart();
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  clearCart(@RequestBody CartRequest request) {
            cartService.clearCart();
    }
    @PostMapping("/remove")
    public CartResponse removeFromCart(@Valid @RequestBody CartRequest request) {
        String foodId = request.getFoodId();
        if(foodId == null || foodId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "FoodID Not Found");
        }
        return cartService.removeFromCart(request);
    }
    @DeleteMapping("/{foodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeItem(@PathVariable String foodId) {
        cartService.removeItem(foodId);
    }
}