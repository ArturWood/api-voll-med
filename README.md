<img src="https://github.com/ArturWood/games-list/assets/111249818/434c56b3-9dc9-412a-91f7-2edc3f389c14" width=300px alt="Java Logo" />
<img src="https://github.com/ArturWood/games-list/assets/111249818/d8539fd2-938e-4126-b3d4-7236a1ffdbef" width=500px alt="SpringFramework Logo" />

# Voll Med - Aplicação Java para realizar a gestão de médicos, pacientes e consultas

### Projeto back-end desenvolvido utilizando Java 17 com Spring Framework

API REST responsavel por realizar operações de buscas e escritas em banco de dados MySQL e retornar os dados em JSON.

![MySql](https://github.com/ArturWood/api-voll-med/assets/111249818/6f568616-c9c9-4e4a-99f1-6f505d586faa)

Ela oferece endpoints para o cadastro, listagem, atualização e exclusão de médicos, pacientes e consultas.

![EndpointsPostman](https://github.com/ArturWood/api-voll-med/assets/111249818/8385e596-6c94-4c50-b504-46e587db23d7)
![Postman](https://github.com/ArturWood/api-voll-med/assets/111249818/444e1910-f993-4964-a70a-0e3a0a436ae6)

## Pré-requisitos

- Java Development Kit (JDK) versão 17
- IDE Java (como Eclipse ou IntelliJ) ou um editor de texto para escrever o código
- Fazer download das dependências e plugins utilizando maven
- MySQL instalado localmente ou container Docker com MySQL
- Postman (opcional, para testar os endpoints localmente)

## Como executar o projeto

### Back-end

```bash
# clonar repositório
git clone https://github.com/ArturWood/api-voll-med.git

# entrar na pasta do projeto
cd api-voll-med

# executar o projeto
./mvnw spring-boot:run
```

## Endpoints

A aplicação expõe os seguintes endpoints:

- Médicos `/medicos`
  - `GET`: Retorna uma lista paginada dos médicos cadastrados.
  - `POST`: Cadastra um novo médico com os detalhes fornecidos no corpo da solicitação.
  - `PUT`: Atualiza as informações de um médico existente com base nos detalhes fornecidos no corpo da solicitação.
  - `DELETE /{id}`: Realiza a exclusão lógica (inativação) do médico com o ID fornecido.
  - `GET /{id}`: Retorna os detalhes do médico específico com o ID fornecido.

- Pacientes `/pacientes`
  - `GET`: Retorna uma lista paginada dos pacientes cadastrados.
  - `POST`: Cadastra um novo paciente com os detalhes fornecidos no corpo da solicitação.
  - `PUT`: Atualiza as informações de um paciente existente com base nos detalhes fornecidos no corpo da solicitação.
  - `DELETE /{id}`: Realiza a exclusão lógica (inativação) do paciente com o ID fornecido.
  - `GET /{id}`: Retorna os detalhes do paciente específico com o ID fornecido.

- Consultas `/consultas`
  - `POST`: Agenda uma nova consulta com os detalhes fornecidos no corpo da solicitação.
  - `DELETE /{id}`: Cancela uma consulta com base nos detalhes fornecidos no corpo da solicitação.
  - `GET /{id}`: Retorna os detalhes de uma consulta específica com o ID fornecido.

## Estrutura do Projeto

O projeto possui a seguinte estrutura de arquivos:

```bash
├───src                                          
│   ├───main                                     
│   │   ├───java                                 
│   │   │   └───com                              
│   │   │       └───dev
│   │   │           └───api.pass.in
│   │   │               ├───controller
│   │   │               ├───dto
│   │   │               ├───model
│   │   │               ├───infra
│   │   │               ├───repository
│   │   │               └───service
│   │   └───resources
│   │       ├───static
│   │       └───templates
└── .gitignore
└── api-voll-med.postman_collection.json
└── pom.xml
```

- O pacote `resources` contém o arquivo `application.properties` que configura o ambiente da aplicação, e a conexão no banco de dados.
- O pacote `controller` contém as classes que definem os endpoints da API.
- O pacote `service` contém as classes responsáveis por acesso ao BD e chamar a lógica do negócio.
- O pacote `infra` contém a classe `ExceptionEntityHandler` responsavel por lidar com as exceptions lançadas pelo controller ou service.
- O pacote `model` contém as classes que representam um medico ou paciente e seu mapeamento no BD, assim como as validações para aplicar as regras de negócio.
- O pacote `repository` contém as interfaces que definem operações de acesso a dados para as entidades.
- Na source do projeto temos o arquivo `.gitignore` que especifica os arquivos e pastas que devem ser ignorados pelo controle de versão do Git
- O arquivo `api-voll-med.postman_collection` para consultar e testar os endpoints na API.
- O arquivo `pom.xml` para download das dependencias necessarias para o projeto usando maven.

## Documentação

No projeto foi utilizado uma imagem MySQL em um Docker container para desenvolvimento;<br>
Foi adicionado a dependência `springdoc` para facilitar a documentação e visualização dos endpoints (acessar rodando localmente);<br>
Alem das dependencias para desenvolvimento com Spring Framework - Web, Bean, JPA;<br>
Links para uso e documentação:

https://hub.docker.com/_/mysql<br>
https://dev.mysql.com/doc/<br>
https://spring.io/projects/spring-data-jpa<br>
https://docs.spring.io/spring-boot/docs/current/reference/html/web.html<br>
http://localhost:8080/swagger-ui/index.html

![swagger](https://github.com/ArturWood/api-voll-med/assets/111249818/d4b6bf85-79c4-499a-886c-e5ee2b309f45)
