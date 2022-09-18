INSERT INTO building (id, name) VALUES (1, 'EON2');

INSERT INTO floor (id, name, building_id) VALUES (1, '1', 1);
INSERT INTO floor (id, name, building_id) VALUES (2, '2', 1);
INSERT INTO floor (id, name, building_id) VALUES (3, '3', 1);

INSERT INTO "ZONE"(id, name, floor_id) VALUES (1, 'A', 1);
INSERT INTO "ZONE"(id, name, floor_id) VALUES (2, 'B', 1);
INSERT INTO "ZONE"(id, name, floor_id) VALUES (3, 'A', 2);
INSERT INTO "ZONE"(id, name, floor_id) VALUES (4, 'B', 2);
INSERT INTO "ZONE"(id, name, floor_id) VALUES (5, 'A', 3);
INSERT INTO "ZONE"(id, name, floor_id) VALUES (6, 'B', 3);

INSERT INTO seat(id, number, zone_id, type) VALUES (1, '1', 1, 'WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (2, '2', 1, 'WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (3, '3', 1, 'WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (4, '4', 1, 'NON_WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (5, '5', 1, 'NON_WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (6, '1', 2, 'WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (7, '2', 2, 'WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (8, '3', 2, 'WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (9, '4', 2, 'NON_WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (10, '5', 2, 'NON_WINDOW');

INSERT INTO seat(id, number, zone_id, type) VALUES (11, '1', 3, 'WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (12, '2', 3, 'WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (13, '3', 3, 'WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (14, '4', 3, 'NON_WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (15, '5', 3, 'NON_WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (16, '1', 4, 'WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (17, '2', 4, 'WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (18, '3', 4, 'WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (19, '4', 4, 'NON_WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (20, '5', 4, 'NON_WINDOW');

INSERT INTO seat(id, number, zone_id, type) VALUES (21, '1', 5, 'WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (22, '2', 5, 'WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (23, '3', 5, 'WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (24, '4', 5, 'NON_WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (25, '5', 5, 'NON_WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (26, '1', 6, 'WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (27, '2', 6, 'WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (28, '3', 6, 'WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (29, '4', 6, 'NON_WINDOW');
INSERT INTO seat(id, number, zone_id, type) VALUES (30, '5', 6, 'NON_WINDOW');


INSERT INTO department (id, name, building_id) VALUES (1, 'Private Banking', 1);
INSERT INTO department (id, name, building_id) VALUES (2, 'Investment Banking', 1);

INSERT INTO oe_code (id, name, total_employees, department_id, type) VALUES (1, 'MBLD1', 38, 1, 'HIGH');
INSERT INTO oe_code (id, name, total_employees, department_id, type, parent_oe_code_id) VALUES (2, 'MBLD11', 14, 1, 'MID', 1);
INSERT INTO oe_code (id, name, total_employees, department_id, type, parent_oe_code_id) VALUES (3, 'MBLD111', 6, 1, 'MID', 2);
INSERT INTO oe_code (id, name, total_employees, department_id, type, parent_oe_code_id) VALUES (4, 'MBLD1111', 5, 1, 'LOW', 3);
INSERT INTO oe_code (id, name, total_employees, department_id, type, parent_oe_code_id) VALUES (5, 'MBLD112', 6, 1, 'MID', 2);
INSERT INTO oe_code (id, name, total_employees, department_id, type, parent_oe_code_id) VALUES (6, 'MBLD1121', 5, 1, 'LOW', 2);

INSERT INTO oe_code (id, name, total_employees, department_id, type, parent_oe_code_id) VALUES (7, 'MBLD12', 23, 1, 'MID', 1);
INSERT INTO oe_code (id, name, total_employees, department_id, type, parent_oe_code_id) VALUES (8, 'MBLD121', 6, 1, 'MID', 7);
INSERT INTO oe_code (id, name, total_employees, department_id, type, parent_oe_code_id) VALUES (9, 'MBLD1211', 5, 1, 'LOW', 8);
INSERT INTO oe_code (id, name, total_employees, department_id, type, parent_oe_code_id) VALUES (10, 'MBLD122', 8, 1, 'MID', 7);
INSERT INTO oe_code (id, name, total_employees, department_id, type, parent_oe_code_id) VALUES (11, 'MBLD1221', 7, 1, 'LOW', 10);
INSERT INTO oe_code (id, name, total_employees, department_id, type, parent_oe_code_id) VALUES (12, 'MBLD123', 8, 1, 'MID', 7);
INSERT INTO oe_code (id, name, total_employees, department_id, type, parent_oe_code_id) VALUES (13, 'MBLD1231', 7, 1, 'LOW', 12);

INSERT INTO oe_code (id, name, total_employees, department_id, type) VALUES (14, 'IBLD1', 12, 1, 'HIGH');
INSERT INTO oe_code (id, name, total_employees, department_id, type, parent_oe_code_id) VALUES (15, 'IBLD11', 5, 1, 'MID', 14);
INSERT INTO oe_code (id, name, total_employees, department_id, type, parent_oe_code_id) VALUES (16, 'IBLD111', 4, 1, 'LOW', 15);
INSERT INTO oe_code (id, name, total_employees, department_id, type, parent_oe_code_id) VALUES (17, 'IBLD12', 6, 1, 'MID', 14);
INSERT INTO oe_code (id, name, total_employees, department_id, type, parent_oe_code_id) VALUES (18, 'IBLD121', 5, 1, 'LOW', 17);

INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (1, 'M12345', 'User-11', 'ADMIN', 1, 1);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (2, 'M12346', 'User-1', 'HIGH_LEVEL_MANAGER', 1, 1);

INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (3, 'M12347', 'User-11', 'MID_LEVEL_MANAGER', 1, 2);

INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (4, 'M12348', 'User-111', 'LOW_LEVEL_MANAGER', 1, 3);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (5, 'M12349', 'User-1111', 'EMPLOYEE', 1, 4);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (6, 'M12350', 'User-1112', 'EMPLOYEE', 1, 4);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (7, 'M12351', 'User-1113', 'EMPLOYEE', 1, 4);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (8, 'M12352', 'User-1114', 'EMPLOYEE', 1, 4);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (9, 'M12353', 'User-1115', 'EMPLOYEE', 1, 4);

INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (10, 'M12354', 'User-112', 'LOW_LEVEL_MANAGER', 1, 5);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (11, 'M12355', 'User-1121', 'EMPLOYEE', 1, 6);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (12, 'M12356', 'User-1122', 'EMPLOYEE', 1, 6);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (13, 'M12357', 'User-1123', 'EMPLOYEE', 1, 6);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (14, 'M12358', 'User-1124', 'EMPLOYEE', 1, 6);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (15, 'M12359', 'User-1125', 'EMPLOYEE', 1, 6);

INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (16, 'M12360', 'User-12', 'MID_LEVEL_MANAGER', 1, 7);

INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (17, 'M12361', 'User-121', 'LOW_LEVEL_MANAGER', 1, 8);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (18, 'M12362', 'User-1211', 'EMPLOYEE', 1, 9);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (19, 'M12363', 'User-1212', 'EMPLOYEE', 1, 9);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (20, 'M12364', 'User-1213', 'EMPLOYEE', 1, 9);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (21, 'M12365', 'User-1214', 'EMPLOYEE', 1, 9);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (22, 'M12366', 'User-1215', 'EMPLOYEE', 1, 9);

INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (23, 'M12367', 'User-122', 'LOW_LEVEL_MANAGER', 1, 10);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (24, 'M12368', 'User-1221', 'EMPLOYEE', 1, 11);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (25, 'M12369', 'User-1222', 'EMPLOYEE', 1, 11);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (26, 'M12370', 'User-1223', 'EMPLOYEE', 1, 11);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (27, 'M12371', 'User-1224', 'EMPLOYEE', 1, 11);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (28, 'M12372', 'User-1225', 'EMPLOYEE', 1, 11);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (29, 'M12373', 'User-1226', 'EMPLOYEE', 1, 11);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (30, 'M12374', 'User-1227', 'EMPLOYEE', 1, 11);

INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (31, 'M12375', 'User-123', 'LOW_LEVEL_MANAGER', 1, 12);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (32, 'M12376', 'User-1231', 'EMPLOYEE', 1, 13);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (33, 'M12377', 'User-1232', 'EMPLOYEE', 1, 13);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (34, 'M12378', 'User-1233', 'EMPLOYEE', 1, 13);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (35, 'M12379', 'User-1234', 'EMPLOYEE', 1, 13);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (36, 'M12380', 'User-1235', 'EMPLOYEE', 1, 13);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (37, 'M12381', 'User-1236', 'EMPLOYEE', 1, 13);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (38, 'M12382', 'User-1237', 'EMPLOYEE', 1, 13);

INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (39, 'M12383', 'User-2', 'HIGH_LEVEL_MANAGER', 1, 14);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (40, 'M12384', 'User-21', 'LOW_LEVEL_MANAGER', 1, 15);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (41, 'M12385', 'User-211', 'EMPLOYEE', 1, 16);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (42, 'M12386', 'User-212', 'EMPLOYEE', 1, 16);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (43, 'M12387', 'User-213', 'EMPLOYEE', 1, 16);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (44, 'M12388', 'User-214', 'EMPLOYEE', 1, 16);

INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (45, 'M12389', 'User-22', 'LOW_LEVEL_MANAGER', 1, 17);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (46, 'M12390', 'User-221', 'EMPLOYEE', 1, 18);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (47, 'M12391', 'User-222', 'EMPLOYEE', 1, 18);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (48, 'M12392', 'User-223', 'EMPLOYEE', 1, 18);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (49, 'M12393', 'User-224', 'EMPLOYEE', 1, 18);
INSERT INTO employee (id, mpid, name, role, department_id, oe_code_id) VALUES (50, 'M12394', 'User-225', 'EMPLOYEE', 1, 18);




INSERT INTO department_admin (id, employee_id, department_id) VALUES (1, 1, 1);



