package com.anacarolinaebruno.wedding.api.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties("products")
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private String name;

    private String description;

    private Double price;

    private String image;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
