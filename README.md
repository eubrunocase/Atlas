# Documentação do Sistema Atlas - Sistema de Controle de Projetos

## 1. Visão Geral

O Atlas é um sistema de controle de projetos desenvolvido para gerenciar operações de cadastramento e solicitação de projetos por professores para uma fábrica de software. O sistema permite que professores solicitem projetos, enquanto administradores gerenciam grupos e acompanham o ciclo de vida dos projetos.

## 2. Arquitetura

### 2.1 Tecnologias Utilizadas

**Front-End:**
- Angular
- HTML, CSS e JavaScript

**Back-End:**
- Java com Spring Framework
- Spring Security para autenticação e autorização
- JWT (JSON Web Token) para gestão de sessões

**Banco de Dados:**
- PostgreSQL

### 2.2 Estrutura do Projeto

O projeto segue uma arquitetura MVC (Model-View-Controller) com comunicação entre Front-End e Back-End via HTTP. Todo o processamento de negócio é realizado no Back-End, enquanto o Front-End apenas busca ou envia informações para exibição ao usuário.

#### Principais Componentes:

- **Controllers**: Gerenciam as requisições HTTP
- **Services**: Contêm a lógica de negócio e implementam padrão de herança com BaseService
- **Models**: Representam as entidades do sistema
- **Repositories**: Realizam a persistência de dados via JpaRepository
- **DTOs**: Objetos de transferência de dados para operações específicas
- **Security**: Implementa autenticação e autorização com JWT

## 3. Requisitos Funcionais

### 3.1 Controle de Projetos

#### Para Administradores:
- Cadastrar professores e alunos nos grupos da fábrica de software
- Atualizar informações dos grupos, incluindo professores coordenadores
- Cadastrar professores habilitados a solicitar projetos
- Alterar ou excluir solicitações validadas
- Gerenciar status dos projetos

#### Para Professores:
- Solicitar projetos informando nome, objetivo, data de início, escopo e público-alvo
- Acompanhar status das solicitações
- Confirmar recebimento do projeto após finalização
- Encaminhar dúvidas via Classroom

### 3.2 Fluxo de Solicitação

1. Solicitação inicial recebe status "aguardando análise preliminar"
2. Professor recebe feedback sobre sucesso ou falha da solicitação
3. Status pode mudar para "em análise", "projeto recusado", "em andamento" ou "finalizado"
4. Após término, professor confirma recebimento
5. Projeto é atualizado quando houver mudanças

### 3.3 Integração com Repositórios Git

- Repositórios disponibilizados pela equipe no Git UCSAL

## 4. Requisitos Não Funcionais

- Comunicação entre Front-End e Back-End via HTTP
- Centralização da lógica de negócio no Back-End
- Segurança baseada em tokens JWT com expiração de 2 horas
- Autenticação com senhas criptografadas usando BCrypt
- Restrições de acesso baseadas em perfis (ADMINISTRADOR/PROFESSOR)

## 5. Estrutura do Código

### 5.1 Modelos de Dados (Entities)

#### Users (Classe Abstrata)
- Implementa UserDetails do Spring Security
- Atributos: id, login, password, role
- Classes derivadas: Administrador, Professor

#### Project
- Atributos: id, name, objetivo, dataInicio, escopo, publicoAlvo, status
- Enum de Status: AGUARDANDO_ANALISE_PRELIMINAR, EM_ANALISE, PROJETO_RECUSADO, EM_ANDAMENTO, FINALIZADO

### 5.2 Controllers

#### AdmController
- Endpoints para gerenciamento de administradores (CRUD)
- Base URL: `/atlas/adm`
- Métodos:
  - `POST /atlas/adm` - Criar administrador
  - `GET /atlas/adm` - Listar todos os administradores
  - `DELETE /atlas/adm/{id}` - Remover administrador
  - `PUT /atlas/adm/{id}` - Atualizar administrador

#### ProfessorController
- Endpoints para gerenciamento de professores (CRUD)
- Base URL: `/atlas/professor`
- Métodos:
  - `POST /atlas/professor` - Criar professor
  - `GET /atlas/professor` - Listar todos os professores
  - `DELETE /atlas/professor/{id}` - Remover professor
  - `DELETE /atlas/professor` - Remover todos os professores
  - `PUT /atlas/professor/{id}` - Atualizar professor

