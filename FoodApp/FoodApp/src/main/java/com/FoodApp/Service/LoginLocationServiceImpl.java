package com.FoodApp.Service;

import com.FoodApp.Entity.LoginLocationEntity;
import com.FoodApp.Repository.LoginLocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginLocationServiceImpl implements LoginLocationService {

    private final LoginLocationRepository loginLocationRepository;

    public LoginLocationServiceImpl(LoginLocationRepository loginLocationRepository) {
        this.loginLocationRepository = loginLocationRepository;
    }

    @Override
    public LoginLocationEntity saveLoginLocation(LoginLocationEntity loginLocation) {
        return loginLocationRepository.save(loginLocation);
    }

    @Override
    public LoginLocationEntity getLocationByUserId(String userId) {
        return loginLocationRepository.findByUserId(userId);
    }
}