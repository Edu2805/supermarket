CREATE TABLE IF NOT EXISTS job_position (
    id UUID PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    code INTEGER NOT NULL,
    assignments VARCHAR(100),
    employee UUID NOT NULL,
    FOREIGN KEY (employee) REFERENCES employee (id),
    UNIQUE (id, name, code)
);
