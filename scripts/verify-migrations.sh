cat > scripts/verify-migrations.sh << 'VERIFY_SCRIPT'
#!/bin/bash

echo "🔍 Verifying Migration Files"
echo "============================="
echo ""

MIGRATION_DIR="src/main/resources/db/migration"
errors=0

# Verificar se existem arquivos duplicados
echo "Checking for duplicate migration versions..."
versions=$(ls $MIGRATION_DIR/V*.sql 2>/dev/null | grep -o 'V[0-9]*' | sort)
duplicates=$(echo "$versions" | uniq -d)

if [ ! -z "$duplicates" ]; then
    echo "❌ ERROR: Duplicate migration versions found:"
    echo "$duplicates"
    errors=$((errors + 1))
else
    echo "✓ No duplicate versions"
fi
echo ""

# Verificar sequência de versões
echo "Checking version sequence..."
expected=1
for file in $(ls $MIGRATION_DIR/V*.sql 2>/dev/null | sort -V); do
    version=$(basename $file | grep -o 'V[0-9]*' | grep -o '[0-9]*')
    if [ $version -ne $expected ]; then
        echo "⚠️  WARNING: Gap in version sequence. Expected V$expected, found V$version"
        echo "   File: $(basename $file)"
    fi
    expected=$((version + 1))
done
echo "✓ Checked up to V$((expected - 1))"
echo ""

# Verificar sintaxe SQL básica
echo "Checking SQL syntax (basic)..."
for file in $MIGRATION_DIR/V*.sql; do
    if ! grep -q "CREATE TABLE\|ALTER TABLE\|CREATE INDEX" "$file"; then
        echo "⚠️  WARNING: $(basename $file) might be empty or invalid"
        errors=$((errors + 1))
    fi
done
echo "✓ Basic syntax check complete"
echo ""

# Verificar CREATE TABLE duplicados
echo "Checking for duplicate table definitions..."
tables=$(grep -h "CREATE TABLE" $MIGRATION_DIR/V*.sql 2>/dev/null | grep -o "CREATE TABLE [IF NOT EXISTS ]* [a-zA-Z_]*" | awk '{print $NF}' | sort)
duplicates=$(echo "$tables" | uniq -d)

if [ ! -z "$duplicates" ]; then
    echo "❌ ERROR: Tables defined in multiple migrations:"
    echo "$duplicates"
    echo ""
    echo "These tables should use 'CREATE TABLE IF NOT EXISTS' or be in a single migration"
    errors=$((errors + 1))
else
    echo "✓ No duplicate table definitions"
fi
echo ""

if [ $errors -eq 0 ]; then
    echo "✅ All checks passed!"
    exit 0
else
    echo "❌ Found $errors errors"
    exit 1
fi
VERIFY_SCRIPT

chmod +x scripts/verify-migrations.sh

echo "📝 Created verification script: scripts/verify-migrations.sh"
echo "Run it with: ./scripts/verify-migrations.sh"