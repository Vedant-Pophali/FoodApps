package com.FoodApp.Service;
import com.FoodApp.IO.FoodRequest;
import com.FoodApp.IO.FoodResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodService{
    String uploadFile(MultipartFile file);
    FoodResponse addFood(FoodRequest request, MultipartFile file);
    List<FoodResponse> readFoods();
    FoodResponse readFood(String id);
    boolean deleteFile(String fileName);
    void deleteFood(String id);
}