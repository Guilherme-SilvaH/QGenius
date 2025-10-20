SERVICE=$1

if [ -z "$SERVICE" ]; then
    echo "🔨 Rebuilding all services..."
    docker-compose build --no-cache
else
    echo "🔨 Rebuilding $SERVICE..."
    docker-compose build --no-cache $SERVICE
fi

echo "✅ Rebuild complete!"
echo ""
echo "To restart the service(s):"
echo "  docker-compose up -d $SERVICE"
