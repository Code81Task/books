package com.Code81.books.service;

import com.Code81.books.dto.PublisherDto;

import java.util.List;

public interface PublisherService {
    PublisherDto createPublisher(PublisherDto publisherDto);

    List<PublisherDto> getPublishers();
}
