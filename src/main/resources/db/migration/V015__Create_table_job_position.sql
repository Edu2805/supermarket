CREATE TABLE IF NOT EXISTS job_position (
    id UUID PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    code INTEGER NOT NULL,
    assignments VARCHAR(100),
    salary UUID NOT NULL,
    FOREIGN KEY (salary) REFERENCES salary (id),
    UNIQUE (id, name, code)
);
