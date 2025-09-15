package com.Code81.books.controller;

import com.Code81.books.dto.AuthorDto;
import com.Code81.books.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/authors")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;



    @PostMapping()
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.createAuthor(authorDto);
    }

    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAuthor();
    }
}
