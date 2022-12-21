ALTER TABLE IF EXISTS employee ADD CONSTRAINT FK_main_employee_to_department
    FOREIGN KEY (department)
    REFERENCES department (id);