BEGIN TRANSACTION;

-- Drop the existing authors table if it exists
DROP TABLE IF EXISTS authors;

-- Create a new authors table
CREATE TABLE authors (
   id INTEGER PRIMARY KEY AUTOINCREMENT,
   name TEXT NOT NULL
);

-- Drop the existing genres table if it exists
DROP TABLE IF EXISTS genres;

-- Create a new genres table
CREATE TABLE genres (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(100)
);

-- Drop the existing books table if it exists
DROP TABLE IF EXISTS books;

-- Create a new books table
CREATE TABLE books (
    id INTEGER PRIMARY KEY,
    name TEXT,
    title VARCHAR(255),
    language VARCHAR(100),
    pages INTEGER,
    auth_id INTEGER,
    genre_id INTEGER, -- Added genre_id column
    FOREIGN KEY (auth_id) REFERENCES authors(id),
    FOREIGN KEY (genre_id) REFERENCES genres(id)
);

COMMIT;

-- Insert data into the genres, authors, and books tables
INSERT INTO authors (name) VALUES ('Stephen King');
INSERT INTO authors (name) VALUES ('J.K. Rowling');

INSERT INTO genres (name) VALUES ('romance');
INSERT INTO genres (name) VALUES ('history');

INSERT INTO books (name, title, language, pages, auth_id, genre_id) VALUES ('The Shining', 'The Shining', 'English', 447, 1, 1);
INSERT INTO books (name, title, language, pages, auth_id, genre_id) VALUES ('It', 'It', 'English', 1138, 1, 1);
INSERT INTO books (name, title, language, pages, auth_id, genre_id) VALUES ('Harry Potter and the Philosopher''s Stone', 'Harry Potter and the Philosopher''s Stone', 'English', 223, 2, 2);
INSERT INTO books (name, title, language, pages, auth_id, genre_id) VALUES ('Harry Potter and the Chamber of Secrets', 'Harry Potter and the Chamber of Secrets', 'English', 251, 2, 2);

