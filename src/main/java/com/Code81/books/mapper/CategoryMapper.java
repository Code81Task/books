package com.Code81.books.mapper;

import com.Code81.books.dto.CategoryDto;
import com.Code81.books.entity.Category;
import org.mapstruct.Mapper;


@Mapper(
        componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto categoryDto);
}
