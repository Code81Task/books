-- Publishers table (normalized to avoid redundancy)
CREATE TABLE publishers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address TEXT,

);

-- Authors table
CREATE TABLE authors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    bio TEXT
);

-- Categories table (hierarchical: self-referencing for parent categories)
CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
);

-- Books table (core entity with extended metadata)
CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    publisher_id INTEGER REFERENCES publishers(id) ON DELETE SET NULL,
    language VARCHAR(50),
    publication_year INTEGER,
    isbn VARCHAR(20) UNIQUE,
    edition VARCHAR(50),
    summary TEXT,
    cover_image_url VARCHAR(255)
);

-- Book-Authors junction (many-to-many for multiple authors)
CREATE TABLE book_authors (
    book_id INTEGER REFERENCES books(id) ON DELETE CASCADE,
    author_id INTEGER REFERENCES authors(id) ON DELETE CASCADE,
    PRIMARY KEY (book_id, author_id)
);

-- Book-Categories junction (many-to-many)
CREATE TABLE book_categories (
    book_id INTEGER REFERENCES books(id) ON DELETE CASCADE,
    category_id INTEGER REFERENCES categories(id) ON DELETE CASCADE,
    PRIMARY KEY (book_id, category_id)
);