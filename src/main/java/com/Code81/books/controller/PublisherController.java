package com.Code81.books.controller;

import com.Code81.books.dto.PublisherDto;
import com.Code81.books.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publishers")
@AllArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;



    @PostMapping
    public PublisherDto createPublisher(@RequestBody PublisherDto publisherDto) {
        return publisherService.createPublisher(publisherDto);
    }

    @GetMapping
    public List<PublisherDto> getAllPublishers() {
        return publisherService.getPublishers();
    }
}
