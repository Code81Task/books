package com.Code81.books.service.impl;

import com.Code81.books.dto.AuthorDto;
import com.Code81.books.dto.BookDto;
import com.Code81.books.dto.CategoryDto;
import com.Code81.books.dto.PublisherDto;
import com.Code81.books.entity.Author;
import com.Code81.books.entity.Book;
import com.Code81.books.entity.Category;
import com.Code81.books.entity.Publisher;
import com.Code81.books.error.BookApiException;
import com.Code81.books.mapper.BookMapper;
import com.Code81.books.repo.AuthorRepo;
import com.Code81.books.repo.BookRepo;
import com.Code81.books.repo.CategoryRepo;
import com.Code81.books.repo.PublisherRepo;
import com.Code81.books.service.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepository;
    private final PublisherRepo publisherRepository;
    private final AuthorRepo authorRepository;
    private final CategoryRepo categoryRepository;
    @Autowired
    private BookMapper bookMapper;

    @Override
    public BookDto createBook(BookDto bookDto) {
        log.info("Creating new book with title: {}", bookDto.getTitle());

        validateBookDto(bookDto);

        if (bookDto.getIsbn() != null && bookRepository.existsByIsbnAndIsDeletedFalse(bookDto.getIsbn())) {
            throw new BookApiException("Book with ISBN " + bookDto.getIsbn() + " already exists");
        }

        Book book = bookMapper.toEntity(bookDto);

        if (bookDto.getPublisher() != null && bookDto.getPublisher().getId() != null) {
            Publisher publisher = publisherRepository.findById(bookDto.getPublisher().getId().longValue())
                    .orElseThrow(() -> new BookApiException("Publisher not found with id: " + bookDto.getPublisher().getId()));
            book.setPublisher(publisher);
        }

        if (bookDto.getAuthors() != null && !bookDto.getAuthors().isEmpty()) {
            Set<Long> authorIds = bookDto.getAuthors().stream()
                    .map(authorDto -> authorDto.getId().longValue())
                    .collect(Collectors.toSet());
            Set<Author> authors = authorRepository.findByIdIn(authorIds);

            if (authors.size() != authorIds.size()) {
                throw new BookApiException("One or more authors not found");
            }
            book.setAuthors(authors);
        }

        // Set categories if provided
        if (bookDto.getCategories() != null && !bookDto.getCategories().isEmpty()) {
            Set<Long> categoryIds = bookDto.getCategories().stream()
                    .map(categoryDto -> categoryDto.getId().longValue())
                    .collect(Collectors.toSet());
            Set<Category> categories = categoryRepository.findByIdIn(categoryIds);

            if (categories.size() != categoryIds.size()) {
                throw new BookApiException("One or more categories not found");
            }
            book.setCategories(categories);
        }

        book.setDeleted(false);
        Book savedBook = bookRepository.save(book);

        log.info("Book created successfully with id: {}", savedBook.getId());
        return bookMapper.toDto(savedBook);
    }

    @Override
    public BookDto getBookById(Integer id) {
        log.info("Fetching book with id: {}", id);

        if (id == null) {
            throw new BookApiException("Book ID cannot be null");
        }

        Book book = bookRepository.findActiveBookById(id.longValue())
                .orElseThrow(() -> new BookApiException("Book not found with id: " + id));

        return bookMapper.toDto(book);
    }

    @Override
    public List<BookDto> getAllBooks() {
        log.info("Fetching all active books");

        List<Book> books = bookRepository.findAllActiveBooks();
        return books.stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto updateBook(Integer id, BookDto bookDto) {
        log.info("Updating book with id: {}", id);

        if (id == null) {
            throw new BookApiException("Book ID cannot be null");
        }

        Book existingBook = bookRepository.findActiveBookById(id.longValue())
                .orElseThrow(() -> new BookApiException("Book not found with id: " + id));

        // Validate the updated book data
        validateBookDto(bookDto);

        // Check if ISBN is being changed and if new ISBN already exists
        if (bookDto.getIsbn() != null &&
                !bookDto.getIsbn().equals(existingBook.getIsbn()) &&
                bookRepository.existsByIsbnAndIsDeletedFalse(bookDto.getIsbn())) {
            throw new BookApiException("Book with ISBN " + bookDto.getIsbn() + " already exists");
        }

        // Update basic fields
        existingBook.setTitle(bookDto.getTitle());
        existingBook.setLanguage(bookDto.getLanguage());
        existingBook.setPublicationYear(bookDto.getPublicationYear());
        existingBook.setIsbn(bookDto.getIsbn());
        existingBook.setEdition(bookDto.getEdition());
        existingBook.setSummary(bookDto.getSummary());
        existingBook.setCoverImageUrl(bookDto.getCoverImageUrl());

        // Update publisher if provided
        if (bookDto.getPublisher() != null && bookDto.getPublisher().getId() != null) {
            Publisher publisher = publisherRepository.findById(bookDto.getPublisher().getId().longValue())
                    .orElseThrow(() -> new BookApiException("Publisher not found with id: " + bookDto.getPublisher().getId()));
            existingBook.setPublisher(publisher);
        } else {
            existingBook.setPublisher(null);
        }

        // Update authors
        existingBook.getAuthors().clear();
        if (bookDto.getAuthors() != null && !bookDto.getAuthors().isEmpty()) {
            Set<Long> authorIds = bookDto.getAuthors().stream()
                    .map(authorDto -> authorDto.getId().longValue())
                    .collect(Collectors.toSet());
            Set<Author> authors = authorRepository.findByIdIn(authorIds);

            if (authors.size() != authorIds.size()) {
                throw new BookApiException("One or more authors not found");
            }
            existingBook.setAuthors(authors);
        }

        // Update categories
        existingBook.getCategories().clear();
        if (bookDto.getCategories() != null && !bookDto.getCategories().isEmpty()) {
            Set<Long> categoryIds = bookDto.getCategories().stream()
                    .map(categoryDto -> categoryDto.getId().longValue())
                    .collect(Collectors.toSet());
            Set<Category> categories = categoryRepository.findByIdIn(categoryIds);

            if (categories.size() != categoryIds.size()) {
                throw new BookApiException("One or more categories not found");
            }
            existingBook.setCategories(categories);
        }

        Book updatedBook = bookRepository.save(existingBook);

        log.info("Book updated successfully with id: {}", updatedBook.getId());
        return bookMapper.toDto(updatedBook);
    }

    @Override
    public void deleteBook(Integer id) {
        log.info("Deleting book with id: {}", id);

        if (id == null) {
            throw new BookApiException("Book ID cannot be null");
        }

        Book book = bookRepository.findActiveBookById(id.longValue())
                .orElseThrow(() -> new BookApiException("Book not found with id: " + id));

        // Soft delete - mark as deleted instead of removing from database
        book.setDeleted(true);
        bookRepository.save(book);

        log.info("Book soft deleted successfully with id: {}", id);
    }

    @Override
    public List<BookDto> getBooksByCategory(Long categoryId) {
        List<Book> books = bookRepository.findByCategoryId(categoryId);

        if (books.isEmpty()) {
            throw new BookApiException("No books found for category id: " + categoryId);
        }

        return books.stream()
                .map(b -> new BookDto(
                        b.getId(),
                        b.getTitle(),
                        b.getPublisher() != null
                                ? new PublisherDto(b.getPublisher().getId(), b.getPublisher().getName(),b.getPublisher().getAddress())
                                : null,
                        b.getLanguage(),
                        b.getPublicationYear(),
                        b.getIsbn(),
                        b.getEdition(),
                        b.getSummary(),
                        b.getCoverImageUrl(),
                        b.isDeleted(),
                        b.getAuthors().stream()
                                .map(a -> new AuthorDto(a.getId(), a.getName(), a.getBio()))
                                .collect(Collectors.toSet()),
                        b.getCategories().stream()
                                .map(c -> new CategoryDto(c.getId(), c.getName()))
                                .collect(Collectors.toSet())
                ))
                .collect(Collectors.toList());
    }

    private void validateBookDto(BookDto bookDto) {
        if (bookDto == null) {
            throw new BookApiException("Book data cannot be null");
        }

        if (bookDto.getTitle() == null || bookDto.getTitle().trim().isEmpty()) {
            throw new BookApiException("Book title cannot be null or empty");
        }

        if (bookDto.getPublicationYear() != null && bookDto.getPublicationYear() < 0) {
            throw new BookApiException("Publication year cannot be negative");
        }

        if (bookDto.getIsbn() != null && bookDto.getIsbn().trim().isEmpty()) {
            throw new BookApiException("ISBN cannot be empty if provided");
        }
    }

}