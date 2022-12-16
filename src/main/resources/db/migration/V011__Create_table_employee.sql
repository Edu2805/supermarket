CREATE TABLE IF NOT EXISTS employee (
    id UUID PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    middle_name VARCHAR(30),
    last_name VARCHAR(30) NOT NULL,
    register_number INTEGER NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    rg VARCHAR(15),
    nationality VARCHAR(20) NOT NULL,
    naturalness VARCHAR(20) NOT NULL,
    birth_date DATE NOT NULL,
    scholarity INTEGER NOT NULL,
    dependents BOOLEAN NOT NULL,
    father_name VARCHAR(50),
    mother_name VARCHAR(50) NOT NULL,
    UNIQUE (id, cpf)
);
