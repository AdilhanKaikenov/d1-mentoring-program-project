DROP FUNCTION IF EXISTS generate_users(int);
DROP FUNCTION IF EXISTS generate_friendships(int);
DROP FUNCTION IF EXISTS generate_posts(int);
DROP FUNCTION IF EXISTS generate_likes(int);

CREATE FUNCTION generate_users(rowsnumber INT)
  RETURNS VOID AS $$
DECLARE
    names TEXT ARRAY DEFAULT array['OLIVER', 'HARRY', 'JACK', 'GEORGE', 'NOAH', 'CHARLIE', 'JACOB', 'ALFIE', 'FREDDIE', 'OSCAR', 'LEO', 'LOGAN', 'ARCHIE', 'THEO', 'THOMAS',
'JAMES', 'JOSHUA', 'HENRY', 'WILLIAM', 'MAX', 'LUCAS', 'ETHAN', 'ARTHUR', 'MASON', 'ISAAC', 'HARRISON', 'TEDDY', 'FINLEY', 'DANIEL', 'RILEY',
'EDWARD', 'JOSEPH', 'ALEXANDER', 'ADAM', 'REGGIE', 'SAMUEL', 'JAXON', 'SEBASTIAN', 'ELIJAH', 'HARLEY', 'TOBY', 'ARLO', 'DYLAN', 'JUDE', 'BENJAMIN',
'RORY', 'TOMMY', 'JAKE', 'LOUIE', 'CARTER', 'JENSON', 'HUGO', 'BOBBY', 'FRANKIE', 'OLLIE', 'ZACHARY', 'DAVID', 'ALBIE', 'LEWIS', 'LUCA', 'RONNIE',
'JACKSON', 'MATTHEW', 'ALEX', 'HARVEY', 'REUBEN', 'JAYDEN', 'CALEB', 'HUNTER', 'THEODORE', 'NATHAN', 'BLAKE', 'LUKE', 'ELLIOT', 'ROMAN', 'STANLEY',
'DEXTER', 'MICHAEL', 'ELLIOTT', 'TYLER', 'RYAN', 'ELLIS', 'FINN', 'ALBERT', 'KAI', 'LIAM', 'CALLUM','LOUIS','AARON','EZRA', 'LEON', 'CONNOR', 'GRAYSON',
'JAMIE', 'AIDEN','GABRIEL', 'AUSTIN','LINCOLN', 'ELI', 'BEN'];
    surnames TEXT ARRAY DEFAULT array['SMITH', 'JOHNSON', 'WILLIAMS', 'JONES', 'BROWN', 'DAVIS','MILLER', 'WILSON', 'MOORE', 'TAYLOR', 'ANDERSON', 'THOMAS', 'JACKSON', 'WHITE', 'HARRIS', 'MARTIN', 'THOMPSON',
'GARCIA', 'MARTINEZ', 'ROBINSON', 'CLARK', 'RODRIGUEZ', 'LEWIS', 'LEE', 'WALKER', 'HALL', 'ALLEN', 'YOUNG', 'HERNANDEZ', 'KING', 'WRIGHT', 'LOPEZ', 'HILL',
'SCOTT', 'GREEN', 'ADAMS', 'BAKER', 'GONZALEZ', 'NELSON', 'CARTER', 'MITCHELL', 'PEREZ', 'ROBERTS', 'TURNER', 'PHILLIPS', 'CAMPBELL', 'PARKER', 'EVANS', 'EDWARDS',
'COLLINS', 'STEWART', 'SANCHEZ', 'MORRIS', 'ROGERS', 'REED', 'COOK', 'MORGAN', 'WELSH', 'BELL', 'MURPHY', 'BAILEY', 'RIVERA', 'COOPER', 'RICHARDSON', 'COX',
'HOWARD', 'WARD', 'TORRES', 'PETERSON', 'GRAY', 'RAMIREZ', 'JAMES', 'WATSON', 'BROOKS', 'KELLY', 'SANDERS', 'PRICE', 'WELSH', 'BENNETT', 'WOOD', 'BARNES',
'ROSS', 'HENDERSON', 'COLEMAN', 'JENKINS', 'PERRY', 'POWELL', 'WELSH', 'LONG','PATTERSON', 'HUGHES', 'FLORES', 'WASHINGTON', 'BUTLER', 'SIMMONS', 'FOSTER', 'GONZALES',
'BRYANT', 'ALEXANDER', 'RUSSELL', 'GRIFFIN', 'WELSH', 'DIAZ', 'HAYES'];
    birth_date date;
