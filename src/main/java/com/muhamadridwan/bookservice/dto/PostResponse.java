package com.muhamadridwan.bookservice.dto;

import lombok.Data;

@Data
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private UserResponse user;
}
