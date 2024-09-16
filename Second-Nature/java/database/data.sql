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

INSERT INTO type (name) VALUES ('Mower'),('Blower'),('Skid Steer Loader'),('Tractor'),('Mini Skid Steer Loader'),('Fertilizer Spreader'),('Trailer'),('String Trimmer'),('Hedge Trimmer'),('Push Mower'),('Chainsaw'),('Demosaw');

INSERT INTO equipment (serial_number, model, name, type_id, starting_hours, entered_by_user_id, entered_on_date, notes, is_active, active_notes, is_archived)
VALUES
('V-Ride1', 'JSCGPNHEQ001', 'Scag V-Ride', 1, 2183, 3, '2024-08-23', '52 inch deck, has QwikChute', true, 'No Current Issues', false),
('N1100815', 'STTII-61V-37BV-EFI', 'Scag Turf-Tiger II', 1, 0, 3, '2024-08-23', '61 inch deck, has QwikChute, bagging unit and ClamShell Unit', true, 'No Current Issues', false),
('406990672', '72523', 'Toro MultiForce', 1, 0, 3, '2024-08-23', '61 inch deck, has QwikChute', false, 'SMOKING, Burning spilled oil from tipping accident', false),
('123607GC', 'WSZK61S61E8E-49S', 'Wright Stander 1',1, 0, 3, '2024-08-23', '61 inch deck, Currently the oldest Wright Stander, has QwikChute, Yellow Blade Guards', true, 'No Current Issues', false),
('158298GA', 'WSZK61S61E8E-49S', 'Wright Stander 2', 1, 0, 3, '2024-08-23', '61 inch deck, Currently the newest Wright Stander, has QwikChute, Black Blade Guards', true, 'No Current Issues', false),
('WindStorm1', 'LSCGPNHEQ001', 'Scag WindStorm', 2, 0, 3, '2024-08-23', 'Primarily used during Spring and Fall', true, 'No Current Issues', false),
('476', 'BR600', 'Stihl Backpack Blower 1', 2, 0, 3, '2024-09-10', 'Smaller backpack blower, smaller nozzle', true, 'No Current Issues', false),
('296922238', 'BR600', 'Stihl Backpack Blower 2', 2, 0, 3, '2024-09-10', 'Smaller backpack blower, smaller nozzle', true, 'No Current Issues', false),
('768', 'BR600', 'Stihl Backpack Blower 3', 2, 0, 3, '2024-09-10', 'Smaller backpack blower, smaller nozzle', true, 'No Current Issues', false),
('530932964', 'BR600', 'Stihl Backpack Blower 4', 2, 0, 3, '2024-09-10', 'Smaller backpack blower, smaller nozzle', true, 'No Current Issues', false),
('528880823', 'BR600', 'Stihl Backpack Blower 5', 2, 0, 3, '2024-09-10', 'Smaller backpack blower, smaller nozzle', true, 'No Current Issues', false),
('528522433', 'BR600', 'Stihl Backpack Blower 6', 2, 0, 3, '2024-09-10', 'Smaller backpack blower, smaller nozzle', true, 'No Current Issues', false),
('537896435', 'BR600', 'Stihl Backpack Blower 7', 2, 0, 3, '2024-09-10', 'Smaller backpack blower, smaller nozzle', true, 'No Current Issues', false),
('4154', 'BR700', 'Stihl Backpack Blower 8', 2, 0, 3, '2024-09-10', 'Medium size backpack blower, large nozzle', true, 'No Current Issues', false),
('528488539', 'BR800', 'Stihl Backpack Blower 9', 2, 0, 3, '2024-09-10', 'large backpack blower, large nozzle', true, 'No Current Issues', false),
('525245141', 'BR800', 'Stihl Backpack Blower 10', 2, 0, 3, '2024-09-10', 'large backpack blower, large nozzle', true, 'No Current Issues', false),
('BACKPACK1', 'BR800', 'Stihl Backpack Blower 11', 2, 0, 3, '2024-09-10', 'large backpack blower, large nozzle', true, 'No Current Issues', false),
('B3NK30446', 'T595', '2019 Bobcat', 3, 0, 3, '2024-09-09', '2019 Bobcat', true, 'No Current Issues', false),
('519014863', '773', 'Bobcat G Series', 3, 0, 3, '2024-09-09', 'G Series Bobcat', true, 'No Current Issues', false),
('0008P20287', 'PK-3KBB-NA1C-XLXX', 'SG46 Fertilizer Spreader', 6, 0, 3, '2024-09-09', 'RiderFertilizer Spreader', true, 'No Current Issues', false),
('A6346', 'TL500', 'B26 Kubota Tractor', 4, 0, 3, '2024-09-09', 'Orange Kubota Tractor', true, 'No Current Issues', false),
('4WXBD172061D29961', 'D147', 'DLH Gray Dump Trailer', 7, 0, 3, '2024-09-09', 'DLH Series Gray Dump Trailer', true, 'No Current Issues', false),
('4WXBD1929E1025613', 'DLBC68', 'Black Dump Trailer', 7, 0, 3, '2024-09-09', 'Black Dump Trailer', true, 'No Current Issues', false),
('B3NL19206', 'S595', '2020 Bobcat', 3, 0, 3, '2024-08-23', '2020 Bobcat', true, 'No Current Issues', false),
('B52P34004', 'MT100', 'Bobcat Track Loader', 5, 0, 3, '2024-08-23', '2023 Bobcat Track Loader', true, 'No Current Issues', false),
('200000633', '22303', 'Diesel Dingo', 5, 0, 3, '2024-09-10', 'Diesel Dingo', true, 'No Current Issues', false),
('DINGO1', '22318', 'Gas Dingo', 5, 0, 3, '2024-09-10', 'Gas Dingo', true, 'No Current Issues', false),

