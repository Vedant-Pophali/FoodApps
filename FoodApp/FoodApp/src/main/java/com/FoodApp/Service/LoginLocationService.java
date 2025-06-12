package com.FoodApp.Service;

import com.FoodApp.Entity.LoginLocationEntity;

public interface LoginLocationService {
    LoginLocationEntity saveLoginLocation(LoginLocationEntity loginLocation);
    LoginLocationEntity getLocationByUserId(String userId);
}