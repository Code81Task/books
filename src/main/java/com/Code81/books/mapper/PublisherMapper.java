package com.Code81.books.mapper;

import com.Code81.books.dto.PublisherDto;
import com.Code81.books.entity.Publisher;
import org.mapstruct.Mapper;


@Mapper(
        componentModel = "spring")
public interface PublisherMapper {

    PublisherDto toDto(Publisher publisher);

    Publisher toEntity(PublisherDto publisherDto);
}