BEGIN
FOR i IN 1..rowsnumber LOOP
    SELECT TIMESTAMP '2008-01-10 10:00:00' +
        random() * (TIMESTAMP '1980-01-20 20:00:00' -
        TIMESTAMP '2008-01-10 10:00:00') INTO birth_date;

    INSERT INTO "Users"(id, name, surname, birth_date)
        VALUES(i, names[floor(random() * 100 + 1)], surnames[floor(random() * 100 + 1)], birth_date);
END LOOP;
END;
$$
LANGUAGE 'plpgsql';

CREATE FUNCTION generate_friendships(friendships_number INT)
  RETURNS VOID AS $$
DECLARE
    user1_id integer;
    user2_id integer;
    friendships_count integer;
BEGIN
LOOP EXIT WHEN friendships_number = 0;
	SELECT id FROM "Users" ORDER BY random() LIMIT 1 INTO user1_id;
	SELECT id FROM "Users" WHERE id != user1_id ORDER BY random() LIMIT 1 INTO user2_id;

	SELECT COUNT(*) FROM "Friendships" WHERE userid1 = user1_id AND userid2 = user2_id INTO friendships_count;

	IF friendships_count = 0 THEN
        INSERT INTO "Friendships"(userid1, userid2, f_date)
            VALUES(user1_id, user2_id, now());

        INSERT INTO "Friendships"(userid1, userid2, f_date)
            VALUES(user2_id, user1_id, now());

	    friendships_number := friendships_number - 1 ;
	END IF;
END LOOP;
END;
$$
LANGUAGE 'plpgsql';

CREATE FUNCTION generate_posts(rowsnumber INT)
  RETURNS VOID AS $$
DECLARE
	user_id integer;
	random_date date;
BEGIN
FOR i IN 1..rowsnumber LOOP
    SELECT id FROM "Users" ORDER BY random() LIMIT 1 INTO user_id;
    SELECT now() + random() * (TIMESTAMP '2017-01-20 20:00:00' - now()) INTO random_date;

    INSERT INTO "Posts"(id, userid, text, post_date)
        VALUES(i, user_id, 'Test text content', random_date);
END LOOP;
END;
$$
LANGUAGE 'plpgsql';

CREATE FUNCTION generate_likes(rowsnumber INT)
  RETURNS VOID AS $$
DECLARE
	post_id integer;
	user_id integer;
	likes_count integer;
	random_date date;
BEGIN
LOOP EXIT WHEN rowsnumber = 0;
	SELECT id FROM "Posts" ORDER BY random() LIMIT 1 INTO post_id;
	SELECT id FROM "Users" ORDER BY random() LIMIT 1 INTO user_id;

	SELECT COUNT(*) FROM "Likes" WHERE postid = post_id AND userid = user_id INTO likes_count;

	IF likes_count = 0 THEN
		SELECT '2016-12-01 00:00:00'::DATE + random() * (TIMESTAMP '2016-01-01 20:00:00' - '2016-12-30 20:00:00') INTO random_date;

		INSERT INTO "Likes"(postid, userid, like_date)
			VALUES(post_id, user_id, random_date);

		rowsnumber := rowsnumber - 1 ;
	END IF;
END LOOP;
END;
$$
LANGUAGE 'plpgsql';