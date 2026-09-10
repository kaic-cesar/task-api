# Task API

Uma aplicação full-stack de gerenciamento de tarefas, desenvolvida para praticar a construção de uma aplicação completa com **Java, Spring Boot, PostgreSQL e JavaScript**.

O projeto possui uma API REST responsável pelo gerenciamento das tarefas e uma interface web para interação com a aplicação.

> **Status:** Projeto de estudo e portfólio. Executado localmente.

## Funcionalidades

* Criar tarefas
* Listar todas as tarefas
* Buscar uma tarefa pelo ID
* Atualizar uma tarefa
* Concluir uma tarefa
* Reabrir uma tarefa
* Excluir tarefas
* Armazenar as tarefas em banco de dados PostgreSQL
* Interface web para gerenciamento das tarefas
* Integração entre frontend e API REST

## Tecnologias

### Backend

* Java 17
* Spring Boot
* Spring Web MVC
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Lombok
* Springdoc OpenAPI

### Frontend

* HTML5
* CSS3
* JavaScript
* Fetch API

## Arquitetura

O backend foi estruturado utilizando uma separação em camadas:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL
```

### Principais componentes

```text
src/
└── main/
    └── java/
        └── com/kaiccesar/task_api/
            ├── controller/
            ├── service/
            ├── repository/
            ├── model/
            ├── dto/
            ├── exception/
            └── config/
```

O frontend está localizado em:

```text
front/
├── index.html
├── style.css
└── script.js
```

## Como funciona

A aplicação permite criar e gerenciar tarefas através de uma interface web.

Cada tarefa possui:

* **ID**
* **Título**
* **Descrição**
* **Data de criação**
* **Status**

O status da tarefa pode ser alterado entre pendente e concluída, permitindo também reabrir uma tarefa já concluída.

## API REST

A API utiliza o seguinte recurso:

```text
/v1/tasks
```

### Listar tarefas

```http
GET /v1/tasks
```

Retorna todas as tarefas cadastradas.

### Buscar tarefa

```http
GET /v1/tasks/{id}
```

Retorna uma tarefa específica através do seu ID.

### Criar tarefa

```http
POST /v1/tasks
```

Exemplo:

```json
{
  "title": "Estudar Spring Boot",
  "description": "Aprender Spring Security"
}
```

### Atualizar tarefa

```http
PUT /v1/tasks/{id}
```

Atualiza os dados de uma tarefa existente.

### Concluir ou reabrir tarefa

```http
PATCH /v1/tasks/{id}
```

Altera o status da tarefa.

### Excluir tarefa

```http
DELETE /v1/tasks/{id}
```

Remove uma tarefa do banco de dados.

## Banco de dados

As tarefas são persistidas utilizando **PostgreSQL** através do **Spring Data JPA/Hibernate**.

A aplicação utiliza variáveis de ambiente para as configurações de conexão com o banco:

```env
DB_URL=
DB_USERNAME=
DB_PASSWORD=
```

As credenciais locais não devem ser versionadas no repositório.

## Executando o projeto

### Pré-requisitos

* Java 17+
* Maven
* PostgreSQL
* Navegador web

### 1. Clone o repositório

```bash
git clone https://github.com/kaic-cesar/task-api.git
```

```bash
cd task-api
```

### 2. Configure o banco de dados

Crie um banco PostgreSQL para o projeto e configure as variáveis de ambiente:

```env
DB_URL=jdbc:postgresql://localhost:5432/task_api
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
```

### 3. Execute o backend

Linux/macOS:

```bash
./mvnw spring-boot:run
```

Windows:

```bash
mvnw.cmd spring-boot:run
```

A API estará disponível em:

```text
http://localhost:8080
```

### 4. Execute o frontend

Abra o arquivo:

```text
front/index.html
```

no navegador.

O frontend se comunica com a API através de:

```text
http://localhost:8080/v1/tasks
```

## Objetivo do projeto

Este projeto foi desenvolvido como uma aplicação prática para consolidar conhecimentos em:

* Desenvolvimento de APIs REST
* Spring Boot
* Arquitetura em camadas
* Injeção de dependências
* DTOs
* Spring Data JPA
* Hibernate
* Persistência com PostgreSQL
* Integração entre frontend e backend
* Operações CRUD
* Tratamento de exceções
* Consumo de APIs utilizando JavaScript

## Próximos estudos

Como este é um projeto de aprendizado, alguns recursos podem ser adicionados futuramente:

* Spring Security
* Autenticação e autorização
* Validação de dados
* Testes automatizados
* Paginação
* Docker
* Deploy

## Autor

**Kaic Cesar**

Desenvolvedor focado em Backend com Java e Spring Boot.

[GitHub](https://github.com/kaic-cesar)
