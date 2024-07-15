BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role,first_name,last_name,title)
VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'User First Name', 'User Last Name', 'Default Title');

INSERT INTO users (username,password_hash,role,first_name,last_name,title)
VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'Admin First Name', 'Admin Last Name', 'Default Title');

INSERT INTO time_off_requests (user_id, request_date, start_date, end_date, status, request_reason, comment)
VALUES (1, '2024-07-15', '2024-07-22', '2024-07-26', 'approved', 'Need time off.', 'Take all the time you need.');

COMMIT TRANSACTION;
