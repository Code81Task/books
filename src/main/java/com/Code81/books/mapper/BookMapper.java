package com.Code81.books.mapper;

import com.Code81.books.dto.BookDto;
import com.Code81.books.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {PublisherMapper.class, AuthorMapper.class, CategoryMapper.class}
)
public interface BookMapper {
    @Mapping(source = "publisher", target = "publisher")
    @Mapping(source = "authors", target = "authors")
    @Mapping(source = "categories", target = "categories")
    BookDto toDto(Book book);

    @Mapping(source = "publisher", target = "publisher")
    @Mapping(source = "authors", target = "authors")
    @Mapping(source = "categories", target = "categories")
    Book toEntity(BookDto bookDto);
}