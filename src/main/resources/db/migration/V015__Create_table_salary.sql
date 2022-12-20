CREATE TABLE IF NOT EXISTS salary (
    id UUID PRIMARY KEY,
    "position" VARCHAR(30) NOT NULL,
    salary_range VARCHAR(10) NOT NULL,
    gross_salary NUMERIC(10,2) NOT NULL,
    net_salary NUMERIC(10,2),
    INSS NUMERIC(10,2) NOT NULL,
    FGTS NUMERIC(10,2) NOT NULL,
    IRRF NUMERIC(10,2),
    salary_advance NUMERIC(10,2),
    benefits VARCHAR(100),
    UNIQUE (id, "position")
);