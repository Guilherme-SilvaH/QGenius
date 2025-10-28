CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    plan VARCHAR(50) NOT NULL DEFAULT 'FREE',
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

-- Índices para performance
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_plan ON users(plan);

-- ==========================
-- Tabela themes (temas padrão do sistema)
-- ==========================
CREATE TABLE themes (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code VARCHAR(100) NOT NULL UNIQUE,
    descricao VARCHAR(255) NOT NULL
);

-- Índice para busca por código
CREATE INDEX idx_themes_code ON themes(code);

-- Inserir temas padrão
INSERT INTO themes (code, descricao) VALUES
('MATEMATICA', 'Matemática'),
('FISICA', 'Física'),
('QUIMICA', 'Química'),
('BIOLOGIA', 'Biologia'),
('HISTORIA', 'História'),
('GEOGRAFIA', 'Geografia'),
('PORTUGUES', 'Português'),
('LITERATURA', 'Literatura'),
('FILOSOFIA', 'Filosofia'),
('SOCIOLOGIA', 'Sociologia'),
('INFORMATICA', 'Informática'),
('PROGRAMACAO', 'Programação'),
('INGLES', 'Inglês'),
('ATUALIDADES', 'Atualidades'),
('ARTE', 'Arte'),
('EDUCACAO_FISICA', 'Educação Física'),
('ECONOMIA', 'Economia'),
('DIREITO', 'Direito'),
('PSICOLOGIA', 'Psicologia'),
('MEDICINA', 'Medicina'),
('ENGENHARIA', 'Engenharia'),
('ASTRONOMIA', 'Astronomia'),
('MEIO_AMBIENTE', 'Meio Ambiente'),
('TECNOLOGIA', 'Tecnologia'),
('INTELIGENCIA_ARTIFICIAL', 'Inteligência Artificial');

-- ==========================
-- Tabela generations (gerações de questões)
-- ==========================
CREATE TABLE generations (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    theme VARCHAR(255) NOT NULL,
    difficulty VARCHAR(50),
    type VARCHAR(50),
    quantity INT,
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
    questions JSONB,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_generations_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

-- Índices para performance
CREATE INDEX idx_generations_user_id ON generations(user_id);
CREATE INDEX idx_generations_status ON generations(status);
CREATE INDEX idx_generations_created_at ON generations(created_at DESC);
