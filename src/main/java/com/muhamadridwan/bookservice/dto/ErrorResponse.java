package com.muhamadridwan.bookservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {
    private int status;
    private List<String> errors;
}
