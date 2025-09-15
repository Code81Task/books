package com.Code81.books.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BookDto {
    private Long id;
    private String title;
    private PublisherDto publisher;
    private String language;
    private Integer publicationYear;
    private String isbn;
    private String edition;
    private String summary;
    private String coverImageUrl;
    private boolean isDeleted;
    private Set<AuthorDto> authors;
    private Set<CategoryDto> categories;
}
