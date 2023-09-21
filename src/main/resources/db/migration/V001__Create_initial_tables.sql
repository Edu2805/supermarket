/* Logico_1_supermercado: */

CREATE TABLE IF NOT EXISTS product_data (
   id UUID NOT NULL,
   name VARCHAR(50) NOT NULL,
   code DECIMAL NOT NULL,
   unity INTEGER NOT NULL,
   purchase_price DECIMAL(10, 2) NOT NULL,
   sale_price DECIMAL(10, 2) NOT NULL,
   margin DECIMAL(10, 4),
   ean_13 VARCHAR(13),
   dun_14 VARCHAR(14),
   inventory DECIMAL NOT NULL,
   subsection_id UUID,
   provider_product UUID,
   CONSTRAINT pk_product_data PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS subsection (
   id UUID NOT NULL,
   name VARCHAR(50) NOT NULL,
   code DECIMAL NOT NULL,
   main_section UUID,
   CONSTRAINT pk_subsection PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS main_section (
   id UUID NOT NULL,
   name VARCHAR(50) NOT NULL,
   code DECIMAL NOT NULL,
   department UUID,
   CONSTRAINT pk_main_section PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS department (
   id UUID NOT NULL,
   name VARCHAR(50) NOT NULL,
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
   register_number DECIMAL NOT NULL,
   full_name VARCHAR(100) NOT NULL,
   person UUID,
   sub_section UUID,
   job_position UUID,
   CONSTRAINT pk_employee PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS salary (
   id UUID NOT NULL,
   position VARCHAR(30) NOT NULL,
   salary_range VARCHAR(10) NOT NULL,
   gross_salary DECIMAL(10, 2) NOT NULL,
   net_salary DECIMAL(10, 2) NOT NULL,
   inss DECIMAL(10, 2),
   fgts DECIMAL(10, 2),
   irrf DECIMAL(10, 2),
   salary_advance DECIMAL(10, 2),
   benefits VARCHAR(100),
   competence_start date NOT NULL,
   final_competence date NOT NULL,
   CONSTRAINT pk_salary PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS other_discount (
   id UUID NOT NULL,
   discount_name VARCHAR(100) NOT NULL,
   discount_value DECIMAL(10, 2) NOT NULL,
   salary_id UUID,
   CONSTRAINT pk_other_discount PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS other_addition (
  id UUID NOT NULL,
   addition_name VARCHAR(100) NOT NULL,
   addition_value DECIMAL(10, 2) NOT NULL,
   salary_id UUID,
   CONSTRAINT pk_other_addition PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS job_position (
   id UUID NOT NULL,
   name VARCHAR(50) NOT NULL,
   code DECIMAL NOT NULL,
   assignments VARCHAR(100),
   salary UUID,
   CONSTRAINT pk_job_position PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_data (
   id UUID NOT NULL,
   user_name VARCHAR(50) NOT NULL,
   password VARCHAR(100) NOT NULL,
   role VARCHAR(50) NOT NULL,
   registration_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   is_employee BOOLEAN NOT NULL,
   CONSTRAINT pk_user_data PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS person (
   id UUID NOT NULL,
   first_name VARCHAR(30) NOT NULL,
   middle_name VARCHAR(30),
   last_name VARCHAR(30) NOT NULL,
   cpf VARCHAR(11) NOT NULL,
   rg VARCHAR(15),
   nationality VARCHAR(20),
   naturalness VARCHAR(20),
   birth_date date NOT NULL,
   scholarity INTEGER,
   dependents BOOLEAN,
   father_name VARCHAR(50),
   mother_name VARCHAR(50) NOT NULL,
   email VARCHAR(50) NOT NULL,
   user_data UUID,
   CONSTRAINT pk_person PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS provider (
   id UUID NOT NULL,
   name VARCHAR(50) NOT NULL,
   code DECIMAL NOT NULL,
   subscription_type INTEGER NOT NULL,
   subscription_number VARCHAR(50) NOT NULL,
   state_registration VARCHAR(20) NOT NULL,
   municipal_registration VARCHAR(20),
   address VARCHAR(60) NOT NULL,
   phone VARCHAR(11) NOT NULL,
   responsible VARCHAR(60) NOT NULL,
   CONSTRAINT pk_provider PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS goods_issue (
   id UUID NOT NULL,
   sale_number DECIMAL NOT NULL,
   products_total DECIMAL(10, 2) NOT NULL,
   subtotal DECIMAL(10, 2) NOT NULL,
   total_received DECIMAL(10, 2) NOT NULL,
   change DECIMAL(10, 2) NOT NULL,
   is_effective_sale BOOLEAN NOT NULL,
   payment_options_type VARCHAR NOT NULL,
   registration_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   CONSTRAINT pk_goods_issue PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS product_issue_list (
   goods_issue_id UUID NOT NULL,
   product_list VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS goods_receipt (
   id UUID NOT NULL,
   control_number DECIMAL NOT NULL,
   invoice VARCHAR(50) NOT NULL,
   products_total DECIMAL(10, 2) NOT NULL,
   registration_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   is_received BOOLEAN NOT NULL,
   CONSTRAINT pk_goods_receipt PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS goods_receipt_to_product_data (
   goods_receipt_id UUID NOT NULL,
   product_data_id UUID NOT NULL
);

CREATE TABLE IF NOT EXISTS product_receipt_list (
   goods_receipt_id UUID NOT NULL,
   produc_receipt_list VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS goods_issue_to_product_data (
   goods_issue_id UUID NOT NULL,
   product_data_id UUID NOT NULL
);

CREATE TABLE IF NOT EXISTS historical_goods_receipt (
   id UUID NOT NULL,
   name VARCHAR(50) NOT NULL,
   product_code DECIMAL,
   purchase_price DECIMAL,
   inventory DECIMAL,
   provider_product_name VARCHAR(255),
   department_name VARCHAR(255),
   mainsection_name VARCHAR(255),
   subsection_name VARCHAR(255),
   invoice VARCHAR(255),
   total_invoice DECIMAL,
   registration_date TIMESTAMP WITHOUT TIME ZONE,
   is_received BOOLEAN NOT NULL,
   CONSTRAINT pk_historical_goods_receipt PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS historical_goods_issue (
   id UUID NOT NULL,
   name VARCHAR(50) NOT NULL,
   product_code DECIMAL,
   ean13 VARCHAR(255),
   dun14 VARCHAR(255),
   sale_price DECIMAL,
   inventory DECIMAL,
   provider_product_name VARCHAR(255),
   department_name VARCHAR(255),
   mainsection_name VARCHAR(255),
   subsection_name VARCHAR(255),
   sale_number DECIMAL,
   products_total DECIMAL,
   is_effective_sale BOOLEAN NOT NULL,
   registration_date TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_historical_goods_issue PRIMARY KEY (id)
);