#### ProjectController
- Endpoints para gerenciamento de projetos (CRUD)
- Base URL: `/atlas/project`
- Métodos:
  - `POST /atlas/project` - Criar projeto
  - `GET /atlas/project` - Listar todos os projetos
  - `DELETE /atlas/project/{id}` - Remover projeto
  - `PUT /atlas/project/{id}` - Atualizar projeto

#### AuthenticationController
- Endpoints para autenticação e registro
- Base URL: `/atlas/auth`
- Métodos:
  - `POST /atlas/auth/login` - Autenticar usuário (professor ou administrador)
  - `POST /atlas/auth/register/professor` - Registrar novo professor
  - `POST /atlas/auth/register/adm` - Registrar novo administrador (requer privilégios de administrador)

### 5.3 Services

#### BaseService<T>
- Classe abstrata genérica que implementa operações CRUD básicas
- Métodos:
  - `findAll()` - Recupera todos os registros
  - `save(T entity)` - Salva uma entidade
  - `update(Long id, T entity)` - Atualiza uma entidade
  - `delete(Long id)` - Remove uma entidade por ID

#### AdmService
- Estende BaseService<Administrador>
- Sobrescreve o método `save()` para criptografar senhas antes de salvar

#### ProfessorService
- Estende BaseService<Professor>
- Sobrescreve o método `save()` para criptografar senhas antes de salvar
- Método adicional `deleteAll()` para remover todos os professores

#### ProjectService
- Estende BaseService<Project>
- Implementa operações CRUD para projetos

#### CryptPasswordService
- Serviço para criptografia de senhas usando BCrypt
- Métodos:
  - `encrypt(String password)` - Criptografa uma senha
  - `matches(String password, String encodedPassword)` - Verifica se uma senha corresponde à versão criptografada

#### Serviços de Autenticação
- **CustomUserDetailsService**: Implementação primária de UserDetailsService
- **AuthorizationServiceAdm**: UserDetailsService específico para administradores
- **AuthorizationServiceProfessor**: UserDetailsService específico para professores

### 5.4 Segurança

#### SecurityConfigurations
- Configuração do Spring Security
- Definição de permissões por endpoint
- Configuração da autenticação e autorização

#### securityFilter
- Filtro de segurança para validação de tokens JWT
- Recuperação de usuários baseada no token

#### TokenService
- Geração e validação de tokens JWT
- Configuração de expiração de token (2 horas)

## 6. DTOs (Data Transfer Objects)

- **AuthenticationDTO**: Armazena credenciais de login (login e password)
- **LoginResponseDTO**: Retorna token de acesso após autenticação bem-sucedida
- **RegisterDTO**: Contém dados para registro de usuário (login, password, role)

## 7. Fluxos de Autenticação

### 7.1 Login
1. Cliente envia credenciais (login/senha) como AuthenticationDTO para `/atlas/auth/login`
2. AuthenticationController valida credenciais usando AuthenticationManager
3. Se válidas, TokenService gera um token JWT
4. Token é retornado ao cliente como LoginResponseDTO
5. Cliente utiliza token para requisições autenticadas

### 7.2 Registro
1. Registro de professores: `/atlas/auth/register/professor` (acesso público)
   - Cria um novo Professor com senha criptografada
2. Registro de administradores: `/atlas/auth/register/adm` (apenas para administradores)
   - Cria um novo Administrador com senha criptografada
   - Requer autenticação com role ADMINISTRADOR

## 8. Controle de Acesso

### 8.1 Perfis de Usuário

#### ADMINISTRADOR
- Acesso total ao sistema
- Gerenciamento de usuários e projetos
- Alteração de status de projetos

#### PROFESSOR
- Solicitação de projetos
- Visualização de status
- Confirmação de recebimento de projetos

### 8.2 Permissões por Endpoint

- **Professor**:
  - GET `/atlas/professor` - Acesso permitido para visualizar professores
  - GET `/atlas/project` - Acesso permitido para visualizar projetos
  - POST `/atlas/project` - Acesso permitido para criar projetos

- **Administrador**:
  - Acesso total a todos os endpoints
  - Exclusivo acesso a endpoints de administração:
    - Todos os endpoints `/atlas/adm`
    - DELETE e PUT em `/atlas/professor`
    - DELETE e PUT em `/atlas/project`
    - POST `/atlas/auth/register/adm`

