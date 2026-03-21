package com.FoodApp.Controller;
import com.FoodApp.IO.UserRequest;
import com.FoodApp.IO.UserResponse;
import com.FoodApp.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody UserRequest userRequest){
        return userService.registerUser(userRequest);
    }

}