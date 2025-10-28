ALTER TABLE users
ADD COLUMN updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW();

-- Adicionar updated_at em generations
ALTER TABLE generations
ADD COLUMN updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW();

-- Adicionar updated_at em themes_customs
ALTER TABLE themes_customs
ADD COLUMN updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW();

-- Criar função para atualizar updated_at automaticamente
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ language 'plpgsql';

-- Criar triggers para atualizar updated_at automaticamente
CREATE TRIGGER update_users_updated_at
    BEFORE UPDATE ON users
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_generations_updated_at
    BEFORE UPDATE ON generations
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_themes_customs_updated_at
    BEFORE UPDATE ON themes_customs
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();