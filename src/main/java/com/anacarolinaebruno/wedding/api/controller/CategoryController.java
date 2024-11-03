package com.anacarolinaebruno.wedding.api.controller;

import com.anacarolinaebruno.wedding.api.dto.response.CategoryResponseDTO;
import com.anacarolinaebruno.wedding.api.mapper.CategoryMapper;
import com.anacarolinaebruno.wedding.api.model.entity.Category;
import com.anacarolinaebruno.wedding.api.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("categories")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getCategories() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categoryMapper.toResponseList(categories));
    }
}
