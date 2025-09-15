package com.Code81.books.repo;

import com.Code81.books.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends JpaRepository<Book,Long> {

    @Query("SELECT b FROM Book b WHERE b.isDeleted = false")
    List<Book> findAllActiveBooks();

    @Query("SELECT b FROM Book b WHERE b.id = :id AND b.isDeleted = false")
    Optional<Book> findActiveBookById(@Param("id") Long id);

    Optional<Book> findByIsbnAndIsDeletedFalse(String isbn);

    boolean existsByIsbnAndIsDeletedFalse(String isbn);
    @Query("SELECT b FROM Book b JOIN b.categories c WHERE c.id = :categoryId")
    List<Book> findByCategoryId(Long categoryId);
}
