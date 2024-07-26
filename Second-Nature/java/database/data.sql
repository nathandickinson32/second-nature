BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role,first_name,last_name,title)
VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'User First Name', 'User Last Name', 'Default Title');

INSERT INTO users (username,password_hash,role,first_name,last_name,title)
VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'Admin First Name', 'Admin Last Name', 'Default Title');

INSERT INTO users (username,password_hash,role,first_name,last_name,title)
VALUES ('boss','$2a$10$HEWndqvi9gOWgcw2b.bUNu0AS31AzXBc7YAfAiklkaHXkTAE1HabW','ROLE_USER', 'Boss First Name', 'Boss Last Name', 'manager');

INSERT INTO time_off_requests (user_id, request_date, start_date, end_date, status, request_reason, comment, review_date)
VALUES (1, '2024-07-15', '2024-07-22', '2024-07-26', 'Approved', 'Need time off.', 'Take all the time you need.', '2024-07-16');

INSERT INTO professional_check_in (manager_id, employee_id, notes, date)
VALUES (3, 1, 'Default note', '2024-07-26');

INSERT INTO kudos (giver_user_id, receiver_user_id, date, title, notes)
VALUES (3, 1, '2024-07-26', 'Default Title', 'Default Note');

INSERT INTO equipment (serial_number, model, name, starting_hours, entered_by_user_id, entered_on_date, notes, active, active_note)
VALUES ('Default Serial Number', 'Default Model', 'Default Name', 0, 3, '2024-07-26', 'Default Note', true, 'Default Active Note');

INSERT INTO maintenance (equipment_id, hours, entered_by_user_id, date, notes, complete)
VALUES (1, 0, 3, '2024-07-26', 'Default Note', false);

INSERT INTO maintenance_performed (equipment_id, ticket_id, description, performed_by, notes)
VALUES (1, 1, 'Default Description', 'Default Performed By', 'Default Notes');

COMMIT TRANSACTION;
