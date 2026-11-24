# API de Cursos

Projeto único que evolui pelas semanas 1–8: entidade JPA `Curso`, camadas Controller/Service/Repository, CRUD com DTO, validação e erros JSON, paginação/filtros, JWT e RBAC, Swagger/OpenAPI e coleção Postman.

## Configuração local
Java 17+, Maven e PostgreSQL. Crie o banco `cursos`, depois configure `DATABASE_URL`, `DATABASE_USER`, `DATABASE_PASSWORD` e `JWT_SECRET` (segredo HMAC com 32+ caracteres). `APP_STUDENT_NAME` e `APP_STUDENT_RA` alimentam o endpoint de desafio sem gravar dados pessoais no repositório. Nunca use valores de produção em arquivo versionado.

`mvn spring-boot:run`; Swagger local em `/swagger-ui/index.html`. DDL é atualizado pelo Hibernate para ambiente didático; produção deve usar migrações versionadas.

## Rotas
- `GET /api/cursos?page=0&limit=20&sort=asc&minPrice=0&maxPrice=500` — limite máximo 100, retorna `data`, `meta` e links.
- `GET /api/cursos/{id}`, `POST /api/cursos`, `PUT /api/cursos/{id}`, `DELETE /api/cursos/{id}`.
- `POST /api/auth/register` cria perfil USER com BCrypt; `POST /api/auth/login` devolve JWT.
- Exclusão exige `ROLE_ADMIN`; usuários autenticados comuns recebem 403. Para o exercício, crie uma conta ADMIN via console administrativo seguro fora da rota pública antes de executar o cenário; não existe cadastro público de privilégios.
- `/api/desafio/ra` monta a frase a partir de variáveis de ambiente. Preencha o RA real antes da demonstração.

## Erros e segurança
Erros planejados usam JSON padronizado para 400, 404, 409 e erros inesperados sem stack trace. JWT é stateless e expira; senha tem hash BCrypt. `JWT_SECRET` vazio faz a aplicação falhar ao iniciar. Para produção acrescente rotação de chaves, rate limiting, migrations, logs correlacionados, métricas, CORS restrito e gestão segura de usuário administrador.

## Entregas semanais
1. `Curso` JPA com descrição/turno e configuração PostgreSQL.
2. Arquitetura Controller–Service–Repository e endpoints REST.
3. CRUD com DTOs, `Optional` por `orElseThrow`, `ResponseEntity` e status HTTP.
4. Atualização com verificação de existência e exclusão administrativa.
5. Bean Validation e handler global; JSON malformado e não encontrado têm 400/404.
6. Página, limite, ordenação e filtros por faixa de preço; metadados e links.
7. BCrypt, token JWT, perfis, rota protegida e Swagger.
8. Integração final; a coleção Postman cobre sucesso/erros. Deploy, capturas reais e pitch presencial ainda precisam de ambiente cloud, credenciais e execução humana. Nenhum link de produção ou captura foi inventado.

## Reflexão final
“Funciona na minha máquina” descreve uma execução num ambiente conhecido. Produção acrescenta rede, concorrência, credenciais, falhas e observabilidade. Swagger explica o contrato; a coleção torna verificações reproduzíveis; deploy testa configuração e operação fora do ambiente local. Juntas, essas práticas reduzem ambiguidade e tornam defeitos detectáveis antes de afetarem usuários.

### Bootstrap do perfil ADMIN para demonstração
Depois de registrar um usuário, um administrador do banco pode promover somente a conta de demonstração com `UPDATE usuarios SET perfil='ADMIN' WHERE email='<email-controlado>';`. Faça isso apenas no banco local de demonstração e remova o acesso após a avaliação. Não há endpoint público para elevar privilégios.
