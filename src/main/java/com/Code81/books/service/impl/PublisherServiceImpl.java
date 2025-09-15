package com.Code81.books.service.impl;

import com.Code81.books.dto.PublisherDto;
import com.Code81.books.entity.Publisher;
import com.Code81.books.error.BookApiException;
import com.Code81.books.repo.PublisherRepo;
import com.Code81.books.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepo publisherRepository;


    @Override
    public PublisherDto createPublisher(PublisherDto publisherDto) {
        if (publisherDto.getName() == null || publisherDto.getName().isBlank()) {
            throw new BookApiException("Publisher name cannot be empty");
        }

        Publisher publisher = new Publisher();
        publisher.setName(publisherDto.getName());
        publisher.setAddress(publisherDto.getAddress());

        Publisher saved = publisherRepository.save(publisher);
        return new PublisherDto(saved.getId(), saved.getName(), saved.getAddress());
    }

    @Override
    public List<PublisherDto> getPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        if (publishers.isEmpty()) {
            throw new BookApiException("No publishers found in the system");
        }

        return publishers.stream()
                .map(p -> new PublisherDto(p.getId(), p.getName(), p.getAddress()))
                .collect(Collectors.toList());
    }
}
