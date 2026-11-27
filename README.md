# API REST de Veículos — Spring Boot

**Aluno:** Samuel Araujo · **RA:** 210025640 · **Curso:** Engenharia da Computação
**Instituição:** UNISAL — Campus Campinas, Unidade São José

## Sobre

API REST (Java 17, Spring Boot, Spring Data JPA, Maven) para cadastrar,
consultar, atualizar e excluir veículos. Por padrão usa o banco H2 (arquivo
`./data/carrosdb`), então não precisa instalar nada. Para usar MySQL, basta
trocar as linhas indicadas no `application.properties`.

## Como executar

    mvn spring-boot:run

A API sobe em `http://localhost:8080`.

## Endpoints

- `POST /carros` — cadastrar veículo
- `GET /carros` — listar todos
- `GET /carros/{id}` — consultar por ID
- `PUT /carros/{id}` — atualizar
- `DELETE /carros/{id}` — excluir

Exemplo de cadastro (`Content-Type: application/json`):

    {
      "marca": "Volkswagen",
      "modelo": "Golf",
      "anoFabricacao": 2020,
      "anoModelo": 2021,
      "motorizacao": "1.4 TSI"
    }
