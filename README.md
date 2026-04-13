# Finan

API backend do aplicativo **Finan**, um sistema de controle financeiro pessoal voltado ao registro de entradas e saídas e ao cálculo de orçamento mensal.

## Visão geral

O Finan permite que o usuário acompanhe sua movimentação financeira no dia a dia, categorizando lançamentos e consolidando o resultado do mês. A partir das receitas e despesas registradas, o serviço calcula o orçamento mensal, possibilitando uma visão clara do saldo e da distribuição dos gastos por categoria.

### Principais funcionalidades

- Cadastro e gerenciamento de **categorias** de receita e despesa.
- Registro de **valores** (entradas e saídas) associados a categorias.
- Cálculo e consulta do **orçamento mensal**, consolidando totais e saldo do período.
- Autenticação e autorização via **JWT** (OAuth2 Resource Server).
- Documentação interativa da API via **Swagger/OpenAPI**.

## Stack

- **Java 17**
- **Spring Boot 4.0.5** (Web MVC, Data JPA, Security, Validation, Mail)
- **PostgreSQL** como banco de dados
- **Flyway** para versionamento de schema
- **JWT (jjwt)** para autenticação
- **springdoc-openapi** para documentação da API
- **Lombok** para redução de boilerplate
- **Maven** como gerenciador de build

## Estrutura do projeto

```
src/main/java/com/geraldo/finan
├── config/         # Configuração de segurança e infraestrutura
├── controllers/    # Endpoints REST (Categoria, Valor, Orçamento)
├── services/       # Regras de negócio
├── repositories/   # Acesso a dados (Spring Data JPA)
├── entities/       # Entidades JPA
├── dto/            # Objetos de transferência de dados
└── utils/          # Utilitários compartilhados
```

As migrações de banco ficam em [src/main/resources/db/migration/](src/main/resources/db/migration/) e são aplicadas automaticamente pelo Flyway na inicialização.

## Como executar

### Pré-requisitos

- Java 17+
- PostgreSQL em execução
- Variáveis/propriedades de conexão configuradas em [application.properties](src/main/resources/application.properties) (ou no profile `dev` em [application-dev.properties](src/main/resources/application-dev.properties))

### Rodando em desenvolvimento

```bash
./mvnw spring-boot:run
```

### Gerando o build

```bash
./mvnw clean package
java -jar target/finan-0.0.1-SNAPSHOT.jar
```

## Documentação da API

Com a aplicação em execução, a documentação Swagger fica disponível em:

```
http://localhost:8080/swagger-ui.html
```

Exemplos de requisições também podem ser encontrados em [testes.http](testes.http).
