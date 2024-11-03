package com.anacarolinaebruno.wedding.api.dto.response;

import lombok.Data;

@Data
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;
    private CategoryResponseDTO category;

}
