#!/bin/bash

echo "⚠️  Resetting QGenius Database..."

# Parar backend se estiver rodando
echo "🛑 Stopping backend..."
pkill -f "qgenius-backend" 2>/dev/null || true

# Drop database
echo "🗑️  Dropping database..."
docker exec qgenius-postgres psql -U postgres -c "DROP DATABASE IF EXISTS qgenius;"

# Create database
echo "🔨 Creating database..."
docker exec qgenius-postgres psql -U postgres -c "CREATE DATABASE qgenius;"

# Clean Flyway cache (opcional)
rm -rf target/flyway 2>/dev/null || true

echo ""
echo "✅ Database reset complete!"
echo ""
echo "Now you can start the backend:"
echo "  ./mvnw spring-boot:run"