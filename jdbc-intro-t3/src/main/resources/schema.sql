 DROP TABLE IF EXISTS public."Users";
 DROP TABLE IF EXISTS public."Friendships";
 DROP TABLE IF EXISTS public."Posts";
 DROP TABLE IF EXISTS public."Likes";

CREATE TABLE public."Users"
(
  id integer NOT NULL,
  name character varying(100),
  surname character varying(100),
  birth_date date,
  CONSTRAINT "Users_pkey" PRIMARY KEY (id)
);

CREATE TABLE public."Friendships"
(
  userid1 integer NOT NULL,
  userid2 integer NOT NULL,
  f_date date,
  CONSTRAINT "Friendships_pkey" PRIMARY KEY (userid1, userid2)
);

CREATE TABLE public."Posts"
(
  id integer NOT NULL,
  userid integer,
  text character varying(1000),
  post_date date,
  CONSTRAINT "Posts_pkey" PRIMARY KEY (id)
);

CREATE TABLE public."Likes"
(
  postid integer NOT NULL,
  userid integer,
  like_date date,
  CONSTRAINT "Likes_pkey" PRIMARY KEY (postid, userid)
);