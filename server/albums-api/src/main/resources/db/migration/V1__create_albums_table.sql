CREATE TABLE albums (
	id SERIAL PRIMARY KEY,
	title VARCHAR(100) NOT NULL,
	artist VARCHAR(50) NOT NULL,
	year INTEGER not NULL,
	done boolean DEFAULT false
);

INSERT INTO albums (title, artist, year) VALUES
	('Electric Ladyland', 'Jimi Hendrix', 1968),
	('Blue', 'Joni Mitchell', 1971),
	('Dance Fever', 'Florence and the Machine', 2022);