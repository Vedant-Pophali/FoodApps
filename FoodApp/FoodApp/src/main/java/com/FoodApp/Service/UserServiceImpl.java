package com.FoodApp.Service;

import com.FoodApp.Entity.UserEntity;
import com.FoodApp.IO.UserRequest;
import com.FoodApp.IO.UserResponse;
import com.FoodApp.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Builder
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public UserResponse registerUser(UserRequest request) {
        UserEntity newUser = convertToEntity(request);
        newUser = userRepository.save(newUser);
        return convertToResponse(newUser);
    }

    @Override
    public String findByUserId(){
        String loggedInUserEmail = authenticationFacade.getAuthentication().getName();
        UserEntity loggedInUser = userRepository.findByEmail(loggedInUserEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + loggedInUserEmail));
        return loggedInUser.getId();
    }


    private UserEntity convertToEntity(UserRequest userRequest) {
        return UserEntity.builder()
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()) )
                .name(userRequest.getName())
                .build();
    }

    private UserResponse convertToResponse(UserEntity registeredUser){
            return UserResponse.builder()
                    .id(registeredUser.getId())
                    .name(registeredUser.getName())
                    .email(registeredUser.getEmail())
                    .build();
    }
}