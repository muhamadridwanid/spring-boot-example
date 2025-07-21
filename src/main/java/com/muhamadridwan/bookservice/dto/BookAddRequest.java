package com.muhamadridwan.bookservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookAddRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String author;
}
