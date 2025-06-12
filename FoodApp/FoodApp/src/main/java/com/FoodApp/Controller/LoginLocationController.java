package com.FoodApp.Controller;

import com.FoodApp.Entity.LoginLocationEntity;
import com.FoodApp.Service.LoginLocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/location")
public class LoginLocationController {

    private final LoginLocationService loginLocationService;

    public LoginLocationController(LoginLocationService loginLocationService) {
        this.loginLocationService = loginLocationService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveLoginLocation(@RequestBody LoginLocationEntity location, Authentication authentication) {
        try {
            String userId = authentication.getName(); // Get user ID from token
            location.setUserId(userId); // Inject userId into the entity
            LoginLocationEntity savedLocation = loginLocationService.saveLoginLocation(location);
            return ResponseEntity.ok(savedLocation);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving login location: " + e.getMessage());
        }
    }

    @GetMapping("/user")
    public ResponseEntity<LoginLocationEntity> getUserLocation(Authentication authentication) {
        String userId = authentication.getName();
        LoginLocationEntity location = loginLocationService.getLocationByUserId(userId);
        if (location != null) {
            return ResponseEntity.ok(location);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}