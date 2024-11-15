package com.anacarolinaebruno.wedding.api.service;

import com.anacarolinaebruno.wedding.api.model.entity.Category;
import com.anacarolinaebruno.wedding.api.model.entity.Product;
import com.anacarolinaebruno.wedding.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    public Page<Product> getAllProductsPageable(Long categoryId, Pageable pageable) {
        if (categoryId != null) {
            Category category = categoryService.getCategory(categoryId);
            return productRepository.findByCategoryAndActiveIsTrue(category, pageable);
        }

        return productRepository.findByActiveIsTrue(pageable);
    }

    public List<Product> getProducts(List<Long> ids) {
        return productRepository.findAllById(ids);
    }
}
