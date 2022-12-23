/* Logico_1_supermercado: */

CREATE TABLE IF NOT EXISTS product_data (
   id UUID NOT NULL,
   name VARCHAR(30) NOT NULL,
   unity INTEGER NOT NULL,
   purchase_price DECIMAL(10, 2) NOT NULL,
   sale_price DECIMAL(10, 2) NOT NULL,
   margin DECIMAL(10, 2),
   provider VARCHAR(60) NOT NULL,
   ean_13 VARCHAR(13),
   dun_14 VARCHAR(14),
   internal_code DECIMAL NOT NULL,
   inventory DECIMAL NOT NULL,
   subsection_id UUID,
   CONSTRAINT pk_product_data PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS subsection (
   id UUID NOT NULL,
   name VARCHAR(30) NOT NULL,
   code DECIMAL NOT NULL,
   main_section UUID,
   CONSTRAINT pk_subsection PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS main_section (
  id UUID NOT NULL,
   name VARCHAR(30) NOT NULL,
   code DECIMAL NOT NULL,
   department UUID,
   CONSTRAINT pk_main_section PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS department (
  id UUID NOT NULL,
   name VARCHAR(30) NOT NULL,
   code DECIMAL NOT NULL,
   establishment UUID,
   CONSTRAINT pk_department PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS establishment (
  id UUID NOT NULL,
   name VARCHAR(50) NOT NULL,
   code DECIMAL NOT NULL,
   cnpj VARCHAR(14) NOT NULL,
   state_registration VARCHAR(20) NOT NULL,
   municipal_registration VARCHAR(20),
   address VARCHAR(60) NOT NULL,
   phone VARCHAR(11) NOT NULL,
   manager VARCHAR(60) NOT NULL,
   CONSTRAINT pk_establishment PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS employee (
   id UUID NOT NULL,
   first_name VARCHAR(30) NOT NULL,
   middle_name VARCHAR(30),
   last_name VARCHAR(30) NOT NULL,
   register_number DECIMAL NOT NULL,
   cpf VARCHAR(11) NOT NULL,
   rg VARCHAR(15),
   nationality VARCHAR(20) NOT NULL,
   naturalness VARCHAR(20) NOT NULL,
   birth_date date NOT NULL,
   scholarity INTEGER NOT NULL,
   dependents BOOLEAN NOT NULL,
   father_name VARCHAR(50),
   mother_name VARCHAR(50) NOT NULL,
   department UUID,
   job_position UUID,
   user_data UUID,
   CONSTRAINT pk_employee PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS salary (
   id UUID NOT NULL,
   position VARCHAR(30) NOT NULL,
   salary_range VARCHAR(10) NOT NULL,
   gross_salary DECIMAL(10, 2) NOT NULL,
   net_salary DECIMAL(10, 2) NOT NULL,
   inss DECIMAL(10, 2) NOT NULL,
   fgts DECIMAL(10, 2) NOT NULL,
   irrf DECIMAL(10, 2) NOT NULL,
   salary_advance DECIMAL(10, 2) NOT NULL,
   benefits VARCHAR(100) NOT NULL,
   CONSTRAINT pk_salary PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS job_position (
   id UUID NOT NULL,
   name VARCHAR(30) NOT NULL,
   code DECIMAL NOT NULL,
   assignments VARCHAR(100),
   salary UUID,
   CONSTRAINT pk_job_position PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_data (
   id UUID NOT NULL,
   user_name VARCHAR(10) NOT NULL,
   password VARCHAR(8) NOT NULL,
   role INTEGER NOT NULL,
   registration_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   CONSTRAINT pk_user_data PRIMARY KEY (id)
);