## 9. Processo de Validação de Token

1. Cliente envia requisição com token JWT no header Authorization
2. O filtro securityFilter intercepta a requisição
3. O TokenService valida o token e extrai o login do usuário
4. O sistema busca o usuário nos repositórios (AdmRepository ou ProfessorRepository)
5. Se encontrado, cria uma authentication e configura no SecurityContextHolder
6. A requisição continua seu fluxo normal se o token for válido

## 10. Instalação e Configuração

### 10.1 Requisitos do Sistema
- Java 17 ou superior
- PostgreSQL
- Maven ou Gradle para gerenciamento de dependências
- Node.js (para o Front-End)

### 10.2 Configuração do Back-End
1. Clone o repositório do projeto
2. Configure o banco de dados PostgreSQL
3. Configure as seguintes propriedades no arquivo `application.properties` ou `application.yml`:
   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/atlas
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   api.security.token.secret=your_jwt_secret_key
   ```
4. Execute a aplicação Spring Boot através da classe `AtlasApplication`

### 10.3 Integração com Front-End
- Configurar as URLs do Back-End no cliente Front-End
- Implementar armazenamento e envio de tokens de autenticação
- Criar interfaces para as operações CRUD de projetos e usuários

## 11. API Reference

### Autenticação
```
POST /atlas/auth/login - Login de usuário
POST /atlas/auth/register/professor - Registro de professor
POST /atlas/auth/register/adm - Registro de administrador (apenas admin)
```

### Professores
```
GET /atlas/professor - Listar todos os professores
POST /atlas/professor - Criar professor (apenas admin)
PUT /atlas/professor/{id} - Atualizar professor (apenas admin)
DELETE /atlas/professor/{id} - Excluir professor (apenas admin)
DELETE /atlas/professor - Excluir todos os professores (apenas admin)
```

### Administradores
```
GET /atlas/adm - Listar todos os administradores (apenas admin)
POST /atlas/adm - Criar administrador (apenas admin)
PUT /atlas/adm/{id} - Atualizar administrador (apenas admin)
DELETE /atlas/adm/{id} - Excluir administrador (apenas admin)
```

### Projetos
```
GET /atlas/project - Listar todos os projetos
POST /atlas/project - Criar projeto
PUT /atlas/project/{id} - Atualizar projeto (apenas admin)
DELETE /atlas/project/{id} - Excluir projeto (apenas admin)
```

## 12. Diagrama de Classes

O sistema Atlas é organizado em torno das seguintes classes principais:

1. **Entidades**
   - `Users` (classe abstrata)
     - `Administrador`
     - `Professor`
   - `Project`

2. **Enums**
   - `UserRoles` (ADMINISTRADOR, PROFESSOR)
   - `ProjectStatus` (AGUARDANDO_ANALISE_PRELIMINAR, EM_ANALISE, PROJETO_RECUSADO, EM_ANDAMENTO, FINALIZADO)

3. **Services**
   - `BaseService<T>` (abstrato)
     - `AdmService`
     - `ProfessorService`
     - `ProjectService`
   - `CryptPasswordService`
   - `CustomUserDetailsService`
   - `AuthorizationServiceAdm`
   - `AuthorizationServiceProfessor`

4. **Controllers**
   - `AdmController`
   - `ProfessorController`
   - `ProjectController`
   - `AuthenticationController`

5. **Security**
   - `SecurityConfigurations`
   - `securityFilter`
   - `TokenService`

## 13. Considerações Finais

O Sistema Atlas foi projetado para facilitar a gestão de projetos em uma fábrica de software acadêmica, permitindo que professores solicitem projetos e administradores gerenciem todo o processo. A arquitetura baseada em Spring Boot com segurança JWT proporciona um ambiente seguro e escalável para o desenvolvimento de projetos.

A implementação segue boas práticas de desenvolvimento, incluindo:
- Uso de padrão de herança para serviços (BaseService)
- Separação clara de responsabilidades (MVC)
- Criptografia adequada de senhas
- Autenticação e autorização baseadas em tokens JWT
- Controle de acesso granular por endpoint e perfil de usuário
