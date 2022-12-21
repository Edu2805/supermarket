ALTER TABLE IF EXISTS job_position ADD CONSTRAINT FK_main_job_position_to_salary
    FOREIGN KEY (salary)
    REFERENCES salary (id);