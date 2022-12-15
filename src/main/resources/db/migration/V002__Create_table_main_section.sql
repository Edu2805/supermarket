CREATE TABLE IF NOT EXISTS main_section (
    id UUID PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    code INTEGER NOT NULL,
    UNIQUE (id, name, code)
);
