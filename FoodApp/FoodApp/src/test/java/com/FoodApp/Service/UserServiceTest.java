package com.FoodApp.Service;

import com.FoodApp.Entity.UserEntity;
import com.FoodApp.IO.UserRequest;
import com.FoodApp.IO.UserResponse;
import com.FoodApp.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationFacade authenticationFacade;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void registerUser_Success() {
        UserRequest request = new UserRequest("John Doe", "john@example.com", "password123");
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");

        UserEntity savedUser = new UserEntity();
        savedUser.setId("user-123");
        savedUser.setName(request.getName());
        savedUser.setEmail(request.getEmail());
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);

        UserResponse response = userService.registerUser(request);

        assertNotNull(response);
        assertEquals("user-123", response.getId());
        assertEquals("John Doe", response.getName());
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    public void registerUser_EmailAlreadyExists() {
        UserRequest request = new UserRequest("John Doe", "john@example.com", "password123");
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(new UserEntity()));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(request);
        });

        assertEquals("User not found: john@example.com", exception.getMessage());
        verify(userRepository, never()).save(any(UserEntity.class));
    }
}
