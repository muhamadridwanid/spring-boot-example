package com.muhamadridwan.bookservice.controller;

import com.muhamadridwan.bookservice.dto.PostResponse;
import com.muhamadridwan.bookservice.dto.UserResponse;
import com.muhamadridwan.bookservice.service.PostService;
import com.muhamadridwan.bookservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final PostService postService;
    private final UserService userService;

    @GetMapping("/users/{userId}")
    public UserResponse getUserById(@PathVariable("userId") Long userId) {
        return userService.getById(userId);
    }

    @GetMapping("/users/{userId}/posts")
    public Page<PostResponse> getUserPost(@PathVariable("userId") Long userId, Pageable pageable) {
        return postService.getPosts(userId, pageable);

    }

}
