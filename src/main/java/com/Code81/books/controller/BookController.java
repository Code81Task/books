package com.Code81.books.controller;


import com.Code81.books.dto.BookDto;
import com.Code81.books.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public BookDto createBook( @RequestBody BookDto bookDto) {
        BookDto createdBook = bookService.createBook(bookDto);
        return  createdBook;
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Integer id) {
        BookDto book = bookService.getBookById(id);
        return book;
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        List<BookDto> books = bookService.getAllBooks();
        return books;
    }
    @GetMapping("/category/{categoryId}")
    public List<BookDto> getBooksByCategory(@PathVariable Long categoryId) {
        return bookService.getBooksByCategory(categoryId);
    }
    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable Integer id,
                                             @RequestBody BookDto bookDto) {
        BookDto updatedBook = bookService.updateBook(id, bookDto);
        return updatedBook;
    }


    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return "item deleted successfully";
    }
}