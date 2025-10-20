-- ==========================
-- Tabela users
-- ==========================
CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    plan VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

-- ==========================
-- Tabela generations
-- ==========================
CREATE TABLE generations (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id),
    theme VARCHAR(255) NOT NULL,
    difficulty VARCHAR(50),
    type VARCHAR(50),
    quantity INT,
    status VARCHAR(50) NOT NULL,
    questions JSONB,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

-- ==========================
-- Tabela questions
-- ==========================
CREATE TABLE questions (
    id UUID PRIMARY KEY,
    question VARCHAR(500) NOT NULL,
    context TEXT,
    theme VARCHAR(100) NOT NULL,
    custom_theme VARCHAR(255),
    user_id UUID NOT NULL REFERENCES users(id),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

-- ==========================
-- Tabela themes_customs
-- ==========================
CREATE TABLE themes_customs (
    id UUID PRIMARY KEY,
    custom_themes VARCHAR(255)
);
