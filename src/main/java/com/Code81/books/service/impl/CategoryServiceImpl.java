package com.Code81.books.service.impl;

import com.Code81.books.dto.CategoryDto;
import com.Code81.books.entity.Category;
import com.Code81.books.error.BookApiException;
import com.Code81.books.repo.CategoryRepo;
import com.Code81.books.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepository;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        if (categoryDto.getName() == null || categoryDto.getName().isBlank()) {
            throw new BookApiException("Category name cannot be empty");
        }

        Category category = new Category();
        category.setName(categoryDto.getName());

        Category saved = categoryRepository.save(category);
        return new CategoryDto(saved.getId(), saved.getName());
    }

    @Override
    public List<CategoryDto> getCategory() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new BookApiException("No categories found in the system");
        }

        return categories.stream()
                .map(c -> new CategoryDto(c.getId(), c.getName()))
                .collect(Collectors.toList());
    }
}
