echo "🛑 Stopping QGenius Microservices..."

docker-compose down

echo "✅ All services stopped!"
echo ""
echo "To remove volumes (⚠️  deletes all data):"
echo "  docker-compose down -v"
