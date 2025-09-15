package com.Code81.books.repo;

import com.Code81.books.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepo extends JpaRepository<Publisher,Long> {
}
