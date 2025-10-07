
CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    plan VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE generations (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    theme VARCHAR(255) NOT NULL,
    difficulty VARCHAR(50),
    type VARCHAR(50),
    quantity INT,
    status VARCHAR(50) NOT NULL,
    questions JSONB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_user
        FOREIGN KEY(user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_users_email ON users(email);

CREATE INDEX idx_generations_user_id ON generations(user_id);