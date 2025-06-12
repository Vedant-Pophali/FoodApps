package com.FoodApp.Repository;

import com.FoodApp.Entity.LoginLocationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginLocationRepository extends MongoRepository<LoginLocationEntity, String> {
    LoginLocationEntity findByUserId(String userId);
}