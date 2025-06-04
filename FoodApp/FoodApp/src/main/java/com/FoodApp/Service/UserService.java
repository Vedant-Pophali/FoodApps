package com.FoodApp.Service;


import com.FoodApp.IO.UserRequest;
import com.FoodApp.IO.UserResponse;

public interface UserService {
    UserResponse registerUser(UserRequest request);
    String findByUserId();
}