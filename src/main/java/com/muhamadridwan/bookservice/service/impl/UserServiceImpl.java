package com.muhamadridwan.bookservice.service.impl;

import com.muhamadridwan.bookservice.dto.UserResponse;
import com.muhamadridwan.bookservice.entity.User;
import com.muhamadridwan.bookservice.exception.ResourceNotFoundException;
import com.muhamadridwan.bookservice.repository.UserRepository;
import com.muhamadridwan.bookservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponse getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User is not found!")
        );
        return mapResponse(user);
    }

    private UserResponse mapResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }
}
