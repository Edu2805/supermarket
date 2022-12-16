CREATE TABLE IF NOT EXISTS establishment (
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    cnpj VARCHAR(14) NOT NULL,
    code INTEGER NOT NULL,
    state_registration VARCHAR(20) NOT NULL,
    municipal_registration VARCHAR(20),
    address VARCHAR(60) NOT NULL,
    phone VARCHAR(11) NOT NULL,
    manager VARCHAR(60) NOT NULL,
    UNIQUE (id, cnpj, code, state_registration, municipal_registration, phone, manager)
);