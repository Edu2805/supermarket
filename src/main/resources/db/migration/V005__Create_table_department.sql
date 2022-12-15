CREATE TABLE IF NOT EXISTS department (
    id UUID PRIMARY KEY,
    name VARCHAR NOT NULL,
    code INTEGER NOT NULL,
    UNIQUE (id, name, code)
);