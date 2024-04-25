package com.k35dl.g6.utils;

import lombok.Data;

@Data
public class ReviewProductDTO {
    private Long id;
    private int rating;
    private String comment;
    private String username; 
}
