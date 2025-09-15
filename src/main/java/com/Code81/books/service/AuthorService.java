package com.Code81.books.service;

import com.Code81.books.dto.AuthorDto;
import com.Code81.books.dto.CategoryDto;
import com.Code81.books.dto.PublisherDto;

import java.util.List;

public interface AuthorService {

    AuthorDto createAuthor(AuthorDto authorDto);
    List<AuthorDto> getAuthor();
}
