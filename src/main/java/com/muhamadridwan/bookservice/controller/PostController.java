package com.muhamadridwan.bookservice.controller;

import com.muhamadridwan.bookservice.dto.PostResponse;
import com.muhamadridwan.bookservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public Page<PostResponse> getPostByUserId(
            @RequestParam(value = "userId", required = false) Long userId,
            Pageable pageable) {
        return postService.getPosts(userId, pageable);
    }
}
