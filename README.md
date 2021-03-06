[![Build Status](https://app.travis-ci.com/gilvano/votosapi.svg?branch=main)](https://app.travis-ci.com/gilvano/votosapi)
[![codecov](https://codecov.io/gh/gilvano/votosapi/branch/main/graph/badge.svg?token=I2E0M6IN99)](https://codecov.io/gh/gilvano/votosapi)

## API para votação de pautas
API REST para votação de pautas por associados.

- Cadastro de Associado
- Cadastro de Pautas
- Abertura de Sessão de Votação por Pauta
- Voto dos Associados na Sessão
- Consulta dos resultados por Sessão


### Tecnologias utilizadas
- Java 11
- Spring Boot Web, Data JPA, Test, Validation, Amqp
- JUnit
- Mockito
- Swagger(OpenAPi 3)
- Postgresql
- Docker
- Lombok
- RabbitMQ

Utilizei Spring Boot pela praticidade que o mesmo proporciona para um desenvolvimento rápido de pequenas aplicações, por ter uma grande quantidade de componentes que facilitam a utilização de outras tecnologias, por exempolo o Spring Boot Starter Amqp que facilita a integraçã com o RabbitMQ. Lombok utlizei por acretitar que facilita o desenvolvimento, evitando a criação de construtores, getter e setters. JUnit e Mockito utilizei para alguns testes unitários. Swagger para documentação e testes da API. Postgresql escolhi por já estar acostumado com o mesmo e ser um bom banco de dados opensource. Docker foi utilizado para facilitar a criação dos serviços de banco de dados e mensageria. RabbiMQ também foi uma escolha por já estar usando o mesmo.


### Execução do projeto

Clonar o projeto
  
```git clone https://github.com/gilvano/votosapi.git```

Navegar até a pasta /votosapi/docker e executar o comando para subir os serviços do Postgresql e do RabbitMQ

```docker compose up -d ```

Voltar para a pasta votosapi e subir a aplicação

```./mvnw spring-boot:run```

Acessar a url com a documentação da API

 [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Acesso na web
[app-votosapi.herokuapp.com/swagger-ui.html](https://app-votosapi.herokuapp.com/swagger-ui.html)

### Utilização da API

#### Cadastro de Associado

POST - http://localhost:8080/api/v1/associado

#### Cadastro de Pauta

POST - http://localhost:8080/api/v1/pauta

#### Cadastro de Sessão de Votação

POST - http://localhost:8080/api/v1/sessaovotacao

#### Cadastro de Voto

POST - http://localhost:8080/api/v1/voto


#### Resultado da Sessão 

GET - http://localhost:8080/api​/v1​/sessaovotacao​/{id}​/resultado


### Tarefas Bônus
#### Tarefa Bônus 1 - Integração com sistemas externos
Implementada integração com api que verifica se o associado está apto para votar.

#### Tarefa Bônus 2 - Mensageria e filas
Implementado uma tarefa para enviar as sessões finalizadas para um serviço de mensageria.

#### Tarefa Bônus 3 - Performance
Configurado Spring Boot Actuator e o Spring Boot Admin Client para monitoramento da API, que pode está acessível no link http://localhost:8080/actuator e pode ser lido por uma instancia do Spring Boot Admin Server(https://github.com/codecentric/spring-boot-admin)

#### Tarefa Bônus 4 - Versionamento da API
Versionamento da api foi feito com a implementação dos controllers na pasta /api/v1
