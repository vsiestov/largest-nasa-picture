package com.example.demo.dto;

import lombok.Data;

@Data
public class Photo {
    private Long id;
    private String sol;
    private Camera camera;
    private String img_src;
    private Rover rover;
    private Long size;
}
