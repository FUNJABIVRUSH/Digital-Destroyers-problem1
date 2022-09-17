INSERT INTO building (id, name) VALUES (1, 'EON2');
INSERT INTO department (id, name, building_id) VALUES (1, 'Private Banking', 1);
INSERT INTO employee (id, mpid, name, role, department_id) VALUES (1, 'M12345', 'User-1', 'ADMIN', 1);
INSERT INTO department_admin (id, employee_id, department_id) VALUES (1, 1, 1);

INSERT INTO oe_code (id, name, total_employees, department_id, type) VALUES (1, 'MBLD1', 100, 1, 'HIGH');
INSERT INTO oe_code (id, name, total_employees, department_id, type) VALUES (2, 'MBLD11', 100, 1, 'MID');
INSERT INTO oe_code (id, name, total_employees, department_id, type) VALUES (3, 'MBLD111', 100, 1, 'LOW');

