# Instruções de Uso

## Como executar a aplicação

Para executar toda a aplicação, você precisa apenas ter o **Docker** e **Docker Compose** instalados na sua máquina.

### Comandos

1. **Para iniciar todos os serviços:**
```bash
docker-compose up --build
```

2. **Para iniciar em background (recomendado):**
```bash
docker-compose up --build -d
```

3. **Para parar os serviços:**
```bash
docker-compose down
```

4. **Para ver o status dos containers:**
```bash
docker-compose ps
```

5. **Para ver os logs:**
```bash
docker-compose logs
```

### Serviços disponíveis

- **Discovery Server (Eureka)**: http://localhost:8761
- **Config Server**: http://localhost:8888
- **Criar Usuários**: http://localhost:8081
- **CRUD de Eventos**: http://localhost:8082
- **Notification Service**: http://localhost:8083

### Observações importantes

- ✅ **Não é necessário ter Java instalado** - tudo roda dentro do Docker
- ✅ **Não é necessário Maven** - o build é feito automaticamente
- ✅ **As pastas `target/` estão no .gitignore** - não serão versionadas
- ✅ **O build é feito automaticamente** pelo Docker a cada execução

### Primeira execução

Na primeira execução, o Docker irá:
1. Baixar as imagens base do Java 17
2. Fazer o build dos projetos com Maven
3. Criar os containers
4. Iniciar todos os serviços

Isso pode levar alguns minutos na primeira vez, mas será mais rápido nas próximas execuções.
