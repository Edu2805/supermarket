ALTER table IF EXISTS main_section ADD CONSTRAINT FK_main_section_to_department
    FOREIGN KEY (department)
    REFERENCES department (id);