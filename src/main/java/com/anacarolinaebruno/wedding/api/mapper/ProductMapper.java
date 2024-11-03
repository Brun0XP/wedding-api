package com.anacarolinaebruno.wedding.api.mapper;

import com.anacarolinaebruno.wedding.api.dto.response.ProductResponseDTO;
import com.anacarolinaebruno.wedding.api.model.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponseDTO toResponse(Product product);

    default Page<ProductResponseDTO> toResponsePage(Page<Product> productsPage) {
        return productsPage.map(this::toResponse);
    }
}