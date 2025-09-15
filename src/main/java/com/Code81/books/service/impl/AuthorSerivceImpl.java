package com.Code81.books.service.impl;

import com.Code81.books.dto.AuthorDto;
import com.Code81.books.entity.Author;
import com.Code81.books.error.BookApiException;
import com.Code81.books.repo.AuthorRepo;
import com.Code81.books.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorSerivceImpl implements AuthorService {

    private final AuthorRepo authorRepository;



    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        if (authorDto.getName() == null || authorDto.getName().isBlank()) {
            throw new BookApiException("Author name cannot be empty");
        }

        Author author = new Author();
        author.setName(authorDto.getName());
        author.setBio(authorDto.getBio());

        Author saved = authorRepository.save(author);
        return new AuthorDto(saved.getId(), saved.getName(), saved.getBio());
    }

    @Override
    public List<AuthorDto> getAuthor() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            throw new BookApiException("No authors found in the system");
        }

        return authors.stream()
                .map(a -> new AuthorDto(a.getId(), a.getName(), a.getBio()))
                .collect(Collectors.toList());
    }
}
