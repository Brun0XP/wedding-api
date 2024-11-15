package com.anacarolinaebruno.wedding.api.controller;

import com.anacarolinaebruno.wedding.api.dto.response.ProductResponseDTO;
import com.anacarolinaebruno.wedding.api.mapper.ProductMapper;
import com.anacarolinaebruno.wedding.api.model.entity.Product;
import com.anacarolinaebruno.wedding.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("product")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(@RequestParam(required = false) Long categoryId, @PageableDefault(size = 200) Pageable pageable) {
        Page<Product> productsPage = productService.getAllProductsPageable(categoryId, pageable);
        return ResponseEntity.ok(productMapper.toResponsePage(productsPage));
    }
}
