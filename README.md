# 🏋️ Treino API — Projeto Academia CP2 (2º semestre/2026)

API REST em **Java 17 + Spring Boot 3** com **Spring Data JPA** e banco **SQL Server**, para gerenciamento de treinos e exercícios de uma academia.
Disciplina: *Microservices and Web Engineering*.

**Integrantes:**

| Nome completo | RM |
|---|---|
| Arthur Queiroz | 555205 |
| Gabriel Borges | 556142 |

---

## 🧰 Tecnologias
Java 17 · Spring Boot 3.2.5 · Spring Data JPA/Hibernate · **SQL Server (mssql-jdbc)** · Bean Validation · Lombok · SpringDoc (Swagger UI) · Docker

## 📁 Estrutura
```
src/main/java/com/checkpoint/treino
├── controller/   TreinoController, ExercicioController (endpoints REST)
├── dto/          request / response
├── mapper/       conversão entidade <-> DTO
├── model/        Treino, Exercicio (entidades JPA -> tabelas treinos, exercicios)
├── repository/   Spring Data JPA
├── config/       DataLoader (dados de exemplo)
└── exception/    tratamento de erros de validação (400)
src/main/resources
├── application.properties / application-default.properties / application-prd.properties
└── sql/schema.sql   (script T-SQL das tabelas)
```

---

## 🔌 Configuração da conexão com o SQL Server

A conexão está em `src/main/resources/application-default.properties` e é configurada por **variáveis de ambiente**:

| Variável | Padrão | Descrição |
|---|---|---|
| `DB_HOST` | `localhost` | Servidor do SQL Server |
| `DB_PORT` | `1433` | Porta |
| `DB_NAME` | `treino_db` | Nome do banco |
| `DB_USER` | `sa` | Usuário |
| `DB_PASSWORD` | `Fiap@12345` | Senha |
| `DDL_AUTO` | `update` | `update` cria as tabelas sozinho; use `none` se o banco já tiver as tabelas |

URL JDBC usada:
```
jdbc:sqlserver://<DB_HOST>:<DB_PORT>;databaseName=<DB_NAME>;encrypt=true;trustServerCertificate=true
```

### Usando o banco disponibilizado (remoto)
Defina os dados fornecidos e rode a aplicação.

Linux/macOS:
```bash
export DB_HOST=SERVIDOR DB_PORT=1433 DB_NAME=BANCO DB_USER=USUARIO DB_PASSWORD=SENHA
```
Windows (PowerShell):
```powershell
$env:DB_HOST="SERVIDOR"; $env:DB_PORT="1433"; $env:DB_NAME="BANCO"; $env:DB_USER="USUARIO"; $env:DB_PASSWORD="SENHA"
```
> O banco informado precisa existir. As tabelas `treinos` e `exercicios` são criadas automaticamente (`DDL_AUTO=update`) ou manualmente com `src/main/resources/sql/schema.sql`.

### Banco local com Docker (opção mais simples para testar)
```bash
docker compose up -d sqlserver sqlserver-init
```
Sobe o SQL Server em `localhost:1433` (usuário `sa`, senha `Fiap@12345`) e cria o banco `treino_db`.

---

## ▶️ Como executar

**Pré-requisitos:** JDK 17+, Maven 3.8+ e (para banco local) Docker.

**Opção A – Maven (com banco local ou remoto já configurado):**
```bash
mvn spring-boot:run
```

**Opção B – Tudo com Docker (API + SQL Server):**
```bash
docker compose up -d --build
```

A API fica em `http://localhost:8081`. Na primeira execução (profile `default`) são inseridos 2 treinos e 2 exercícios de exemplo.

**Swagger UI:** http://localhost:8081/swagger-ui.html · **OpenAPI:** http://localhost:8081/api-docs

### Profile `prd`
Usa `ddl-auto=validate` (não cria tabelas). Rode antes o `schema.sql` no banco e informe:
`SPRING_PROFILES_ACTIVE=prd`, `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`.

---

## 🔀 Endpoints

### Treinos (`/treinos`)
| Método | Rota | Descrição |
|---|---|---|
| GET | /treinos | Lista todos |
| GET | /treinos/{id} | Busca por ID |
| POST | /treinos | Cria |
| PUT | /treinos/{id} | Atualiza |
| DELETE | /treinos/{id} | Exclui |

### Exercícios (`/exercicios`)
| Método | Rota | Descrição |
|---|---|---|
| GET | /exercicios | Lista todos |
| GET | /exercicios/{id} | Busca por ID |
| POST | /exercicios | Cria |
| PUT | /exercicios/{id} | Atualiza |
| DELETE | /exercicios/{id} | Exclui |

Dados inválidos retornam **400** com os campos com erro; ID inexistente retorna **404**.

---

## 🧪 Como testar

```bash
# Consultar
curl http://localhost:8081/treinos
curl http://localhost:8081/exercicios

# Inserir treino
curl -X POST http://localhost:8081/treinos -H "Content-Type: application/json" -d '{
  "nome": "Treino C - Pernas",
  "dataTreino": "2026-10-10",
  "duracaoMinutos": 70,
  "tipo": "Musculação",
  "nivel": "Avançado",
  "observacoes": "Foco em força"
}'

# Inserir exercício
curl -X POST http://localhost:8081/exercicios -H "Content-Type: application/json" -d '{
  "nome": "Agachamento Livre",
  "grupoMuscular": "Pernas",
  "series": 4,
  "repeticoes": 8,
  "cargaKg": 80.0,
  "descricao": null
}'

# Alterar
curl -X PUT http://localhost:8081/exercicios/1 -H "Content-Type: application/json" -d '{
  "nome": "Supino Reto",
  "grupoMuscular": "Peitoral",
  "series": 5,
  "repeticoes": 8,
  "cargaKg": 70.0
}'

# Excluir
curl -X DELETE http://localhost:8081/treinos/1
```

**Conferir direto no SQL Server:**
```sql
SELECT * FROM treinos;
SELECT * FROM exercicios;
```
