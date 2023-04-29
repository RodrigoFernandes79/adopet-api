<h1 align="center"> Adopet-api 🤖 </h1>
#Alura no Challenge Backend 6ª Edição

Projeto em Java realizado através da Alura no Challenge Backend 6ª Edição. Adopet API é uma REST API de uma plataforma para conectar pessoas que desejam adotar animais de estimação e abrigos.

![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)

![Badge Java](https://camo.githubusercontent.com/80db829f48ed5c5c3d48d6a3d864ff175b0e6cc6c5a12fcceaf5e14396f2bd6c/68747470733a2f2f696d672e736869656c64732e696f2f7374617469632f76313f6c6162656c3d4a617661266d6573736167653d313726636f6c6f723d6f72616e6765267374796c653d666f722d7468652d6261646765266c6f676f3d6a617661)
![Badge Spring](https://camo.githubusercontent.com/0b4c1a53e58ff8a484caf1c09d0832ddf6ad68a419d8d51611ff030f0e4f61bc/68747470733a2f2f696d672e736869656c64732e696f2f7374617469632f76313f6c6162656c3d537072696e67626f6f74266d6573736167653d76332e302e3526636f6c6f723d627269676874677265656e267374796c653d666f722d7468652d6261646765266c6f676f3d737072696e67)
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

- `Atualizar`: Atualizar Tutor através de um PUT /tutores/{ID}, onde ID é o identificador do Tutor, os novos dados do Tutor devem ser enviados no corpo da requisição.

- `Buscar por id`: Busca Tutor por ID através de um GET /tutores/{ID}, onde {ID} é o identificador do Tutor.

- `Buscar todos`: Busca de Tutores através de um GET /tutores.

- `Deletar`: Deletar Tutor através de um DELETE /tutores/{ID}, onde {ID} é o identificador do Tutor.

### 🏠Abrigo

- `Cadastrar`: Salvar Abrigo através de um POST /abrigos com as informações em um JSON no corpo da requisição.

- `Buscar todos`: Busca paginada de abrigos através de um GET /abrigos.

- `Buscar por id`: Busca Abrigo por ID através de um GET /abrigos/{ID}, onde {ID} é o identificador do Abrigo.

- `Atualizar`: Atualizar Abrigo através de um PUT /abrigos/{ID}, onde ID é o identificador do Abrigo, os novos dados do abrigo devem ser enviados no corpo da requisição.

- `Deletar`: Deletar Abrigo através de um DELETE /abrigos/{ID}, onde {ID} é o identificador do Abrigo.

