package com.Code81.books.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Integer id;
    private String title;
    private PublisherDto publisher;
    private String language;
    private Integer publicationYear;
    private String isbn;
    private String edition;
    private String summary;
    private String coverImageUrl;
    private Set<AuthorDto> authors;
    private Set<CategoryDto> categories;
}
