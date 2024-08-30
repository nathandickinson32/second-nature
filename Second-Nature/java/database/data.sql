BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role,first_name,last_name,title)
VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'User First Name', 'User Last Name', 'Default Title');

INSERT INTO users (username,password_hash,role,first_name,last_name,title)
VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'Admin First Name', 'Admin Last Name', 'Default Title');

INSERT INTO users (username,password_hash,role,first_name,last_name,title)
VALUES ('boss','$2a$10$HEWndqvi9gOWgcw2b.bUNu0AS31AzXBc7YAfAiklkaHXkTAE1HabW','ROLE_USER', 'Boss First Name', 'Boss Last Name', 'manager');

INSERT INTO users (username,password_hash,role,first_name,last_name,title)
VALUES ('bob','$2a$10$5MDhl03/4h0Mn14.pi/HiOSkYZfQUtfyiRAcMfPgDECHF4EqAjiVq','ROLE_USER', 'Bob', 'Boberson', 'Default Title');

INSERT INTO time_off_requests (user_id, request_date, start_date, end_date, status, request_reason, comment, review_date)
VALUES (1, '2024-07-15', '2024-07-22', '2024-07-26', 'Approved', 'Need time off.', 'Take all the time you need.', '2024-07-16');

INSERT INTO professional_check_in (manager_id, employee_id, notes, date)
VALUES (3, 1, 'Default note', '2024-07-26');

INSERT INTO kudos (giver_user_id, receiver_user_id, date, title, notes, is_archived)
VALUES (3, 1, '2024-07-26', 'Default Title', 'Default Note', false);

INSERT INTO type (name) VALUES ('Mower'),('Blower');

INSERT INTO equipment (serial_number, model, name, type_id, starting_hours, entered_by_user_id, entered_on_date, notes, is_active, active_notes, is_archived)
VALUES
('V-Ride1', 'JSCGPNHEQ001', 'Scag V-Ride', 1, 2183, 3, '2024-08-23', '52 inch deck, has QwikChute', true, 'No Current Issues', false),
('N1100815', 'STTII-61V-37BV-EFI', 'Scag Turf-Tiger II', 1, 0, 3, '2024-08-23', '61 inch deck, has QwikChute, bagging unit and ClamShell Unit', true, 'No Current Issues', false),
('406990672', '72523', 'Toro MultiForce', 1, 0, 3, '2024-08-23', '61 inch deck, has QwikChute', false, 'SMOKING, Burning spilled oil from tipping accident', false),
('123607GC', 'WSZK61S61E8E-49S', 'Wright Stander 1',1, 0, 3, '2024-08-23', '61 inch deck, Currently the oldest Wright Stander, has QwikChute, Yellow Blade Guards', true, 'No Current Issues', false),
('158298GA', 'WSZK61S61E8E-49S', 'Wright Stander 2', 1, 0, 3, '2024-08-23', '61 inch deck, Currently the newest Wright Stander, has QwikChute, Black Blade Guards', true, 'No Current Issues', false),
('WindStorm1', 'LSCGPNHEQ001', 'Scag WindStorm', 2, 0, 3, '2024-08-23', 'Primarily used during Spring and Fall', true, 'No Current Issues', false);

INSERT INTO maintenance_tickets (equipment_id, hours, entered_by_user_id, date, notes, is_complete, is_archived)
VALUES (1, 0, 3, '2024-07-26', 'Default Note', false, false);

INSERT INTO maintenance_performed (equipment_id, ticket_id, description, performed_by, performed_on_date, notes, hours)
VALUES (1, 1, 'Default Description', 'Default Performed By', '2024-08-27', 'Default Notes', 0);

COMMIT TRANSACTION;
