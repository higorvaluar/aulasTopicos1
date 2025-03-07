# Aulas de TP1

Este é um projeto Java desenvolvido como parte das aulas de **Tópicos em Programação I**. Ele utiliza **Quarkus**, **Hibernate ORM**, **PostgreSQL** e **Docker** para gerenciar entidades de estados brasileiros, com operações básicas de CRUD (Create, Read, Update, Delete). **O projeto ainda está em produção e em constante evolução.**

## Tecnologias Utilizadas

- **Quarkus**: Framework Java para aplicações nativas na nuvem.
- **Hibernate ORM**: Framework de mapeamento objeto-relacional (ORM) para Java.
- **PostgreSQL**: Banco de dados relacional.
- **Docker**: Plataforma para criar, implantar e executar aplicações em contêineres.
- **Maven**: Ferramenta de gerenciamento de dependências e build.

## Funcionalidades

- **Listar todos os estados**: Retorna uma lista de todos os estados cadastrados.
- **Buscar estado por sigla**: Retorna um estado específico com base na sigla.
- **Adicionar um novo estado**: Permite a inclusão de um novo estado no banco de dados.
- **Atualizar um estado**: Permite a edição dos dados de um estado existente.
- **Excluir um estado**: Remove um estado do banco de dados.

## Pré-requisitos

Antes de começar, você precisará ter instalado:

- **Java JDK 21** (ou superior).
- **Maven**.
- **Docker** (opcional, para rodar o PostgreSQL em um contêiner).
- **PostgreSQL** (se não estiver usando Docker).

## Configuração do Projeto

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/aulas-de-topicos1.git
cd aulas-de-topicos1
```

### 2. Configuração do Banco de Dados

#### Usando Docker (recomendado)

1. Inicie um contêiner PostgreSQL com Docker:

```bash
docker run --name topicos1db -e POSTGRES_USER=topicos1 -e POSTGRES_PASSWORD=123456 -e POSTGRES_DB=topicos1db -p 5432:5432 -d postgres
```

2. Verifique se o contêiner está rodando:

```bash
docker ps
```

#### Usando PostgreSQL local

1. Crie um banco de dados chamado `topicos1db`.
2. Crie um usuário `topicos1` com senha `123456` e conceda todas as permissões necessárias.

### 3. Configuração do `application.properties`

#### No arquivo `src/main/resources/application.properties`, verifique as seguintes configurações:

```properties
quarkus.datasource.db-kind = postgresql
quarkus.datasource.username = topicos1
quarkus.datasource.password = 123456
quarkus.datasource.jdbc.url = jdbc:postgresql://localhost:5432/topicos1db

quarkus.hibernate-orm.database.generation=drop-and-create
quarkus.hibernate-orm.log.sql=true
quarkus.hibernate-orm.log.bind-parameters=true
```

### 4. Executando a Aplicação

1. Compile e execute o projeto com Maven:

```bash
./mvnw quarkus:dev
```

2. A aplicação estará disponível em:
```bash
http://localhost:8080
```

## Endpoints da API

### Estados

- **GET /estados**: Retorna todos os estados.
```bash
curl -X GET http://localhost:8080/estados
```

- **GET /estados/sigla/{sigla}**: Retorna um estado por sigla.
```bash
curl -X GET http://localhost:8080/estados/sigla/SP
```

- **POST /estados**: Adiciona um novo estado.
```bash
curl -X POST -H "Content-Type: application/json" -d '{"nome": "Minas Gerais", "sigla": "MG"}' http://localhost:8080/estados
```

- **PUT /estados/{id}**: Atualiza um estado existente.
```bash
curl -X PUT -H "Content-Type: application/json" -d '{"nome": "Minas Gerais", "sigla": "MG"}' http://localhost:8080/estados/1
```

- **DELETE /estados/{id}**: Exclui um estado.
```bash
curl -X DELETE http://localhost:8080/estados/1
```

## Licença

Este projeto está licenciado sob a licença MIT.
