package com.Code81.books.repo;

import com.Code81.books.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AuthorRepo extends JpaRepository<Author,Long> {
    Set<Author> findByIdIn(Set<Long> ids);

}
