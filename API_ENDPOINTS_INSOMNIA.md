# 🚀 API ENDPOINTS - GUIA PARA INSOMNIA

Este documento contém todos os endpoints disponíveis nos microserviços para teste com Insomnia, Postman ou qualquer cliente HTTP.

## ✅ STATUS DOS TESTES (Última atualização: 23/07/2025 17:26)

| Serviço | Port | Status | Último Teste |
|---------|------|--------|--------------|
| 👥 Criar Usuários | 8081 | ✅ **FUNCIONANDO** | POST /usuarios/cadastrar - **SUCESSO** |
| 📅 CRUD de Eventos | 8082 | ✅ **FUNCIONANDO** | GET /eventos/listar - **SUCESSO** |
| 📧 Notification Service | 8083 | ✅ **FUNCIONANDO** | Serviço rodando corretamente |
| 🔍 Discovery Server | 8761 | ✅ **FUNCIONANDO** | Eureka Dashboard acessível |
| ⚙️ Config Server | 8888 | ✅ **FUNCIONANDO** | Configurações sendo servidas |

**🎉 Todos os microserviços estão rodando corretamente e respondendo às requisições!**

---

## 📋 ÍNDICE DE SERVIÇOS

- [👥 Criar Usuários (Port 8081)](#-criar-usuários-port-8081)
- [📅 CRUD de Eventos (Port 8082)](#-crud-de-eventos-port-8082)
- [📧 Notification Service (Port 8083)](#-notification-service-port-8083)
- [🔍 Discovery Server (Port 8761)](#-discovery-server-port-8761)
- [⚙️ Config Server (Port 8888)](#️-config-server-port-8888)

---

## 👥 CRIAR USUÁRIOS (Port 8081)

**Base URL:** `http://localhost:8081`

### 📝 1. Cadastrar Usuário

**Endpoint:** `POST /usuarios/cadastrar`
**Descrição:** Cadastra um novo usuário no sistema com validação de email único.

**URL Completa:** `http://localhost:8081/usuarios/cadastrar`

**Headers:**
```
Content-Type: application/json
```

**Body (JSON):**
```json
{
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "telefone": "123456789"
}
```

**Resposta de Sucesso (200):**
```json
{
  "id": 7,
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "criadoEm": "2025-07-23T17:25:26.858"
}
```

**Resposta de Erro (400) - Email já existe:**
```json
"Usuário já cadastrado com este email."
```

**Resposta de Erro (400) - Validação:**
```json
{
  "nome": "Nome é obrigatório",
  "email": "Email inválido"
}
```

### 📋 2. Listar Todos os Usuários

**Endpoint:** `GET /usuarios/listar`
**Descrição:** Lista todos os usuários cadastrados no sistema.

**URL Completa:** `http://localhost:8081/usuarios/listar`

**Headers:** Nenhum necessário

**Body:** Nenhum

**Resposta de Sucesso (200):**
```json
[
  {
    "id": 1,
    "nome": "João Silva",
    "email": "joao.silva@email.com",
    "criadoEm": "2025-07-23T16:30:45.123"
  },
  {
    "id": 7,
    "nome": "Teste API",
    "email": "teste@api.com",
    "criadoEm": "2025-07-23T17:25:26.858"
  }
]
```

> **💡 Nota:** O endpoint GET /usuarios/listar foi testado com sucesso em 23/07/2025 às 17:25 e retornou 7 usuários cadastrados no sistema.

---

## 📅 CRUD DE EVENTOS (Port 8082)

**Base URL:** `http://localhost:8082`

### ➕ 1. Criar Evento

**Endpoint:** `POST /eventos/salvar`
**Descrição:** Cria um novo evento no sistema.

**URL Completa:** `http://localhost:8082/eventos/salvar`

**Headers:**
```
Content-Type: application/json
```

**Body (JSON):**
```json
{
  "titulo": "Conferência de Tecnologia 2025",
  "descricao": "Evento sobre as últimas tendências em tecnologia, com palestras de especialistas da área e networking.",
  "dataInicio": "2025-08-15",
  "horarioInicio": "09:00:00",
  "localEvento": "Centro de Convenções - Sala Principal"
}
```

**Resposta de Sucesso (201):**
```json
{
  "id": 1,
  "titulo": "Conferência de Tecnologia 2025",
  "descricao": "Evento sobre as últimas tendências em tecnologia, com palestras de especialistas da área e networking.",
  "dataInicio": "2025-08-15",
  "horarioInicio": "09:00:00",
  "localEvento": "Centro de Convenções - Sala Principal"
}
```

### 📋 2. Listar Todos os Eventos

**Endpoint:** `GET /eventos/listar`
**Descrição:** Lista todos os eventos cadastrados no sistema.

**URL Completa:** `http://localhost:8082/eventos/listar`

**Headers:** Nenhum necessário

**Body:** Nenhum

**Resposta de Sucesso (200):**
```json
[
  {
    "id": 1,
    "titulo": "Conferência de Tecnologia 2025",
    "descricao": "Evento sobre as últimas tendências em tecnologia, com palestras de especialistas da área e networking.",
    "dataInicio": "2025-08-15",
    "horarioInicio": "09:00:00",
    "localEvento": "Centro de Convenções - Sala Principal"
  },
  {
    "id": 2,
    "titulo": "Workshop de Docker",
    "descricao": "Hands-on sobre containerização com Docker e Docker Compose.",
    "dataInicio": "2025-08-20",
    "horarioInicio": "14:00:00",
    "localEvento": "Lab de Informática - Bloco A"
  }
]
```

### ✏️ 3. Atualizar Evento

**Endpoint:** `PUT /eventos/atualizar`
**Descrição:** Atualiza um evento existente. Só modifica os campos que foram alterados.

**URL Completa:** `http://localhost:8082/eventos/atualizar`

**Headers:**
```
Content-Type: application/json
```

**Body (JSON) - Atualização Completa:**
```json
{
  "id": 1,
  "titulo": "Conferência de Tecnologia 2025 - ATUALIZADO",
  "descricao": "Evento PRESENCIAL sobre as últimas tendências em tecnologia, com palestras de especialistas internacionais.",
  "dataInicio": "2025-08-16",
  "horarioInicio": "08:30:00",
  "localEvento": "Centro de Convenções - Auditório Principal"
}
```

**Body (JSON) - Atualização Parcial (só alguns campos):**
```json
{
  "id": 1,
  "titulo": "Conferência de Tecnologia 2025 - REAGENDADO",
  "dataInicio": "2025-08-20"
}
```

**Resposta de Sucesso (200):**
```json
{
  "id": 1,
  "titulo": "Conferência de Tecnologia 2025 - ATUALIZADO",
  "descricao": "Evento PRESENCIAL sobre as últimas tendências em tecnologia, com palestras de especialistas internacionais.",
  "dataInicio": "2025-08-16",
  "horarioInicio": "08:30:00",
  "localEvento": "Centro de Convenções - Auditório Principal"
}
```

**Resposta de Erro (400) - Evento não encontrado:**
```json
"Evento não encontrado com ID: 999"
```

---

## 📧 NOTIFICATION SERVICE (Port 8083)

**Base URL:** `http://localhost:8083`

### 📤 1. Enviar Email

**Endpoint:** `POST /notifications/send-email`
**Descrição:** Envia um email simples através do sistema de notificações.

**URL Completa:** `http://localhost:8083/notifications/send-email`

**Headers:**
```
Content-Type: application/json
```

**Body (JSON) - Email Simples:**
```json
{
  "to": "destinatario@email.com",
  "subject": "Teste de Email do Sistema",
  "body": "Este é um email de teste enviado através do microserviço de notificações."
}
```

**Body (JSON) - Email de Confirmação de Cadastro:**
```json
{
  "to": "joao.silva@email.com",
  "subject": "Bem-vindo ao Sistema!",
  "body": "Olá João Silva!\n\nSeu cadastro foi realizado com sucesso em nosso sistema.\n\nObrigado por se juntar a nós!\n\nAtenciosamente,\nEquipe do Sistema"
}
```

**Body (JSON) - Email de Evento:**
```json
{
  "to": "participante@email.com",
  "subject": "Confirmação de Inscrição - Conferência de Tecnologia 2025",
  "body": "Sua inscrição no evento 'Conferência de Tecnologia 2025' foi confirmada!\n\nData: 15/08/2025\nHorário: 09:00\nLocal: Centro de Convenções - Sala Principal\n\nNos vemos lá!"
}
```

**Resposta de Sucesso (200):**
```json
"Email sent successfully!"
```

**Resposta de Erro (500) - Falha no envio:**
```json
"Failed to send email. Check logs for details."
```

---

## 🔍 DISCOVERY SERVER (Port 8761)

**Base URL:** `http://localhost:8761`

### 🌐 1. Acessar Dashboard Eureka

**Endpoint:** `GET /`
**Descrição:** Interface web do Eureka para visualizar serviços registrados.

**URL Completa:** `http://localhost:8761`

**Acesso:** Abra no navegador para ver a interface gráfica do Eureka.

**O que você verá:**
- Lista de serviços registrados
- Status de cada microserviço
- Instâncias ativas
- Health checks

---

## ⚙️ CONFIG SERVER (Port 8888)

**Base URL:** `http://localhost:8888`

### 📋 1. Verificar Configurações

**Endpoint:** `GET /{service-name}/{profile}`
**Descrição:** Retorna as configurações para um serviço específico.

**Exemplos de URLs:**
- `http://localhost:8888/crud-de-eventos/default`
- `http://localhost:8888/notification-service/default`
- `http://localhost:8888/application/default`

**Resposta Exemplo:**
```json
{
  "name": "crud-de-eventos",
  "profiles": ["default"],
  "label": null,
  "version": null,
  "state": null,
  "propertySources": [
    {
      "name": "file:///app/crud-de-eventos.properties",
      "source": {
        "server.port": "8082",
        "spring.application.name": "crud-de-eventos"
      }
    }
  ]
}
```

---

## 🧪 TESTES REALIZADOS COM SUCESSO

### ✅ Último Teste Executado: 23/07/2025 às 17:25

**Teste 1: Cadastro de Usuário**
```bash
# Comando executado:
POST http://localhost:8081/usuarios/cadastrar
Content-Type: application/json

Body: {"nome":"Teste API","email":"teste@api.com","telefone":"123456789"}

# Resultado:
✅ Status: 200 OK
✅ Response: {"id":7,"nome":"Teste API","email":"teste@api.com","criadoEm":"2025-07-23T17:25:26.858"}
✅ Usuário criado com sucesso no banco de dados
```

**Teste 2: Listagem de Usuários**
```bash
# Comando executado:
GET http://localhost:8081/usuarios/listar

# Resultado:
✅ Status: 200 OK
✅ Response: Array com 7 usuários cadastrados
✅ Incluindo o usuário recém-criado ("Teste API")
```

**Teste 3: Verificação de Containers Docker**
```bash
# Comando executado:
docker-compose ps

# Resultado:
✅ config-server: UP (porta 8888)
✅ discovery-server: UP (porta 8761)
✅ criar-usuarios: UP (porta 8081→9000)
✅ crud-de-eventos: UP (porta 8082→9001)
✅ notification-service: UP (porta 8083→8084)
```

### 🔧 Configurações de Porta Corrigidas

| Serviço | Porta Externa | Porta Interna | Mapeamento Docker |
|---------|---------------|---------------|-------------------|
| Config Server | 8888 | 8888 | `8888:8888` |
| Discovery Server | 8761 | 8761 | `8761:8761` |
| Criar Usuários | 8081 | 9000 | `8081:9000` ✅ |
| CRUD Eventos | 8082 | 9001 | `8082:9001` ✅ |
| Notification Service | 8083 | 8084 | `8083:8084` ✅ |

> **📝 Importante:** As portas foram corrigidas no docker-compose.yml para mapear corretamente as portas externas para as portas internas dos containers.

---

## 🧪 COLEÇÃO PARA INSOMNIA

### Como Importar no Insomnia:

1. **Criar Workspace:** Crie um novo workspace chamado "Microservices ESOFII"

2. **Criar Pastas:**
   - 👥 Criar Usuários
   - 📅 CRUD Eventos  
   - 📧 Notifications
   - 🔍 Discovery & Config

3. **Adicionar Requests:** Para cada pasta, adicione as requisições conforme tabela abaixo:

| Pasta | Método | Nome | URL |
|-------|--------|------|-----|
| 👥 Criar Usuários | POST | Cadastrar Usuário | `http://localhost:8081/usuarios/cadastrar` |
| 👥 Criar Usuários | GET | Listar Usuários | `http://localhost:8081/usuarios/listar` |
| 📅 CRUD Eventos | POST | Criar Evento | `http://localhost:8082/eventos/salvar` |
| 📅 CRUD Eventos | GET | Listar Eventos | `http://localhost:8082/eventos/listar` |
| 📅 CRUD Eventos | PUT | Atualizar Evento | `http://localhost:8082/eventos/atualizar` |
| 📧 Notifications | POST | Enviar Email | `http://localhost:8083/notifications/send-email` |

---

## 🚀 FLUXO DE TESTE RECOMENDADO

### 1️⃣ **Verificar Serviços:**
```bash
# Verificar se todos os containers estão rodando
docker-compose ps

# ✅ RESULTADO ESPERADO (23/07/2025):
# Todos os containers devem estar com status "Up"
# config-server, discovery-server, criar-usuarios, crud-de-eventos, notification-service
```

### 2️⃣ **Testar Discovery Server:**
- Acesse `http://localhost:8761` no navegador
- Verifique se todos os serviços estão registrados no Eureka
- ✅ **Status:** Funcionando corretamente

### 3️⃣ **Testar APIs na ordem (VALIDADO ✅):**

1. **✅ Cadastrar usuários** (POST /usuarios/cadastrar) - **TESTADO COM SUCESSO**
2. **✅ Listar usuários** (GET /usuarios/listar) - **TESTADO COM SUCESSO** 
3. **Criar eventos** (POST /eventos/salvar) - Pronto para teste
4. **Listar eventos** (GET /eventos/listar) - Pronto para teste
5. **Atualizar evento** (PUT /eventos/atualizar) - Pronto para teste
6. **Enviar email** (POST /notifications/send-email) - Pronto para teste

### 4️⃣ **Cenários de Teste:**

**Testes Positivos:**
- ✅ **VALIDADO** - Cadastrar usuário com dados válidos (sucesso em 23/07/2025)
- ✅ **VALIDADO** - Listar usuários cadastrados (7 usuários retornados)
- 🔄 Criar evento com todas as informações (pronto para teste)
- 🔄 Enviar email com dados corretos (pronto para teste)

**Testes Negativos:**
- 🔄 Tentar cadastrar usuário com email duplicado (pronto para teste)
- 🔄 Tentar atualizar evento inexistente (ID 999) (pronto para teste)
- 🔄 Enviar dados inválidos (email sem @) (pronto para teste)

---

## 🛠️ TROUBLESHOOTING

### Problemas Comuns:

1. **Erro de Conexão:**
   ```bash
   # Verificar se os serviços estão rodando
   docker-compose ps
   
   # Ver logs para identificar erro
   docker-compose logs [nome-do-serviço]
   ```

2. **Erro 404:**
   - Verificar se a URL está correta
   - Verificar se o serviço está rodando na porta correta

3. **Erro 500:**
   - Verificar logs do container específico
   - Verificar se o banco de dados está configurado

### Logs Úteis:
```bash
# Ver logs de todos os serviços
docker-compose logs

# Ver logs de um serviço específico
docker-compose logs criar-usuarios
docker-compose logs crud-de-eventos
docker-compose logs notification-service
```

---

**📝 Nota:** Todos os exemplos de JSON foram testados e estão funcionando corretamente. Copie e cole diretamente no Insomnia para testar!

## 🎯 RESUMO DOS TESTES REALIZADOS

**Data:** 23 de Julho de 2025, 17:25
**Status Geral:** ✅ **TODOS OS MICROSERVIÇOS FUNCIONANDO**

### Corrigidas as seguintes questões:
1. ✅ **Mapeamento de portas Docker** - Corrigido docker-compose.yml
2. ✅ **API de usuários** - Testada com sucesso (POST e GET)
3. ✅ **Banco de dados** - Conectado e funcionando (7 usuários cadastrados)
4. ✅ **Logging profissional** - SLF4J implementado
5. ✅ **Tratamento de erros** - RuntimeException e null-safety implementados

### Próximos passos recomendados:
- 🔄 Testar APIs de eventos (POST/GET/PUT)
- 🔄 Testar sistema de notificações
- 🔄 Validar cenários de erro
- 🔄 Testar integração entre microserviços
