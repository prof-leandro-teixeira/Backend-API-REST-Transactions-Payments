# Projeto Backend Java para Integração com Gateways de Pagamento

## Estrutura do Projeto

Este projeto utiliza uma **Arquitetura em Camadas** combinada com a **Arquitetura Baseada em Serviços**, uma abordagem padrão em Desenvolvimento de Software. Cada camada tem uma responsabilidade bem definida, seguindo o princípio de separação de responsabilidades e encapsulamento.

### 1. Entity (Modelo)

- **Responsabilidade:** Representa o modelo de dados ou mapeamento das entidades do banco de dados. Cada classe `@Entity` mapeia uma tabela.
- **Classificação:** Camada de Persistência. Manipula diretamente os dados no banco.

### 2. Repository

- **Responsabilidade:** Abstrai a lógica de acesso aos dados usando Spring Data JPA.
- **Classificação:** Camada de Acesso a Dados. Fornece métodos para operações CRUD.

### 3. Service

- **Responsabilidade:** Contém a lógica de negócio, orquestrando operações entre os Repositórios e Controladores.
- **Classificação:** Camada de Serviço. Implementa regras de negócio.

### 4. Controller

- **Responsabilidade:** Exposição dos endpoints da API. Recebe requisições HTTP, valida dados e invoca métodos da camada Service.
- **Classificação:** Camada de Apresentação. Interage com o usuário ou outras aplicações via chamadas HTTP.

### 5. DTO (Data Transfer Object)

- **Responsabilidade:** Transferência de dados entre camadas, desacoplando as entidades do banco das representações usadas pelas APIs.
- **Classificação:** Camada de Transferência de Dados. Promove segurança e performance.

---

## Arquitetura Geral

A estrutura geral é composta por:

1. **Camada de Persistência:** Repository + Entity.
2. **Camada de Serviço:** Service.
3. **Camada de Apresentação:** Controller.
4. **Camada de Transferência de Dados:** DTO.

---

## Princípios SOLID

### Single Responsibility Principle (SRP) - Princípio da Responsabilidade Única

- **Entity:** Cada classe (como `Sale`, `Product`, `Customer`) representa uma tabela no banco.
- **Controller:** Gerencia requisições HTTP e respostas.
- **Service:** Fornece lógica de negócios.
- **Repository:** Interage exclusivamente com o banco de dados.

### Liskov Substitution Principle (LSP) - Princípio da Substituição de Liskov

- Garantido por meio da organização modular. Embora a herança direta não seja usada nas entidades e DTOs, o desacoplamento promove substituições seguras e evita dependências excessivas.

---

**Nota:** Esta estrutura segue boas práticas de desenvolvimento, proporcionando um código escalável, seguro e alinhado aos requisitos da vaga de Desenvolvedor Backend Java Pleno.

# Backlog do Projeto - Desenvolvedor Backend Java Pleno

Este projeto foi criado para atender aos objetivos definidos em uma vaga de Desenvolvedor Backend Java Pleno. A partir das responsabilidades e requisitos descritos, o backlog foi organizado em tarefas realizadas e pendentes.

---

## ✅ **Tarefas Realizadas**

### Estruturação Inicial

- [x] Inicialização do projeto em Java com Spring Boot.
- [x] Criação das classes `Customer`, `Payment`, `Product`, `Sale` e `SalesItem` com `Entity`, `Repository`, `Service` e `Controller`.

### Operações CRUD

- [x] Implementação das operações `GET`, `POST`, `PUT` e `DELETE` para as entidades `Customer` e `Product`.
- [x] Testes das APIs de `Customer` e `Product` com o Postman.

### Configurações Básicas

- [x] Configuração do banco de dados MySQL e verificação de conexões.
- [x] Estruturação do arquivo `.gitignore` e inicialização do controle de versão com Git.

### Planejamento

- [x] Sprint Planning e definição de backlog com as principais tarefas.

---

## 🔄 **Tarefas Pendentes**

### Integrações de Pagamento

- [ ] Implementar funções para processar pagamentos via APIs de gateways (Stripe, PayPal, etc.).
- [ ] Validar respostas das APIs para autorizações, capturas e estornos de pagamentos.
- [ ] Garantir conformidade com padrões como PCI-DSS.

### Funcionalidades Avançadas

- [ ] Resolver problemas com as APIs de `Sale` e `SalesItem` (análise de logs do Spring Boot).
- [ ] Implementar autenticação e segurança com OAuth e JWT.
- [ ] Desenvolver fluxos complexos de pagamentos, incluindo reconciliação e disputas.

### Boas Práticas e Escalabilidade

- [ ] Adicionar documentação clara das APIs.
- [ ] Monitorar desempenho do sistema e implementar melhorias conforme necessário.

### Aprimoramento Técnico

- [ ] Adquirir experiência prática com ferramentas de mensageria como SQS, Kafka ou RabbitMQ.
- [ ] Configurar pipelines de CI/CD para o projeto.
- [ ] Certificar conformidade com padrões locais e internacionais de pagamentos.

---

## 🗓 **Próximos Passos**

1. Finalizar a análise e correção das APIs `Sale` e `SalesItem`.
2. Começar a implementação das integrações com gateways de pagamento.
3. Garantir autenticação e segurança nas operações com APIs.
4. Iniciar a integração com protocolos de comunicação e autenticação como HTTP, HTTPS, Webhooks, OAuth e JWT.
5. Implementar o fluxo de autorização, captura, estornos e reconciliação financeira para as transações.

---

## 📌 **Observações Finais**

- Este projeto está em constante evolução. Algumas tarefas avançadas como conformidade com PCI-DSS e a implementação de fluxos complexos de pagamento serão abordadas nas próximas versões do software.
