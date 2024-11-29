# API REST

Esta API foi construída utilizando **Java** e **Spring Boot**, com o objetivo de aprendizado e estudo sobre desenvolvimento de APIs RESTful. O sistema permite gerenciar **usuários** e seus **posts**, oferecendo funcionalidades para criar, listar, atualizar e excluir tanto usuários quanto posts, com um relacionamento onde cada post pertence a um usuário específico.

## 📚 Sobre o Projeto

O projeto tem como foco a criação de uma API RESTful para gerenciar **usuários** e seus **posts**, com recursos completos de CRUD e segurança através de criptografia de senhas.

## 🛠 Funcionalidades

### **Usuários**
- Cadastro de novos usuários com senhas criptografadas.
- Listagem de todos os usuários.
- Atualização e exclusão de usuários.

### **Posts**
- Criação de posts vinculados a um usuário existente.
- Atualização e exclusão de posts.
- Listagem de todos os posts cadastrados.

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.4.0**
  - Spring Web
  - Spring Data JPA
  - **Spring Security** (para autenticação e segurança)
- **Banco de Dados H2** (em memória para desenvolvimento)
- **BCrypt** (para criptografia de senhas)

## 🔒 Segurança

A API utiliza **BCrypt** para garantir a segurança das senhas dos usuários. A senha é armazenada de forma segura no banco de dados, proporcionando uma camada adicional de proteção.
