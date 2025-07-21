package com.muhamadridwan.bookservice.service.impl;

import com.muhamadridwan.bookservice.dto.UserResponse;
import com.muhamadridwan.bookservice.entity.User;
import com.muhamadridwan.bookservice.exception.ResourceNotFoundException;
import com.muhamadridwan.bookservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getById_shouldReturnUserResponse_whenUserExists() {
        User user = new User();
        user.setId(1L);
        user.setName("Ridwan");
        user.setEmail("ridwan@mail.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserResponse result = userService.getById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Ridwan", result.getName());
        assertEquals("ridwan@mail.com", result.getEmail());
    }

    @Test
    void getById_shouldThrowException_whenUserNotFound() {
        when(userRepository.findById(404L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            userService.getById(404L);
        });
    }
}