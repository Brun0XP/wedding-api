package com.anacarolinaebruno.wedding.api.repository;

import com.anacarolinaebruno.wedding.api.model.entity.Category;
import com.anacarolinaebruno.wedding.api.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByCategory(Category category, Pageable pageable);

}
