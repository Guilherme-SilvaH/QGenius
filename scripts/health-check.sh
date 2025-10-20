echo "🏥 Health Check - QGenius Services"
echo ""

# PostgreSQL
echo -n "PostgreSQL:  "
if docker exec qgenius-postgres pg_isready -U postgres -d qgenius > /dev/null 2>&1; then
    echo "✅ Healthy"
else
    echo "❌ Unhealthy"
fi

# Redis
echo -n "Redis:       "
if docker exec qgenius-redis redis-cli ping > /dev/null 2>&1; then
    echo "✅ Healthy"
else
    echo "❌ Unhealthy"
fi

# Backend
echo -n "Backend:     "
BACKEND_STATUS=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:8080/actuator/health)
if [ "$BACKEND_STATUS" = "200" ]; then
    echo "✅ Healthy"
else
    echo "❌ Unhealthy (Status: $BACKEND_STATUS)"
fi

# IA Service
echo -n "IA Service:  "
IA_STATUS=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:3001/health)
if [ "$IA_STATUS" = "200" ]; then
    echo "✅ Healthy"
else
    echo "❌ Unhealthy (Status: $IA_STATUS)"
fi

# Frontend
echo -n "Frontend:    "
FRONTEND_STATUS=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:3000)
if [ "$FRONTEND_STATUS" = "200" ]; then
    echo "✅ Healthy"
else
    echo "❌ Unhealthy (Status: $FRONTEND_STATUS)"
fi
