# Projeto Academia - API REST com Versionamento

Este projeto implementa uma **API REST** para o gerenciamento de uma academia, incluindo controle de **alunos**, **planos**, **treinos**, **exercícios** e **pagamentos**.  
O sistema foi desenvolvido em **Spring Boot** com versionamento de APIs e documentação via **Swagger**.  

Tecnologias utilizadas:
- Java 21
- Spring Boot
- Spring Data JPA / Hibernate
- Banco de dados H2 (ou outro relacional)
- Swagger/OpenAPI para documentação dos endpoints

---

## Instruções para rodar o projeto

Recomendação de ordem para criação:
1. Treino
2. Exercicio
3. Plano
4. Aluno
5. Pagamento

Exercicio pertence a Treino  
Treino pertence a Aluno  
Plano pertence a Aluno  
Pagamento pertence a Aluno  
  
OBS: Não possui validações personalizadas

---

## Endpoints da API

### Alunos

#### v1

| Método | Endpoint                  | Descrição               |
| :----: | :------------------------ | :---------------------- |
|   GET  | /v1/alunos/listarTodos    | Lista todos os alunos   |
|   GET  | /v1/alunos/buscar/{id}    | Busca aluno por ID      |
|  POST  | /v1/alunos/adicionar      | Adiciona um novo aluno  |
|   PUT  | /v1/alunos/atualizar/{id} | Atualiza dados do aluno |
| DELETE | /v1/alunos/deletar/{id}   | Deleta aluno            |

#### v1.1

| Método | Endpoint                   | Descrição                  |
| :----: | :------------------------- | :------------------------- |
|   PUT  | /v1.1/alunos/reativar/{id} | Reativa um aluno inativado |
|   PUT  | /v1.1/alunos/inativar/{id} | Inativa um aluno           |

---

### Exercícios

| Método | Endpoint                      | Descrição                                    |
| :----: | :---------------------------- | :------------------------------------------- |
|   GET  | /v1/exercicios/listarTodos    | Lista todos os exercícios                    |
|  POST  | /v1/exercicios/adicionar      | Cria um novo exercício vinculado a um treino |
|   PUT  | /v1/exercicios/atualizar/{id} | Atualiza um exercício existente              |
| DELETE | /v1/exercicios/deletar/{id}   | Deleta um exercício                          |

---

### Pagamentos

#### v1

| Método | Endpoint                   | Descrição                      |
| :----: | :------------------------- | :----------------------------- |
|   GET  | /v1/pagamentos/listarTodos | Lista todos os pagamentos      |
|   GET  | /v1/pagamentos/buscar/{id} | Busca pagamento por ID         |
|   PUT  | /v1/pagamentos/pagar/{id}  | Marca pagamento como realizado |

#### v1.1

| Método | Endpoint                          | Descrição                               |
| :----: | :-------------------------------- | :-------------------------------------- |
|   GET  | /v1.1/pagamentos/listarPendentes  | Lista pagamentos pendentes              |
|   GET  | /v1.1/pagamentos/listarAluno/{id} | Lista pagamentos de um aluno específico |

---

### Planos

| Método | Endpoint                  | Descrição                   |
| :----: | :------------------------ | :-------------------------- |
|   GET  | /v1/planos/listarTodos    | Lista todos os planos       |
|   GET  | /v1/planos/buscar/{id}    | Busca plano por ID          |
|  POST  | /v1/planos/adicionar      | Cria um novo plano          |
|   PUT  | /v1/planos/atualizar/{id} | Atualiza um plano existente |
| DELETE | /v1/planos/deletar/{id}   | Deleta um plano             |

---

### Treinos

| Método | Endpoint                   | Descrição                    |
| :----: | :------------------------- | :--------------------------- |
|   GET  | /v1/treinos/listarTodos    | Lista todos os treinos       |
|   GET  | /v1/treinos/buscar/{id}    | Busca treino por ID          |
|  POST  | /v1/treinos/adicionar      | Cria um novo treino          |
|   PUT  | /v1/treinos/atualizar/{id} | Atualiza um treino existente |
| DELETE | /v1/treinos/deletar/{id}   | Deleta um treino             |