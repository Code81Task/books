-- Publishers
INSERT INTO publishers (name, address) VALUES
('Springer Nature', 'Tiergartenstr. 17, Heidelberg, Germany'),
('Oxford University Press', 'Great Clarendon Street, Oxford, UK'),
('Dar Al Shorouk', 'Cairo, Egypt');

-- Authors
INSERT INTO authors (name, bio) VALUES
('Donald Knuth', 'Computer scientist, author of The Art of Computer Programming.'),
('Stephen Hawking', 'Theoretical physicist, author of A Brief History of Time.'),
('Naguib Mahfouz', 'Egyptian novelist, Nobel Prize in Literature winner.'),
('Eric Evans', 'Software designer, author of Domain-Driven Design.'),
('Jane Austen', 'English novelist, known for Pride and Prejudice.');

-- Categories
INSERT INTO categories (name) VALUES
('Computer Science'),
('Physics'),
('Literature'),
('Software Design'),
('Philosophy');

-- Books
INSERT INTO books (title, publisher_id, language, publication_year, isbn, edition, summary, cover_image_url)
VALUES
('The Art of Computer Programming', 1, 'English', 1997, '9780201896831', '3rd', 'Comprehensive monograph written by Donald Knuth covering algorithms and programming.', 'https://example.com/taocp.jpg'),
('A Brief History of Time', 2, 'English', 1988, '9780553380163', '1st', 'An overview of cosmology for the general reader.', 'https://example.com/briefhistory.jpg'),
('The Cairo Trilogy', 3, 'Arabic', 1956, '9789770906075', '1st', 'A series of novels depicting life in Cairo through three generations.', 'https://example.com/cairotrilogy.jpg'),
('Domain-Driven Design', 1, 'English', 2003, '9780321125217', '1st', 'Tackling complexity in software with domain modeling.', 'https://example.com/ddd.jpg'),
('Pride and Prejudice', 2, 'English', 1813, '9780141439518', '1st', 'Classic romantic novel of manners by Jane Austen.', 'https://example.com/pride.jpg');

-- Book-Authors
INSERT INTO book_authors (book_id, author_id) VALUES
(1, 1),  -- The Art of Computer Programming -> Donald Knuth
(2, 2),  -- A Brief History of Time -> Stephen Hawking
(3, 3),  -- The Cairo Trilogy -> Naguib Mahfouz
(4, 4),  -- Domain-Driven Design -> Eric Evans
(5, 5);  -- Pride and Prejudice -> Jane Austen

-- Book-Categories
INSERT INTO book_categories (book_id, category_id) VALUES
(1, 1), -- The Art of Computer Programming -> Computer Science
(2, 2), (2, 5), -- A Brief History of Time -> Physics, Philosophy
(3, 3), -- The Cairo Trilogy -> Literature
(4, 1), (4, 4), -- Domain-Driven Design -> Computer Science, Software Design
(5, 3); -- Pride and Prejudice -> Literature
