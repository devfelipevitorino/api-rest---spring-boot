# 📚 API RESTful com Java e Spring Boot  

Esta API foi desenvolvida com o objetivo de aprendizado e estudo sobre o desenvolvimento de APIs RESTful, permitindo o gerenciamento de usuários e posts. O sistema oferece funcionalidades de CRUD completo com segurança integrada.
## 🚀 Sobre o Projeto  
O projeto foca na criação de uma API RESTful para gerenciar usuários e seus posts, onde cada post pertence a um usuário específico. Agora, a API também utiliza **Swagger** para documentação e teste interativo dos endpoints.  

## 🛠️ Funcionalidades  

### Usuários  
- 📥 **Cadastro**: Criação de novos usuários com senhas criptografadas usando BCrypt.  
- 📄 **Listagem**: Recuperação de todos os usuários cadastrados.  
- ✏️ **Atualização**: Modificação dos dados de usuários existentes.  
- 🗑️ **Exclusão**: Remoção de usuários.  

### Posts  
- 📝 **Criação**: Posts vinculados a um usuário existente.  
- 📄 **Listagem**: Recuperação de todos os posts cadastrados.  
- ✏️ **Atualização**: Modificação de posts existentes.  
- 🗑️ **Exclusão**: Remoção de posts.  

## 📑 Documentação com Swagger  
A API inclui documentação interativa com **Swagger** para facilitar a visualização e teste dos endpoints.  
- Acesse a interface do Swagger em: `/swagger-ui.html`  

## 🧰 Tecnologias Utilizadas  
- **Java 21**  
- **Spring Boot 3.4.0**  
- **Spring Web**  
- **Spring Data JPA**  
- **Banco de Dados H2** (em memória para desenvolvimento)  
- **Swagger** (para documentação e testes interativos da API)  
