ALTER TABLE IF EXISTS employee ADD CONSTRAINT FK_main_employee_to_user_data
    FOREIGN KEY (user_data)
    REFERENCES user_data (id);