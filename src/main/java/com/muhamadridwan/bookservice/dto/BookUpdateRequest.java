package com.muhamadridwan.bookservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookUpdateRequest {
    @NotNull
    private Long id;
    private String title;
    private String author;
}
