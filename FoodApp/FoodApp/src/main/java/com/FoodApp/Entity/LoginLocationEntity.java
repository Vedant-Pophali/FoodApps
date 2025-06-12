package com.FoodApp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "loginLocations")
public class LoginLocationEntity {
    @Id
    private String id;
    private String userId;
    private double latitude;
    private double longitude;
    private String city;
}