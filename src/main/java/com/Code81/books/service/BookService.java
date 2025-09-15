package com.Code81.books.service;

import com.Code81.books.dto.AuthorDto;
import com.Code81.books.dto.BookDto;
import com.Code81.books.dto.CategoryDto;
import com.Code81.books.dto.PublisherDto;
import com.Code81.books.entity.Category;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto bookDto);
    BookDto getBookById(Integer id);
    List<BookDto> getAllBooks();
    BookDto updateBook(Integer id, BookDto bookDto);
    void deleteBook(Integer id);
    List<BookDto> getBooksByCategory(Long categoryId);

}
