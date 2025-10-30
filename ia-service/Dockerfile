# 1. Use uma imagem base oficial do Node (versão 20 é estável e moderna)
FROM node:20-alpine

# 2. Defina o diretório de trabalho dentro do container
WORKDIR /app

# 3. Copie o package.json (e package-lock.json) primeiro
#    Isso otimiza o cache do Docker. Se só mudarmos o server.js,
#    o Docker não vai rodar 'npm install' de novo.
COPY package*.json ./

# 4. Instale TODAS as dependências (incluindo devDependencies como nodemon)
RUN npm install

# 5. Copie o restante do código da aplicação
COPY . .

# 6. Exponha a porta que o app vai rodar (definida no .env)
EXPOSE 3001

# 7. O comando para iniciar o app será definido no docker-compose.yml