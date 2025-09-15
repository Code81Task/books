-- Publishers table (normalized to avoid redundancy)
CREATE TABLE publishers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address TEXT

);

-- Authors table
CREATE TABLE authors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    bio TEXT
);

-- Categories table (hierarchical: self-referencing for parent categories)
CREATE TABLE categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Books table (core entity with extended metadata)
CREATE TABLE books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    publisher_id BIGINT,
    language VARCHAR(50),
    publication_year INTEGER,
    isbn VARCHAR(20) UNIQUE,
    edition VARCHAR(50),
    summary TEXT,
    cover_image_url VARCHAR(255),
    is_deleted BOOLEAN DEFAULT FALSE
);


-- Book-Authors junction (many-to-many for multiple authors)
CREATE TABLE book_authors (
    book_id BIGINT REFERENCES books(id) ON DELETE CASCADE,
    author_id BIGINT REFERENCES authors(id) ON DELETE CASCADE,
    PRIMARY KEY (book_id, author_id)
);

-- Book-Categories junction (many-to-many)
CREATE TABLE book_categories (
    book_id BIGINT REFERENCES books(id) ON DELETE CASCADE,
    category_id BIGINT REFERENCES categories(id) ON DELETE CASCADE,
    PRIMARY KEY (book_id, category_id)
);

