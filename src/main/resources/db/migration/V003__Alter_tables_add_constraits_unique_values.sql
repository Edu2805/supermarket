ALTER TABLE IF EXISTS department ADD CONSTRAINT unique_name_department UNIQUE (name);
ALTER TABLE IF EXISTS department ADD CONSTRAINT unique_code_department UNIQUE (code);
ALTER TABLE IF EXISTS employee ADD CONSTRAINT unique_register_number_employee UNIQUE (register_number);
ALTER TABLE IF EXISTS employee ADD CONSTRAINT unique_person_employee UNIQUE (person);
ALTER TABLE IF EXISTS person ADD CONSTRAINT unique_cpf_employee UNIQUE (cpf);
ALTER TABLE IF EXISTS person ADD CONSTRAINT unique_rg_employee UNIQUE (rg);
ALTER TABLE IF EXISTS person ADD CONSTRAINT unique_user_data_employee UNIQUE (user_data);
ALTER TABLE IF EXISTS establishment ADD CONSTRAINT unique_code_establishment UNIQUE (code);
ALTER TABLE IF EXISTS establishment ADD CONSTRAINT unique_cnpj_establishment UNIQUE (cnpj);
ALTER TABLE IF EXISTS establishment ADD CONSTRAINT unique_state_registration_establishment UNIQUE (state_registration);
ALTER TABLE IF EXISTS establishment ADD CONSTRAINT unique_municipal_registration_establishment UNIQUE (municipal_registration);
ALTER TABLE IF EXISTS job_position ADD CONSTRAINT unique_name_job_position UNIQUE (name);
ALTER TABLE IF EXISTS job_position ADD CONSTRAINT unique_code_job_position UNIQUE (code);
ALTER TABLE IF EXISTS main_section ADD CONSTRAINT unique_name_mainsection UNIQUE (name);
ALTER TABLE IF EXISTS main_section ADD CONSTRAINT unique_code_mainsection UNIQUE (code);
ALTER TABLE IF EXISTS product_data ADD CONSTRAINT unique_ean13_product_data UNIQUE (ean_13);
ALTER TABLE IF EXISTS product_data ADD CONSTRAINT unique_dun14_product_data UNIQUE (dun_14);
ALTER TABLE IF EXISTS product_data ADD CONSTRAINT unique_code_product_data UNIQUE (code);
ALTER TABLE IF EXISTS subsection ADD CONSTRAINT unique_name_subsection UNIQUE (name);
ALTER TABLE IF EXISTS subsection ADD CONSTRAINT unique_code_subsection UNIQUE (code);
ALTER TABLE IF EXISTS user_data ADD CONSTRAINT unique_columns_user_data UNIQUE (user_name);
ALTER TABLE IF EXISTS provider ADD CONSTRAINT unique_code_provider UNIQUE (code);
ALTER TABLE IF EXISTS provider ADD CONSTRAINT unique_subscription_number_provider UNIQUE (subscription_number);
ALTER TABLE IF EXISTS provider ADD CONSTRAINT unique_state_registration_provider UNIQUE (state_registration);
ALTER TABLE IF EXISTS provider ADD CONSTRAINT unique_municipal_registration_provider UNIQUE (municipal_registration);