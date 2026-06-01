# Sistema de Telemedicina para Clínicas Populares

Projeto desenvolvido para a disciplina de Orientação a Objetos, com foco na aplicação de conceitos modernos de Engenharia de Software, Domain-Driven Design (DDD), Test-Driven Development (TDD) e Integração Contínua (CI/CD).

---

## Objetivo do Projeto

O sistema tem como objetivo auxiliar clínicas populares no gerenciamento de:

* Agendamento de consultas
* Atendimento presencial e online
* Prontuários eletrônicos
* Cadastro de pacientes
* Receitas digitais
* Controle básico de faturamento

O projeto busca manter uma arquitetura organizada, segura e escalável, separando corretamente as responsabilidades de cada camada da aplicação.

---

## Tecnologias Utilizadas

* Java 21
* Maven
* JUnit 5
* GitHub Actions
* IntelliJ IDEA

---

## Arquitetura do Projeto

O sistema foi estruturado seguindo conceitos de DDD (Domain-Driven Design).

### Estrutura de Pastas

```plaintext
src/
├── domain/
├── application/
├── infrastructure/
└── presentation/
```

### Camadas

#### Domain

Contém:

* Entidades
* Value Objects
* Regras de negócio
* Aggregates

#### Application

Responsável pelos casos de uso e serviços da aplicação.

#### Infrastructure

Camada responsável por persistência, repositórios e integrações externas.

#### Presentation

Responsável pela interface da aplicação (CLI inicialmente).

---

## Conceitos Aplicados

### Orientação a Objetos

* Encapsulamento
* Composição
* Alta coesão
* Separação de responsabilidades

### DDD

* Entities
* Value Objects
* Aggregate Roots
* Bounded Contexts

### TDD

Os testes unitários foram desenvolvidos utilizando JUnit 5 para validar as regras de domínio da aplicação.

### CI/CD

O projeto utiliza GitHub Actions para:

* Build automático
* Execução de testes
* Validação da aplicação a cada push

---

## Como Executar o Projeto

### Clonar o repositório

```bash
git clone https://github.com/Ju1ninh0/telemedicina
```

### Entrar na pasta do projeto

```bash
cd telemedicina
```

### Rodar os testes

```bash
mvn test
```

### Executar aplicação

```bash
mvn compile
```

---

## Estrutura Atual do Sistema

Atualmente o projeto possui:

* Estrutura arquitetural organizada
* Configuração Maven
* Pipeline CI/CD
* Testes unitários iniciais
* Modelagem inicial do domínio

---

## Status do Projeto

🚧 Em desenvolvimento
