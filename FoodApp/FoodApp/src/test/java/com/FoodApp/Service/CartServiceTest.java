package com.FoodApp.Service;

import com.FoodApp.Entity.CartEntity;
import com.FoodApp.IO.CartRequest;
import com.FoodApp.IO.CartResponse;
import com.FoodApp.Repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private CartServiceImpl cartService;

    @Test
    public void addToCart_NewItem() {
        String userId = "user-123";
        CartRequest request = new CartRequest("food-1");

        when(userService.findByUserId()).thenReturn(userId);
        
        CartEntity cart = new CartEntity();
        cart.setItems(new HashMap<>());
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
        
        when(cartRepository.save(any(CartEntity.class))).thenReturn(cart);

        CartResponse response = cartService.addToCart(request);

        assertNotNull(response);
        assertTrue(response.getItems().containsKey("food-1"));
        assertEquals(1, response.getItems().get("food-1"));
    }

    @Test
    public void removeFromCart_DecrementQuantity() {
        String userId = "user-123";
        CartRequest request = new CartRequest("food-1");

        when(userService.findByUserId()).thenReturn(userId);

        CartEntity cart = new CartEntity();
        Map<String, Integer> items = new HashMap<>();
        items.put("food-1", 2);
        cart.setItems(items);

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
        when(cartRepository.save(any(CartEntity.class))).thenReturn(cart);

        CartResponse response = cartService.removeFromCart(request);

        assertEquals(1, response.getItems().get("food-1"));
    }

    @Test
    public void removeFromCart_RemoveItemWhenZero() {
        String userId = "user-123";
        CartRequest request = new CartRequest("food-1");

        when(userService.findByUserId()).thenReturn(userId);

        CartEntity cart = new CartEntity();
        Map<String, Integer> items = new HashMap<>();
        items.put("food-1", 1);
        cart.setItems(items);

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
        when(cartRepository.save(any(CartEntity.class))).thenReturn(cart);

        CartResponse response = cartService.removeFromCart(request);

        assertFalse(response.getItems().containsKey("food-1"));
    }
}
