echo "🚀 Starting QGenius Microservices..."

# Verificar se .env existe
if [ ! -f .env ]; then
    echo "⚠️  .env file not found. Copying from .env.example..."
    cp .env.example .env
    echo "✅ Please edit .env with your credentials before continuing."
    exit 1
fi

# Subir apenas serviços essenciais
docker-compose up -d postgres redis

echo "⏳ Waiting for databases to be ready..."
sleep 10

# Subir backend
docker-compose up -d backend

echo "⏳ Waiting for backend to be ready..."
sleep 15

# Subir IA service
docker-compose up -d ia-service

echo "⏳ Waiting for IA service to be ready..."
sleep 10

# Subir frontend
docker-compose up -d frontend

echo ""
echo "✅ QGenius is running!"
echo ""
echo "📍 Services:"
echo "   Frontend:  http://localhost:3000"
echo "   Backend:   http://localhost:8080"
echo "   IA Service: http://localhost:3001"
echo "   PostgreSQL: localhost:5432"
echo "   Redis:      localhost:6379"
echo ""
echo "📊 Check status: docker-compose ps"
echo "📋 View logs:    docker-compose logs -f [service-name]"
echo "🛑 Stop all:     docker-compose down"