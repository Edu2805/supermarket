ALTER TABLE IF EXISTS department ADD CONSTRAINT foreign_key_establishment FOREIGN KEY (establishment) REFERENCES establishment (id);
ALTER TABLE IF EXISTS employee ADD CONSTRAINT foreign_key_employee_to_person FOREIGN KEY (person) REFERENCES person (id);
ALTER TABLE IF EXISTS employee ADD CONSTRAINT foreign_key_employee_to_department FOREIGN KEY (department) REFERENCES department (id);
ALTER TABLE IF EXISTS employee ADD CONSTRAINT foreign_key_employee_to_job_position FOREIGN KEY (job_position) REFERENCES job_position (id);
ALTER TABLE IF EXISTS person ADD CONSTRAINT foreign_key_person_to_user_data FOREIGN KEY (user_data) REFERENCES user_data (id);
ALTER TABLE IF EXISTS main_section ADD CONSTRAINT foreign_key_department_to_main_section FOREIGN KEY (department) REFERENCES department (id);
ALTER TABLE IF EXISTS product_data ADD CONSTRAINT foreign_key_subsection_to_product_data FOREIGN KEY (subsection_id) REFERENCES subsection (id);
ALTER TABLE IF EXISTS subsection ADD CONSTRAINT foreign_key_main_section_to_sub_section FOREIGN KEY (main_section) REFERENCES main_section (id);
