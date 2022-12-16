ALTER table IF EXISTS department ADD CONSTRAINT FK_main_department_to_establishment
    FOREIGN KEY (establishment)
    REFERENCES establishment (id);