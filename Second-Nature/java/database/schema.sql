BEGIN TRANSACTION;

DROP TABLE IF EXISTS maintenance_performed;
DROP TABLE IF EXISTS maintenance_ticket;
DROP TABLE IF EXISTS equipment;
DROP TABLE IF EXISTS kudos;
DROP TABLE IF EXISTS professional_check_in;
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

CREATE TABLE professional_check_in (
    check_in_id SERIAL PRIMARY KEY,
    manager_id INT NOT NULL,
    employee_id INT NOT NULL,
    notes VARCHAR,
    date DATE NOT NULL,
    FOREIGN KEY (manager_id) REFERENCES users (user_id),
    FOREIGN KEY (employee_id) REFERENCES users (user_id)
);

CREATE TABLE kudos (
    kudos_id SERIAL PRIMARY KEY,
    giver_user_id INT NOT NULL,
    receiver_user_id INT NOT NULL,
    date DATE NOT NULL,
    title VARCHAR NOT NULL,
    notes VARCHAR,
    archive BOOLEAN NOT NULL,
    FOREIGN KEY (giver_user_id) REFERENCES users (user_id),
    FOREIGN KEY (receiver_user_id) REFERENCES users (user_id)
);

CREATE TABLE equipment (
    equipment_id SERIAL PRIMARY KEY,
    serial_number VARCHAR NOT NULL,
    model VARCHAR NOT NULL,
    name VARCHAR,
    starting_hours INT NOT NULL,
    entered_by_user_id INT NOT NULL,
    entered_on_date DATE NOT NULL,
    notes VARCHAR,
    is_active BOOLEAN NOT NULL,
    active_note VARCHAR,
    updated_by_user_id INT,
    updated_on_date DATE,
    is_archived BOOLEAN NOT NULL,
    FOREIGN KEY (entered_by_user_id) REFERENCES users (user_id),
    FOREIGN KEY (updated_by_user_id) REFERENCES users (user_id)
);

CREATE TABLE maintenance_ticket (
    ticket_id SERIAL PRIMARY KEY,
    equipment_id INT NOT NULL,
    hours INT NOT NULL,
    entered_by_user_id INT NOT NULL,
    date DATE NOT NULL,
    notes VARCHAR,
    is_complete BOOLEAN NOT NULL,
    FOREIGN KEY (entered_by_user_id) REFERENCES users (user_id)
);

CREATE TABLE maintenance_performed (
    maintenance_performed_id SERIAL PRIMARY KEY,
    equipment_id INT NOT NULL,
    ticket_id INT NOT NULL,
    description VARCHAR NOT NULL,
    performed_by VARCHAR NOT NULL,
    notes VARCHAR,
    FOREIGN KEY (equipment_id) REFERENCES equipment (equipment_id),
    FOREIGN KEY (ticket_id) REFERENCES maintenance_ticket (ticket_id)
);

COMMIT TRANSACTION;
