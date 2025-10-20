#!/bin/bash

# ============================================
# scripts/reset-and-migrate.sh
# Reset completo do banco e aplicação das migrations
# ============================================

set -e  # Parar em caso de erro

echo "🔧 QGenius - Database Reset and Migration"
echo "=========================================="
echo ""

# Cores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Verificar se o PostgreSQL está rodando
echo "📡 Checking PostgreSQL..."
if ! docker ps | grep -q qgenius-postgres; then
    echo -e "${RED}❌ PostgreSQL container is not running!${NC}"
    echo "Starting PostgreSQL..."
    docker-compose up -d postgres
    sleep 5
fi
echo -e "${GREEN}✓ PostgreSQL is running${NC}"
echo ""

# Confirmar ação
echo -e "${YELLOW}⚠️  WARNING: This will DELETE ALL DATA in the database!${NC}"
read -p "Are you sure you want to continue? (yes/no): " -r
echo
if [[ ! $REPLY =~ ^[Yy]es$ ]]; then
    echo "Aborted."
    exit 1
fi

# Parar o backend se estiver rodando
echo "🛑 Stopping backend..."
pkill -f "qgenius-backend" 2>/dev/null || true
echo -e "${GREEN}✓ Backend stopped${NC}"
echo ""

# Backup do banco (opcional)
BACKUP_DIR="backups"
TIMESTAMP=$(date +%Y%m%d_%H%M%S)
mkdir -p $BACKUP_DIR

echo "💾 Creating backup (optional)..."
read -p "Do you want to backup the current database? (y/N): " -n 1 -r
echo
if [[ $REPLY =~ ^[Yy]$ ]]; then
    docker exec qgenius-postgres pg_dump -U postgres qgenius > "$BACKUP_DIR/qgenius_$TIMESTAMP.sql"
    echo -e "${GREEN}✓ Backup saved to $BACKUP_DIR/qgenius_$TIMESTAMP.sql${NC}"
fi
echo ""

# Dropar o banco
echo "🗑️  Dropping database..."
docker exec qgenius-postgres psql -U postgres -c "DROP DATABASE IF EXISTS qgenius;" 2>/dev/null || true
echo -e "${GREEN}✓ Database dropped${NC}"
echo ""

# Criar banco novamente
echo "🔨 Creating fresh database..."
docker exec qgenius-postgres psql -U postgres -c "CREATE DATABASE qgenius;"
echo -e "${GREEN}✓ Database created${NC}"
echo ""

# Limpar target/classes (cache do Flyway)
echo "🧹 Cleaning Flyway cache..."
rm -rf target/classes/db/migration 2>/dev/null || true
rm -rf target/flyway 2>/dev/null || true
echo -e "${GREEN}✓ Cache cleaned${NC}"
echo ""

# Verificar migrations
echo "📋 Checking migration files..."
MIGRATION_DIR="backend/src/main/resources/db/migration"

if [ ! -d "$MIGRATION_DIR" ]; then
    echo -e "${RED}❌ Migration directory not found: $MIGRATION_DIR${NC}"
    exit 1
fi

MIGRATION_COUNT=$(ls -1 $MIGRATION_DIR/V*.sql 2>/dev/null | wc -l)
if [ $MIGRATION_COUNT -eq 0 ]; then
    echo -e "${RED}❌ No migration files found in $MIGRATION_DIR${NC}"
    exit 1
fi

echo -e "${GREEN}✓ Found $MIGRATION_COUNT migration files${NC}"
echo ""
echo "Migration files:"
ls -1 $MIGRATION_DIR/V*.sql | while read file; do
    echo "  - $(basename $file)"
done
echo ""

# Remover migrations antigas (V2 e V3 duplicadas)
echo "🧹 Cleaning up old/duplicate migrations..."
read -p "Remove V2 and V3 migrations? (will use the new organized ones) (y/N): " -n 1 -r
echo
if [[ $REPLY =~ ^[Yy]$ ]]; then
    # Backup das migrations antigas
    mkdir -p migrations_backup
    [ -f "$MIGRATION_DIR/V2__questions_schema.sql" ] && mv "$MIGRATION_DIR/V2__questions_schema.sql" migrations_backup/
    [ -f "$MIGRATION_DIR/V3__tablesv2_schema.sql" ] && mv "$MIGRATION_DIR/V3__tablesv2_schema.sql" migrations_backup/
    echo -e "${GREEN}✓ Old migrations backed up to migrations_backup/${NC}"
fi
echo ""

# Compilar o projeto (para copiar migrations para target)
echo "🔨 Compiling project..."
./mvnw clean compile -DskipTests
echo -e "${GREEN}✓ Project compiled${NC}"
echo ""

# Rodar Flyway migrate
echo "🚀 Running Flyway migrations..."
./mvnw flyway:migrate -Dflyway.user=postgres -Dflyway.password=postgres -Dflyway.url=jdbc:postgresql://localhost:5432/qgenius

if [ $? -eq 0 ]; then
    echo -e "${GREEN}✓ Migrations applied successfully!${NC}"
else
    echo -e "${RED}❌ Migration failed!${NC}"
    exit 1
fi
echo ""

# Verificar migrations aplicadas
echo "📊 Checking applied migrations..."
docker exec qgenius-postgres psql -U postgres -d qgenius -c "SELECT version, description, installed_on FROM flyway_schema_history ORDER BY version;"
echo ""

# Listar tabelas criadas
echo "📋 Tables created:"
docker exec qgenius-postgres psql -U postgres -d qgenius -c "\dt"
echo ""

echo -e "${GREEN}=========================================="
echo "✅ Database reset and migration complete!"
echo "==========================================${NC}"
echo ""
echo "You can now start the backend:"
echo "  ./mvnw spring-boot:run"
echo ""
echo "Or run tests:"
echo "  ./mvnw test"
echo ""