('526724760', 'FS94 R', 'Mowing Stihl Trimmer 1', 8, 0, 3, '2024-09-10', 'Lighter trimmer with speed feed head, primarily used for trimming grass, currently marked Number 1', true, 'No Current Issues', false),
('532733886', 'FS94 R', 'Mowing Stihl Trimmer 2', 8, 0, 3, '2024-09-10', 'Lighter trimmer with speed feed head, primarily used for trimming grass, currently marked Number 2', true, 'No Current Issues', false),
('532733862', 'FS94 R', 'Mowing Stihl Trimmer 3', 8, 0, 3, '2024-09-10', 'Lighter trimmer with speed feed head, primarily used for trimming grass, currently marked Number 3', true, 'No Current Issues', false),
('526724749', 'FS94 R', 'Mowing Stihl Trimmer 4', 8, 0, 3, '2024-09-10', 'Lighter trimmer without speed feed head, primarily used for trimming grass, currently marked Number 4', true, 'No Current Issues', false),
('526771201', 'FS94 R', 'Mowing Stihl Trimmer 5', 8, 0, 3, '2024-09-10', 'Lighter trimmer with speed feed head, primarily used for trimming grass, currently marked Number 5', true, 'No Current Issues', false),
('StringTrimmer6', 'FS90 R', 'Hardscape Stihl Trimmer 6', 8, 0, 3, '2024-09-10', 'Heavier trimmer without speed feed head, primarily used for trimming thicker weeds, currently marked Number 6', true, 'No Current Issues', false),
('StringTrimmer7', 'FS90 R', 'Hardscape Stihl Trimmer 7', 8, 0, 3, '2024-09-10', 'Heavier trimmer without speed feed head, primarily used for trimming thicker weeds, currently marked Number 7', true, 'No Current Issues', false),
('510651380', 'FS91 R', 'Hardscape Stihl Trimmer 8', 8, 0, 3, '2024-09-10', 'Heavier trimmer without speed feed head, primarily used for trimming thicker weeds, currently marked Number 8', true, 'No Current Issues', false),
('521972954', 'FS91 R', 'Hardscape Stihl Trimmer 9', 8, 0, 3, '2024-09-10', 'Heavier trimmer without speed feed head, primarily used for trimming thicker weeds, currently marked Number 9', true, 'No Current Issues', false),
('291083307', 'HL90K', 'Hedge Trimmer 8', 9, 0, 3, '2024-09-10', 'Gas powered Hedge Trimmer', true, 'No Current Issues', false),
('530458396', 'HL91K', 'Hedge Trimmer 1', 9, 0, 3, '2024-09-10', 'Gas powered Hedge Trimmer', true, 'No Current Issues', false),
('529657961', 'HL91K', 'Hedge Trimmer 2', 9, 0, 3, '2024-09-10', 'Gas powered Hedge Trimmer', true, 'No Current Issues', false),
('185684942', 'H582T', 'Hedge Trimmer 3', 9, 0, 3, '2024-09-10', 'Gas powered Hedge Trimmer', true, 'No Current Issues', false),
('186515995', 'H582T', 'Hedge Trimmer 4', 9, 0, 3, '2024-09-10', 'Gas powered Hedge Trimmer', true, 'No Current Issues', false),
('190983669', 'H582T', 'Hedge Trimmer 5', 9, 0, 3, '2024-09-10', 'Gas powered Hedge Trimmer', true, 'No Current Issues', false),
('191774162', 'H582T', 'Hedge Trimmer 6', 9, 0, 3, '2024-09-10', 'Gas powered Hedge Trimmer', true, 'No Current Issues', false),
('191774162', 'H582T', 'Hedge Trimmer 7', 9, 0, 3, '2024-09-10', 'Gas powered Hedge Trimmer', true, 'No Current Issues', false),
('406881286', 'ECS180CKA30000', 'Exmark Walk-Behind 1', 10, 0, 3, '2024-08-23', 'Exmark Commercial 30" X-Series Walk-Behind Mower Kawasaki', true, 'No Current Issues', false),
('402265468', '22210', 'Toro Walk-Behind 2', 10, 0, 3, '2024-08-23', 'Toro TurfMaster 30″ Walk Behind', true, 'No Current Issues', false),
('MAGA-2675049', 'HRX217K5VKAA', 'Honda Self Propel 3', 10, 0, 3, '2024-08-23', 'Honda 21 Self Propel″ ', true, 'No Current Issues', false),
('MAMA-1451435', 'HRX217K6VKAA', 'Honda Self Propel 4', 10, 0, 3, '2024-08-23', 'Honda 21 Self Propel', true, 'No Current Issues', false),
('528915163', 'MS 271 FARM BOSS', 'Stihl Chainsaw 1', 11, 0, 3, '2024-09-10', 'Gas powered Chainsaw', true, 'No Current Issues', false),
('538542598', 'MS 271 FARM BOSS', 'Stihl Chainsaw 2', 11, 0, 3, '2024-09-10', 'Gas powered Chainsaw', true, 'No Current Issues', false),
('DEMOSAW1', 'TS400', 'Stihl Demosaw 1', 12, 0, 3, '2024-09-10', '14″ CUT SAW HANDHELD QUICKY 2-CYCLE', true, 'No Current Issues', false),
('DEMOSAW2', 'TS420', 'Stihl Demosaw 2', 12, 0, 3, '2024-09-10', 'STIHL TS 420 Cutquik 14 in. Gas Cut-Off Saw', true, 'No Current Issues', false);


INSERT INTO maintenance_tickets (equipment_id, hours, entered_by_user_id, date, notes, is_complete, is_archived)
VALUES (1, 0, 3, '2024-07-26', 'Default Note', false, false);

INSERT INTO maintenance_performed (equipment_id, ticket_id, description, performed_by, performed_on_date, notes, hours)
VALUES (1, 1, 'Default Description', 'Default Performed By', '2024-08-27', 'Default Notes', 0);

INSERT INTO time_cards (user_id, hour_type, date_time, is_archived)
VALUES (1, 'shift_begin', '2024-09-10 09:05:00', false);

COMMIT TRANSACTION;
