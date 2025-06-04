package com.FoodApp.Controller;

import com.FoodApp.IO.FoodRequest;
import com.FoodApp.IO.FoodResponse;
import com.FoodApp.Service.FoodService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.fasterxml.jackson.core.JsonProcessingException; // ✅ Fixed import

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@AllArgsConstructor
@CrossOrigin("*")
public class FoodController {
    private final FoodService foodService;
    private final ObjectMapper objectMapper;

    @PostMapping
    public FoodResponse addFood(@RequestPart("food") String foodString,
                                @RequestPart("file") MultipartFile file){
        FoodRequest request = null;
        try {
            request = objectMapper.readValue(foodString, FoodRequest.class);
        }
        catch (JsonProcessingException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JSON format");
        }
        return foodService.addFood(request, file);
    }

    @GetMapping
    public List<FoodResponse> readFoods(){
        return  foodService.readFoods();
    }

    @GetMapping("/{id}")
    public FoodResponse readFood(@PathVariable String id){
        return foodService.readFood(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFood(@PathVariable String id){
        foodService.deleteFood(id);
    }
}
