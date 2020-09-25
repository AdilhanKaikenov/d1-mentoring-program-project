DROP FUNCTION IF EXISTS add_book(varchar, int, int, varchar, int, varchar, varchar);
DROP FUNCTION IF EXISTS find_book_by_id(int);
DROP FUNCTION IF EXISTS delete_book_by_id(int);
DROP FUNCTION IF EXISTS update_book(int, varchar, int, int, varchar, int, varchar, varchar);

CREATE OR REPLACE FUNCTION add_book
(title varchar, publish_year int, genre int, description varchar, total_amount int,
author_firstname varchar, author_lastname varchar)
RETURNS integer AS

$BODY$
DECLARE
	author_id integer;
	book_id integer;
BEGIN
SELECT id INTO author_id FROM authors WHERE firstname = author_firstname AND lastname = author_lastname;

IF author_id IS NULL THEN
    INSERT INTO authors(firstname, lastname) VALUES (author_firstname, author_lastname) RETURNING authors.id INTO author_id;
END IF;

INSERT INTO book (title, author, publish_year, genre, description, total_amount) VALUES (title, author_id, publish_year, genre, description, total_amount) RETURNING book.id INTO book_id;

RETURN book_id;
END;
$BODY$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION find_book_by_id(arg_id int) RETURNS
TABLE(id int, title varchar, author_id int, author_firstname varchar, author_lastname varchar,
publish_year int, genre int, description varchar, total_amount int) AS

$BODY$
BEGIN

RETURN QUERY SELECT book.id, book.title,
authors.id as author_id, authors.firstname as author_firstname, authors.lastname as author_lastname,
book.publish_year, genre.id AS genre, book.description, book.total_amount
FROM book
    INNER JOIN authors ON book.author = authors.id
    INNER JOIN genre ON book.genre = genre.id
WHERE book.id = arg_id;

END;
$BODY$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION delete_book_by_id(arg_id int) RETURNS void AS

$BODY$
DECLARE
	author_id integer;
	author_num integer;
BEGIN

SELECT book.author INTO author_id FROM book WHERE book.id = arg_id;

DELETE FROM book WHERE id = arg_id;

SELECT COUNT(*) INTO author_num
FROM book
	INNER JOIN authors ON book.author = authors.id
WHERE authors.id = author_id;

IF author_num = 0 THEN
	DELETE FROM authors WHERE id = author_id;
END IF;

END;
$BODY$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION update_book(arg_id int, arg_title varchar, arg_publish_year int, arg_genre int, arg_description varchar, arg_total_amount int,
arg_author_firstname varchar, arg_author_lastname varchar) RETURNS
TABLE(book_id int, title varchar, author_id int, author_firstname varchar, author_lastname varchar,
publish_year int, genre int, description varchar, total_amount int) AS

$BODY$
DECLARE
	author_id integer;
BEGIN
SELECT id INTO author_id FROM authors WHERE firstname = author_firstname AND lastname = author_lastname;

IF author_id IS NULL THEN
	INSERT INTO authors(firstname, lastname) VALUES (arg_author_firstname, arg_author_lastname) RETURNING authors.id INTO author_id;
END IF;

UPDATE book
SET title = arg_title, author = author_id, publish_year = arg_publish_year, genre = arg_genre, description = arg_description, total_amount = arg_total_amount
WHERE id = arg_id;

RETURN QUERY SELECT book.id, book.title,
authors.id as author_id, authors.firstname as author_firstname, authors.lastname as author_lastname,
book.publish_year, genre.id AS genre, book.description, book.total_amount
FROM book
    INNER JOIN authors ON book.author = authors.id
    INNER JOIN genre ON book.genre = genre.id
WHERE book.id = arg_id;

END;
$BODY$
LANGUAGE 'plpgsql';