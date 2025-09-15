package com.Code81.books.mapper;

import com.Code81.books.dto.BookDto;
import com.Code81.books.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        uses = {PublisherMapper.class, AuthorMapper.class, CategoryMapper.class}
)
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto toDto(Book book);

    Book toEntity(BookDto bookDto);
}
