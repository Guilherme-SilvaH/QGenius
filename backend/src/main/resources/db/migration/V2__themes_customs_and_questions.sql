CREATE TABLE themes_customs (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    custom_themes VARCHAR(255) NOT NULL,
    user_id UUID NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_themes_customs_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

-- Índice para buscar temas por usuário
CREATE INDEX idx_themes_customs_user_id ON themes_customs(user_id);

-- ==========================
-- Tabela questions (questões individuais)
-- ==========================
CREATE TABLE questions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    question VARCHAR(500) NOT NULL,
    context TEXT,
    theme_id UUID,
    custom_theme_id UUID,
    user_id UUID NOT NULL,
    generation_id UUID,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_questions_theme
        FOREIGN KEY (theme_id)
        REFERENCES themes(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_questions_custom_theme
        FOREIGN KEY (custom_theme_id)
        REFERENCES themes_customs(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_questions_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_questions_generation
        FOREIGN KEY (generation_id)
        REFERENCES generations(id)
        ON DELETE SET NULL,

    -- Garantir que pelo menos um tema seja definido
    CONSTRAINT check_has_theme
        CHECK (theme_id IS NOT NULL OR custom_theme_id IS NOT NULL)
);

-- Índices para performance
CREATE INDEX idx_questions_user_id ON questions(user_id);
CREATE INDEX idx_questions_theme_id ON questions(theme_id);
CREATE INDEX idx_questions_custom_theme_id ON questions(custom_theme_id);
CREATE INDEX idx_questions_generation_id ON questions(generation_id);
CREATE INDEX idx_questions_created_at ON questions(created_at DESC);