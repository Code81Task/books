package com.Code81.books.repo;

import com.Code81.books.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CategoryRepo extends JpaRepository<Category,Long> {
    Set<Category> findByIdIn(Set<Long> ids);

}
