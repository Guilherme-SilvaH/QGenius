#!/bin/bash
# =============================================
# 🚀 Script de setup para projeto Node + Express + TypeScript
# =============================================

# Verifica se o nome do projeto foi passado
if [ -z "$1" ]; then
  echo "❌ Uso: ./setup-node-express.sh nome-do-projeto"
  exit 1
fi

PROJECT_NAME=$1

echo "📦 Criando projeto $PROJECT_NAME ..."
mkdir $PROJECT_NAME && cd $PROJECT_NAME

echo "📥 Inicializando npm ..."
npm init -y

echo "⚙️ Instalando dependências principais ..."
npm install express dotenv cors

echo "🧰 Instalando dependências de desenvolvimento ..."
npm install -D typescript ts-node @types/node @types/express @types/cors nodemon

echo "📝 Inicializando TypeScript ..."
npx tsc --init

echo "🧩 Ajustando tsconfig.json ..."
sed -i 's/"rootDir": ".\/"/"rootDir": ".\/src"/' tsconfig.json
sed -i 's/"outDir": ".\/"/"outDir": ".\/dist"/' tsconfig.json

echo "📁 Criando estrutura de pastas ..."
mkdir src
mkdir src/routes
mkdir src/controllers
mkdir src/config

# Cria arquivo .env
cat <<EOF > .env
PORT=3000
EOF

# Cria o servidor base
cat <<EOF > src/server.ts
import express from "express";
import dotenv from "dotenv";
import cors from "cors";

dotenv.config();
const app = express();
app.use(express.json());
app.use(cors());

const PORT = process.env.PORT || 3000;

app.get("/", (req, res) => {
  res.json({ message: "🚀 Servidor rodando com sucesso!" });
});

app.listen(PORT, () => {
  console.log(\`✅ Servidor rodando na porta \${PORT}\`);
});
EOF

npx npm-add-script -k "dev" -v "nodemon src/server.ts"

echo "🎉 Projeto Node + Express + TypeScript criado com sucesso!"
echo ""
echo "👉 Para iniciar:"
echo "cd $PROJECT_NAME"
echo "npm run dev"
