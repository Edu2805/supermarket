/* Logico_1_supermercado: */

CREATE TABLE IF NOT EXISTS product_data (
    id UUID PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    unity INTEGER NOT NULL,
    purchase_price NUMERIC(10,2) NOT NULL,
    sale_price NUMERIC(10,2) NOT NULL,
    margin NUMERIC(10,2),
    provider VARCHAR(60) NOT NULL,
    EAN_13 VARCHAR(13),
    DUN_14 VARCHAR(14),
    internal_code INTEGER NOT NULL,
    inventory INTEGER NOT NULL,
    subsection_id UUID NOT NULL,
    UNIQUE (id, name, EAN_13, DUN_14, internal_code)
);

CREATE TABLE IF NOT EXISTS subsection (
    id UUID PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    code INTEGER NOT NULL,
    UNIQUE (id, code, name)
);

ALTER TABLE IF EXISTS product_data ADD CONSTRAINT FK_product_data_3
    FOREIGN KEY (subsection_id)
    REFERENCES subsection (id);
