package com.Code81.books.mapper;

import com.Code81.books.dto.PublisherDto;
import com.Code81.books.entity.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    PublisherDto toDto(Publisher publisher);

    Publisher toEntity(PublisherDto publisherDto);
}
