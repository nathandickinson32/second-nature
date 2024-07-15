BEGIN TRANSACTION;

DROP TABLE IF EXISTS time_off_requests;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	first_name VARCHAR (100) NOT NULL,
	last_name VARCHAR (100) NOT NULL,
	title VARCHAR (100) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE time_off_requests (
    request_id SERIAL PRIMARY KEY,
    user_id INT,
    request_date DATE NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR (8),
    request_reason VARCHAR (200),
    comment VARCHAR (200),
    review_date DATE,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

COMMIT TRANSACTION;
