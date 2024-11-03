package com.anacarolinaebruno.wedding.api.mapper;

import com.anacarolinaebruno.wedding.api.dto.response.CategoryResponseDTO;
import com.anacarolinaebruno.wedding.api.model.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    List<CategoryResponseDTO> toResponseList(List<Category> rsvp);

}
