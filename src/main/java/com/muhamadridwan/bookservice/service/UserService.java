package com.muhamadridwan.bookservice.service;

import com.muhamadridwan.bookservice.dto.UserResponse;

public interface UserService {
    UserResponse getById(Long id);
}
