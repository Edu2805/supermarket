CREATE TABLE IF NOT EXISTS attachment (
  id UUID NOT NULL,
  name VARCHAR(100),
  type VARCHAR(1000),
  image_data bytea,
  CONSTRAINT pk_attachment PRIMARY KEY (id)
);