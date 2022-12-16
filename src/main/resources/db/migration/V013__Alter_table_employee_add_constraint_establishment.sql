ALTER TABLE IF EXISTS employee ADD CONSTRAINT FK_main_employee_to_establishment
    FOREIGN KEY (establishment)
    REFERENCES establishment (id);