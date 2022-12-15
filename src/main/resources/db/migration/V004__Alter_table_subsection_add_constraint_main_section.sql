ALTER table IF EXISTS subsection ADD CONSTRAINT FK_subsection_to_main_section
    FOREIGN KEY (main_section)
    REFERENCES main_section (id);