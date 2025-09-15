package com.Code81.books.service;

import com.Code81.books.dto.CategoryDto;
import com.Code81.books.dto.PublisherDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    List<CategoryDto> getCategory();
}
