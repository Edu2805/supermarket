CREATE TABLE IF NOT EXISTS user_data (
    id UUID PRIMARY KEY,
    user_name VARCHAR(10) NOT NULL,
    password VARCHAR(8) NOT NULL,
    role INTEGER NOT NULL,
    UNIQUE (id, user_name)
)