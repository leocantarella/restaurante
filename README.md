Restaurante - API em Spring Boot

 ![Java](https://img.shields.io/badge/Java-17+-blue)
 ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0-brightgreen)
 ![H2 Database](https://img.shields.io/badge/H2-Database-orange)
 ![License](https://img.shields.io/badge/License-MIT-green)

API simples de gerenciamento de Itens e Pedidos para um restaurante, utilizando Spring Boot, JPA e H2 em memória.

Funcionalidades:
- CRUD de Itens
- CRUD de Pedidos
- Suporte a JSON para requisições e respostas
- Banco H2 em memória com console web
- Testes via Postman ou curl

Tecnologias:
- Java 17+
- Spring Boot 3+
- Spring Data JPA
- H2 Database
- Lombok (opcional)
- Maven (ou Gradle)

Como rodar:
1. Clone o projeto: git clone https://github.com/seuusuario/restaurante.git
2. Entre na pasta do projeto: cd restaurante
3. Rode com Maven: mvn spring-boot:run
4. Acesse o H2 Console (opcional): http://localhost:8080/h2-console
   - JDBC URL: jdbc:h2:mem:restauranteDB
   - User: sa
   - Password: (vazio)
   > Para persistir os dados entre reinícios, altere a URL do datasource para: spring.datasource.url=jdbc:h2:file:./data/restauranteDB

Endpoints - Itens:
- GET /itens - Listar todos os itens
- GET /itens/{id} - Buscar item por ID
- POST /itens - Criar item (exemplo JSON: { "nome": "Pizza", "tipo": "Comida" })
- PUT /itens/{id} - Atualizar item (exemplo JSON: { "nome": "Pizza Grande", "tipo": "Comida Italiana" })
- DELETE /itens/{id} - Deletar item

Endpoints - Pedidos:
- GET /pedidos - Listar todos os pedidos
- GET /pedidos/{id} - Buscar pedido por ID
- POST /pedidos - Criar pedido (exemplo JSON: { "mesa": 5, "quantidade": 2, "item": { "id": 1 } })
- PUT /pedidos/{id} - Atualizar pedido (exemplo JSON: { "mesa": 7, "quantidade": 3, "item": { "id": 1 } })
- DELETE /pedidos/{id} - Deletar pedido

Testando a API:
- Usando Postman: configure Content-Type: application/json para POST e PUT, e teste endpoints.
- Usando curl:
  - Criar Item: curl -X POST http://localhost:8080/itens -H "Content-Type: application/json" -d '{"nome":"Pizza","tipo":"Comida"}'
  - Criar Pedido: curl -X POST http://localhost:8080/pedidos -H "Content-Type: application/json" -d '{"mesa":5,"quantidade":2,"item":{"id":1}}'
  - Atualizar Item: curl -X PUT http://localhost:8080/itens/1 -H "Content-Type: application/json" -d '{"nome":"Pizza Grande","tipo":"Comida Italiana"}'
  - Buscar Pedido: curl -X GET http://localhost:8080/pedidos/1
  - Deletar Pedido: curl -X DELETE http://localhost:8080/pedidos/1

Observações:
- Todos os endpoints retornam e aceitam JSON.
- O banco H2 é em memória por padrão, então os dados se perdem ao reiniciar.
- Recomenda-se testar com Postman ou curl.
