package com.muhamadridwan.bookservice.service;

import com.muhamadridwan.bookservice.dto.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    Page<PostResponse> getPosts(Long userId, Pageable pageable);
}
