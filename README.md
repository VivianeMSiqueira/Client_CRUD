# Sistema CRUD de Clientes

Este é um sistema de CRUD de Clientes desenvolvido em Java 11, utilizando Spring Boot, JPA e banco de dados H2. Ele permite a criação, leitura, atualização e exclusão de registros de clientes e usa o formato JSON para as requisições.

## Estrutura do Projeto

O projeto está organizado de acorco com as seguintes camadas:

1. **Repository**: Responsável pela interação com o banco de dados H2 e pelas operações CRUD relacionadas aos clientes.

2. **Service**: Contém a lógica de negócios da aplicação. Realiza validações, manipula dados e coordena a comunicação entre o Repository e o Resource.

3. **Model**: Define as classes de modelo que representam os objetos de Cliente e suas propriedades.

4. **Resource**: Implementa os endpoints da API RESTful que permitem que os clientes interajam com o sistema.

## Testes

Foram desenvolvidos testes unitários em todas as camadas do sistema, utilizando os seguintes frameworks:

* Spring Test: Para testes de integração com o Spring Boot.
* JUnit 5: Para testes unitários.
* Mockito: Para criar mocks e simular comportamentos em testes.

## Exemplos de Request

### Criar um Novo Cliente

```http
POST /clientes

{
  "firstName": "Name",
  "lastName" : "Surname",
  "birthDate" : "02-02-1970",
  "email": "name@mail.com",
  "cpf": "513.128.950-55"
}
```
### Atualizar um Cliente

```http
UPDATE /clientes

{
  "firstName": "Name2",
  "lastName" : "Surname",
  "birthDate" : "02-02-1970",
  "email": "name2@mail.com",
  "cpf": "910.869.190-84"
}
```
### Buscar Todos os Clientes

```http
GET /clientes
```
### Buscar um Cliente por ID

```http
GET /clientes/{id}
```
### Excluir um Cliente por ID

```http
DELETE /clientes/{id}
```
