# ========================
# Estágio de dependências
# ========================
FROM node:20-alpine AS deps

WORKDIR /app

# Copiar package.json e package-lock.json
COPY package*.json ./

# Instalar todas dependências (incluindo dev para build)
RUN npm install

# ========================
# Estágio de build
# ========================
FROM node:20-alpine AS builder

WORKDIR /app

# Copiar node_modules do estágio anterior
COPY --from=deps /app/node_modules ./node_modules
# Copiar código fonte
COPY . .

# Build arguments (exemplo: API_URL)
ARG NEXT_PUBLIC_API_URL
ENV NEXT_PUBLIC_API_URL=$NEXT_PUBLIC_API_URL

# Build do Next.js em modo standalone
RUN npm run build

# ========================
# Estágio final (runner)
# ========================
FROM node:20-alpine AS runner

WORKDIR /app
ENV NODE_ENV=production
ENV PORT=3000
ENV HOSTNAME="0.0.0.0"

# Criar usuário não-root
RUN addgroup -g 1001 nodejs && adduser -D -u 1001 -G nodejs nodejs
USER nodejs:nodejs

# Copiar build standalone e arquivos necessários
COPY --from=builder --chown=nodejs:nodejs /app/.next/standalone ./
COPY --from=builder --chown=nodejs:nodejs /app/.next/static ./.next/static
COPY --from=builder --chown=nodejs:nodejs /app/public ./public
COPY --from=builder --chown=nodejs:nodejs /app/package.json ./package.json

# Expor porta
EXPOSE 3000

# Comando para rodar o Next.js standalone
CMD ["node", "server.js"]
