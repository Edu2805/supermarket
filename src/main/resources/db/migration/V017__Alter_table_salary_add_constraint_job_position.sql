ALTER TABLE IF EXISTS salary ADD CONSTRAINT FK_main_salary_to_job_position
    FOREIGN KEY (job_position)
    REFERENCES job_position (id);