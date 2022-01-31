package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Response {
    private Long sol;
    private String camera;
    private Picture picture;
    private User user;
}
