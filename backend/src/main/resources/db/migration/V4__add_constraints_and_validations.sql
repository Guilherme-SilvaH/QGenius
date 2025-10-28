ALTER TABLE users
ADD CONSTRAINT check_users_plan
CHECK (plan IN ('FREE', 'PREMIUM'));

ALTER TABLE users
ADD CONSTRAINT check_users_email_format
CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$');

-- Validações para generations
ALTER TABLE generations
ADD CONSTRAINT check_generations_difficulty
CHECK (difficulty IN ('EASY', 'MEDIUM', 'HARD'));

ALTER TABLE generations
ADD CONSTRAINT check_generations_status
CHECK (status IN ('PENDING', 'PROCESSING', 'READY', 'FAILED'));

ALTER TABLE generations
ADD CONSTRAINT check_generations_type
CHECK (type IN ('MULTIPLE_CHOICE', 'ESSAY', 'TRUE_FALSE', 'MIXED'));

ALTER TABLE generations
ADD CONSTRAINT check_generations_quantity
CHECK (quantity > 0 AND quantity <= 100);