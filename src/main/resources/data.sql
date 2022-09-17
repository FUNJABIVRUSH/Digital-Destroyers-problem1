INSERT INTO building (id, name) VALUES (1, 'EON2');

INSERT INTO floor (id, name, building_id) VALUES (1, '1', 1);
INSERT INTO floor (id, name, building_id) VALUES (2, '2', 1);

INSERT INTO "ZONE"(id, name, floor_id) VALUES (1, 'A', 1);
INSERT INTO "ZONE"(id, name, floor_id) VALUES (2, 'B', 1);
INSERT INTO "ZONE"(id, name, floor_id) VALUES (3, 'A', 2);
INSERT INTO "ZONE"(id, name, floor_id) VALUES (4, 'B', 2);

INSERT INTO seat(id, number, zone_id, type, is_reserved) VALUES (1, '1', 1, 'WINDOW', 0);
INSERT INTO seat(id, number, zone_id, type, is_reserved) VALUES (2, '2', 1, 'NON_WINDOW', 0);
INSERT INTO seat(id, number, zone_id, type, is_reserved) VALUES (3, '1', 2, 'WINDOW',0);
INSERT INTO seat(id, number, zone_id, type, is_reserved) VALUES (4, '2', 2, 'NON_WINDOW',0);
INSERT INTO seat(id, number, zone_id, type, is_reserved) VALUES (5, '1', 3, 'WINDOW',0);
INSERT INTO seat(id, number, zone_id, type, is_reserved) VALUES (6, '2', 3, 'NON_WINDOW',0);
INSERT INTO seat(id, number, zone_id, type, is_reserved) VALUES (7, '1', 4, 'WINDOW',0);
INSERT INTO seat(id, number, zone_id, type, is_reserved) VALUES (8, '2', 4, 'NON_WINDOW',0);


INSERT INTO department (id, name, building_id) VALUES (1, 'Private Banking', 1);

INSERT INTO oe_code (id, name, total_employees, department_id, type) VALUES (1, 'MBLD1', 100, 1, 'HIGH');
INSERT INTO oe_code (id, name, total_employees, department_id, type) VALUES (2, 'MBLD11', 100, 1, 'MID');
INSERT INTO oe_code (id, name, total_employees, department_id, type) VALUES (3, 'MBLD111', 100, 1, 'LOW');

INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (1, 'M12345', 'User-1', 'ADMIN', 1, 1);
INSERT INTO department_admin (id, employee_id, department_id) VALUES (1, 1, 1);



