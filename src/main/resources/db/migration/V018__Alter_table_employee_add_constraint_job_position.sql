ALTER TABLE IF EXISTS employee ADD CONSTRAINT FK_main_employee_to_job_position
    FOREIGN KEY (job_position)
    REFERENCES job_position (id);