DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS authors;


CREATE TABLE public.book
(
  id serial NOT NULL,
  title character varying(100) NOT NULL,
  author integer NOT NULL,
  publish_year integer NOT NULL,
  genre integer NOT NULL,
  description character varying(1600) NOT NULL,
  total_amount integer NOT NULL,
  CONSTRAINT book_pkey PRIMARY KEY (id)
);

CREATE UNIQUE INDEX "BOOK_ID_uindex" ON public.book USING btree (id);

CREATE TABLE public.authors
(
  id serial NOT NULL,
  firstname character varying(100) NOT NULL,
  lastname character varying(100) NOT NULL,
  CONSTRAINT authors_pkey PRIMARY KEY (id)
);

CREATE UNIQUE INDEX "AUTHORS_id_uindex" ON public.authors USING btree (id);

CREATE TABLE public.genre
(
  id serial NOT NULL,
  type character varying(25) NOT NULL,
  CONSTRAINT genre_pkey PRIMARY KEY (id)
);

CREATE UNIQUE INDEX "GENRE_ID_uindex" ON public.genre USING btree (id);

CREATE UNIQUE INDEX "GENRE_TYPE_uindex" ON public.genre USING btree (type COLLATE pg_catalog."default");