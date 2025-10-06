<div align="center">

# 🧠 QGenius

### Geração Inteligente de Questões de Estudo com IA

Plataforma moderna que utiliza Inteligência Artificial para gerar perguntas personalizadas para ENEM, vestibulares e revisões escolares.

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.java.com)
[![Node.js](https://img.shields.io/badge/Node.js-18+-green.svg)](https://nodejs.org)
[![Next.js](https://img.shields.io/badge/Next.js-14-black.svg)](https://nextjs.org)

[Funcionalidades](#-funcionalidades) • [Arquitetura](#-arquitetura) • [Tecnologias](#-tecnologias) • [Instalação](#-instalação) • [Roadmap](#-roadmap)

</div>

---

## ✨ Funcionalidades

- 🎯 **Geração Automática** — Crie questões personalizadas com IA em segundos
- 🎓 **Múltiplos Formatos** — Questões de múltipla escolha ou dissertativas
- 📊 **Níveis Adaptáveis** — Fácil, Médio ou Difícil conforme sua necessidade
- 💾 **Histórico Completo** — Armazene e gerencie todas as suas gerações
- 📤 **Exportação Flexível** — PDF, texto ou cópia rápida
- 💰 **Planos Flexíveis** — Versão gratuita e premium via Stripe
- 📈 **Dashboard Analítico** — Acompanhe seu progresso e estatísticas

---

## 🏗 Arquitetura

```
┌─────────────────┐      ┌──────────────────┐      ┌─────────────────┐
│   Next.js       │─────▶│  Spring Boot     │─────▶│   Node.js IA    │
│   Frontend      │◀─────│  API Gateway     │◀─────│   Service       │
└─────────────────┘      └──────────────────┘      └─────────────────┘
                                  │                          │
                                  │                          │
                                  ▼                          ▼
                         ┌─────────────────┐       ┌─────────────────┐
                         │   PostgreSQL    │       │   OpenAI API    │
                         │   + Redis       │       │   (LLM)         │
                         └─────────────────┘       └─────────────────┘
```

### Fluxo de Geração

1. **Usuário** define tema, nível e quantidade de questões
2. **Frontend** envia requisição para API Gateway
3. **Spring Boot** valida e encaminha para serviço de IA
4. **Node.js** processa prompt e chama LLM (OpenAI/Gemini)
5. **IA** retorna JSON estruturado com questões
6. **Backend** salva no PostgreSQL e notifica usuário
7. **Frontend** exibe resultado formatado

---

## 🧠 Inteligência Artificial

### Estratégia de Prompts

- **Templates Dinâmicos** — Variáveis para tema, nível, tipo e público-alvo
- **Saída Estruturada** — JSON validado com campos obrigatórios
- **Temperatura Controlada** — 0.0-0.8 para balancear criatividade
- **Moderação de Conteúdo** — Filtros para evitar conteúdo inadequado
- **Sistema de Fallback** — Retry automático em caso de falhas

---

## 🗄 Modelo de Dados

### Usuário

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | UUID | Identificador único |
| `name` | String | Nome completo |
| `email` | String | Email (único) |
| `password_hash` | String | Senha criptografada |
| `plan` | Enum | FREE / PREMIUM |
| `created_at` | Timestamp | Data de cadastro |

### Geração

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | UUID | Identificador único |
| `user_id` | UUID | Referência ao usuário |
| `theme` | String | Tema das questões |
| `difficulty` | String | Nível de dificuldade |
| `type` | String | Tipo de questão |
| `quantity` | Integer | Número de perguntas |
| `status` | Enum | pending / ready / failed |
| `questions` | JSON | Questões geradas |
| `created_at` | Timestamp | Data de criação |

---

## 🌐 API Endpoints

### Autenticação
```http
POST /api/v1/auth/signup      # Cadastrar usuário
POST /api/v1/auth/login       # Login (retorna JWT)
```

### Usuário
```http
GET  /api/v1/user/me          # Dados do usuário autenticado
```

### Gerações
```http
POST /api/v1/generations      # Criar nova geração
GET  /api/v1/generations/:id  # Buscar geração específica
GET  /api/v1/generations      # Listar gerações do usuário
```

### Pagamentos
```http
POST /api/v1/stripe/checkout  # Iniciar checkout Stripe
```

---

## 🛠 Tecnologias

<table>
<tr>
<td>

### Frontend
- Next.js 14
- TypeScript
- TailwindCSS
- NextAuth

</td>
<td>

### Backend
- Java 17
- Spring Boot
- Spring Security
- JPA/Hibernate

</td>
<td>

### IA Service
- Node.js
- Express
- OpenAI SDK
- Prompt Engineering

</td>
</tr>
<tr>
<td>

### Database
- PostgreSQL
- Redis

</td>
<td>

### Pagamentos
- Stripe API
- Webhooks

</td>
<td>

### DevOps
- Docker
- Prometheus
- Jaeger

</td>
</tr>
</table>

---

## 🚀 Instalação

### Pré-requisitos

- Node.js 18+
- Java 17+
- PostgreSQL 14+
- Redis
- Conta OpenAI (API Key)

### 1. Clone o repositório

```bash
git clone git@github.com:Guilherme-SilvaH/StudyGen.git
cd StudyGen
```

### 2. Configure as variáveis de ambiente

Crie um arquivo `.env` em cada diretório:

**Backend (`backend/.env`)**
```env
DATABASE_URL=postgresql://localhost:5432/qgenius
REDIS_URL=redis://localhost:6379
JWT_SECRET=seu_secret_aqui
STRIPE_SECRET_KEY=sk_test_...
```

**IA Service (`ia-service/.env`)**
```env
OPENAI_API_KEY=sk-...
PORT=3001
```

**Frontend (`frontend/.env.local`)**
```env
NEXT_PUBLIC_API_URL=http://localhost:8080
NEXTAUTH_SECRET=seu_secret_aqui
NEXTAUTH_URL=http://localhost:3000
```

### 3. Inicie os serviços

**Terminal 1 - Backend**
```bash
cd backend
./mvnw spring-boot:run
```

**Terminal 2 - IA Service**
```bash
cd ia-service
npm install
npm run dev
```

**Terminal 3 - Frontend**
```bash
cd frontend
npm install
npm run dev
```

### 4. Acesse a aplicação

Abra [http://localhost:3000](http://localhost:3000) no seu navegador.

---

## 🗺 Roadmap

### MVP (v1.0)
- [x] Sistema de autenticação
- [x] Geração básica de questões
- [x] Armazenamento de gerações
- [ ] Limites por plano
- [ ] Integração Stripe
- [ ] Exportação PDF
- [ ] Dashboard de estatísticas

### Futuro (v2.0)
- [ ] Compartilhamento de questões
- [ ] Modo colaborativo
- [ ] Suporte a imagens nas questões
- [ ] App mobile (React Native)
- [ ] Integração com Google Classroom

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👨‍💻 Autor

**Guilherme Henrique da Silva**

- GitHub: [@Guilherme-SilvaH](https://github.com/Guilherme-SilvaH)

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para:

1. Fazer um fork do projeto
2. Criar uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abrir um Pull Request

---

<div align="center">

**Feito com ❤️ e ☕ por [Guilherme Henrique](https://github.com/Guilherme-SilvaH)**

⭐ Se este projeto te ajudou, considere dar uma estrela!

</div>