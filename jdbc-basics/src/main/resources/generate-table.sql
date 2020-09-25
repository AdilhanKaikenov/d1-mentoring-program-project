DROP FUNCTION IF EXISTS generate_tables(int);

CREATE FUNCTION generate_tables(tablenumber INT)
  RETURNS VOID AS $$
DECLARE
	table_name TEXT;
	column_name TEXT;
	column_number INTEGER;
	data_type TEXT;
	data_type_array TEXT ARRAY DEFAULT array['BOOLEAN', 'VARCHAR(%s)', 'INTEGER', 'DATE'];
	data_size_array INTEGER ARRAY DEFAULT ARRAY[100, 200, 300, 400, 500, 600, 700, 800, 900, 1000];
BEGIN
	FOR i IN 1..tablenumber LOOP
		SELECT CONCAT('table_', FLOOR(RANDOM() * 10000 + 1), '_', FLOOR(EXTRACT(EPOCH FROM (SELECT NOW())))) INTO table_name;
		SELECT FLOOR(RANDOM() * 10 + 1) INTO column_number;
		EXECUTE FORMAT('CREATE TABLE %s(id INTEGER NOT NULL, CONSTRAINT "%s_pkey" PRIMARY KEY(id));', TRIM(table_name), TRIM(table_name));

		FOR i IN 1..column_number LOOP
			SELECT CONCAT('column_', FLOOR(RANDOM() * 10000 + 1), '_', FLOOR(EXTRACT(EPOCH FROM (SELECT NOW())))) INTO column_name;

			SELECT FORMAT(data_type_array[FLOOR(RANDOM() * ARRAY_LENGTH(data_type_array, 1) + 1)],
					data_size_array[FLOOR(RANDOM() * ARRAY_LENGTH(data_size_array, 1) + 1)]) INTO data_type;

			EXECUTE FORMAT('ALTER TABLE %s ADD COLUMN %s %s;', TRIM(table_name), TRIM(column_name), data_type);
		END LOOP;

	END LOOP;
END;
$$
LANGUAGE 'plpgsql';