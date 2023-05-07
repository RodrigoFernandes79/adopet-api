<h1 align="center"> Adopet-api 🤖 </h1>
 
![Adopet_logo](https://user-images.githubusercontent.com/83513696/235794536-40d4c777-ff31-4bb5-8a23-df0f73a1fb5d.png) 

#Alura no Challenge Backend 6ª Edição

![alura_challenges](https://user-images.githubusercontent.com/83513696/235794427-a9e2c870-d132-41a4-9dd1-8122a6cf1c71.jpg)

Projeto em Java realizado através da Alura no Challenge Backend 6ª Edição. Adopet API é uma REST API de uma plataforma para conectar pessoas que desejam adotar animais de estimação e abrigos.

![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)

![Badge Java](https://camo.githubusercontent.com/80db829f48ed5c5c3d48d6a3d864ff175b0e6cc6c5a12fcceaf5e14396f2bd6c/68747470733a2f2f696d672e736869656c64732e696f2f7374617469632f76313f6c6162656c3d4a617661266d6573736167653d313726636f6c6f723d6f72616e6765267374796c653d666f722d7468652d6261646765266c6f676f3d6a617661)
![Badge Spring](https://camo.githubusercontent.com/0b4c1a53e58ff8a484caf1c09d0832ddf6ad68a419d8d51611ff030f0e4f61bc/68747470733a2f2f696d672e736869656c64732e696f2f7374617469632f76313f6c6162656c3d537072696e67626f6f74266d6573736167653d76332e302e3526636f6c6f723d627269676874677265656e267374796c653d666f722d7468652d6261646765266c6f676f3d737072696e67)
[![Docker Hub Repo](https://img.shields.io/docker/pulls/fernandesrh/api-adopet.svg)](https://hub.docker.com/repository/docker/fernandesrh/api-adopet)
## ✔️Técnicas e tecnologias utilizadas
- `☕Java 17`
- `💻Visual Studio Code`
- `📚Maven`
- `♨️Spring Boot`
- `🗃️Spring Data JPA`
- `🐘PostgreSQL`
- `🔐Spring Security`
- `🪽Flyway`
- `🌶️Lombok`
- `🧋Mockito`
- `🐋Docker`

## 💡Funcionalidades:

### 👤Tutor

- `Cadastrar`: Salvar Tutor através de um POST /tutores com as informações em um JSON no corpo da requisição.

- `Atualizar`: Atualizar Tutor através de um PATCH /tutores/{ID}, onde ID é o identificador do Tutor, os novos dados do Tutor devem ser enviados no corpo da requisição.

     * Apenas o próprio usuário Tutor pode atualizar seus dados. 
     * É necessário estar autenticado. 

- `Buscar por id`: Busca Tutor por ID através de um GET /tutores/{ID}, onde {ID} é o identificador do Tutor.

     * É necessário estar autenticado.

- `Buscar todos`: Busca de Tutores através de um GET /tutores.

     * Busca paginada de pets através de um GET /tutores.
     * É necessário estar autenticado.

- `Deletar`: Deletar Tutor através de um DELETE /tutores/{ID}, onde {ID} é o identificador do Tutor.

    * Apenas o próprio usuário Tutor pode atualizar seus dados. 
    * É necessário estar autenticado. 

### 🏠Abrigo

- `Cadastrar`: Salvar Abrigo através de um POST /abrigos com as informações em um JSON no corpo da requisição.

- `Buscar todos`: Busca paginada de abrigos através de um GET /abrigos.
    
    * Busca paginada de pets através de um GET /abrigos.
    * É necessário estar autenticado.

- `Buscar por id`: Busca Abrigo por ID através de um GET /abrigos/{ID}, onde {ID} é o identificador do Abrigo.

    * É necessário estar autenticado.

- `Atualizar`: Atualizar Abrigo através de um PATCH /abrigos/{ID}, onde ID é o identificador do Abrigo, os novos dados do abrigo devem ser enviados no corpo da requisição.

    * Apenas o próprio usuário Abrigo pode atualizar seus dados.
    * É necessário estar autenticado.

- `Deletar`: Deletar Abrigo através de um DELETE /abrigos/{ID}, onde {ID} é o identificador do Abrigo.

    * Apenas o próprio usuário Abrigo pode atualizar seus dados.
    * É necessário estar autenticado.
     
 ### 🐱Pet
 
- `Cadastrar`: Salvar Pet através de um POST /pets com as informações em um JSON no corpo da requisição.

    * Apenas Abrigos podem cadastrar Pets.
    * É necessário estar autenticado.
    
- `Buscar todos`: Busca paginada de Pets através de um GET /pets.
    
    * Busca paginada de pets através de um GET /abrigos.
    * Somente Pets não adotados aparecem na busca paginada.
    * É necessário estar autenticado.
     
- `Buscar por id`: Busca Pet por ID através de um GET /pets/{ID}, onde {ID} é o identificador do Pet.

    * É necessário estar autenticado.
    
- `Atualizar`: Atualizar Pet através de um PATCH /pets/{ID}, onde ID é o identificador do Pet, os novos dados devem ser enviados no corpo da requisição.
    
    * Apenas o Abrigo que cadastrou o Pet pode atualizá-lo.
     
- `Deletar`: Deletar Pet através de um DELETE /pets/{ID}, onde {ID} é o identificador do Pet.

    * Apenas o Abrigo que cadastrou o Pet pode deleta-lo.
    * Pet relacionado a uma Adoção não pode ser deletado.
    
😻 Adoção

- `Adotar`: Solicitar uma adoção de um Pet através de um POST /adocao . 
   
    * É necessário estar autenticado. 
    * Apenas Tutor pode solicitar uma adoção.
    * Apenas usuários do tipo Tutor podem solicitar uma adoção.
    * Apenas Pets não adotados podem receber uma solicitação de adoção.

- `Deletar`: Deletar uma adoção através de um DELETE /adocao/{ID}, onde {ID} é o identificador do Pet.

    * Uma adoção só pode ser deletada pelo Abrigo relacionado na adoção.
    * Ao cancelar a adoção, o Pet fica como false no banco de dados.
