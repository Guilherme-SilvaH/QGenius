require('dotenv').config();
const express = require('express');
const cors = require('cors');
const { GoogleGenerativeAI } = require('@google/generative-ai');

const app = express();
app.use(express.json());
app.use(cors());

const genAI = new GoogleGenerativeAI(process.env.GOOGLE_API_KEY);

function limparRespostaGemini(texto) {
  const match = texto.match(/```json([\s\S]*?)```/);
  if (match && match[1]) return match[1].trim();

  const start = texto.indexOf('[') !== -1 ? texto.indexOf('[') : texto.indexOf('{');
  return texto.substring(start);
}

app.post('/api/generate', async (req, res) => {
  try {
    const { themes, difficulty, type, quantity } = req.body;
    console.log(`[IA-SERVICE] Requisição: ${themes}, ${difficulty}, ${type}, ${quantity}`);

    const prompt = `
      Gere ${quantity} perguntas de quiz sobre "${themes}".
      Dificuldade: ${difficulty}.
      Tipo: ${type}.
      REGRAS:
      1. Responda apenas com um array JSON válido.
      2. Estrutura:
         {
           "question": "texto da pergunta",
           "options": ["A", "B", "C", "D"],
           "answer": "texto exato da resposta correta"
         }
      3. Se for "Verdadeiro/Falso", use ["Verdadeiro", "Falso"].
    `;

    const model = genAI.getGenerativeModel({ model: 'gemini-2.0-flash-exp' });
    const result = await model.generateContent(prompt);
    
    const textoDaIA = result.response.text();
    const textoJSONLimpo = limparRespostaGemini(textoDaIA);

    let perguntasArray;
    try {
      perguntasArray = JSON.parse(textoJSONLimpo);
    } catch (err) {
      console.error('[IA-SERVICE] JSON inválido:', textoJSONLimpo);
      return res.status(500).json({ error: 'IA retornou JSON inválido.' });
    }

    console.log(`[IA-SERVICE] Enviando ${perguntasArray.length} perguntas.`);
    res.status(200).json(perguntasArray);

  } catch (error) {
    console.error('[IA-SERVICE] Erro ao gerar perguntas:', error);
    res.status(500).json({ error: 'Falha ao gerar perguntas no serviço de IA.' });
  }
});

app.get('/health', (req, res) => {
  res.json({ status: 'ok' });
});

const PORT = process.env.PORT || 3001;
app.listen(PORT, () => console.log(`[IA-SERVICE] Rodando na porta ${PORT}`));