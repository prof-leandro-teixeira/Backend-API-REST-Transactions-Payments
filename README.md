# Projeto Backend Java com Integração com Gateways de Pagamento

Este é um projeto backend desenvolvido em **Java Spring Boot** com **Hibernate** e **arquitetura MVC**. O objetivo principal é realizar vendas com produtos para clientes e integrar essas vendas com sistemas de pagamentos financeiros, garantindo uma integração perfeita com gateways de pagamento como **Stripe** e **PayPal**.

O sistema segue as melhores práticas de design de software, com foco em **SOLID** para uma **arquitetura MVC** modular e de fácil manutenção.


### Princípios SOLID

- **SRP (Princípio da Responsabilidade Única)**: Cada classe tem uma única responsabilidade.
- **LSP (Princípio da Substituição de Liskov)**: Tratamento de erros e validações são feitas com classes específicas.
- **ISP (Princípio da Segregação de Interface)**: Interfaces específicas para repositórios e serviços.
- **DIP (Princípio da Inversão de Dependência)**: Uso de injeção de dependência via Spring para desacoplar as classes e facilitar a manutenção e testes.

### Arquitetura MVC utilizando pacotes:

- **Entity**: Mapeia tabelas do banco de dados.
- **Repository**: Abstrai o acesso aos dados com **Spring Data JPA** e **Hibernate**.
- **Service**: Implementa a lógica de negócios.
- **Controller**: Expõe os endpoints da API REST.
- **DTO**: Realiza a transferência de dados entre camadas, desacoplando as entidades do banco das APIs.

## Tecnologias Usadas

- **Java** com **Spring Boot**
- **Hibernate** e **Spring Data JPA** para persistência de dados
- **Arquitetura MVC**
- **Git** para controle de versão

## Funcionalidades

- Realização de vendas e integração com sistemas de pagamento
- Implementação de fluxos complexos de pagamento (autorização, captura, estornos, etc.)
- Conformidade com padrões de segurança, como PCI-DSS
- API REST para manipulação de dados de vendas

# SIMULAÇÃO DE METODOLOGIAS ÁGEIS COM SCRUM

##BACKLOG DO PRODUTO

- Realizar vendas com produtos para clientes
- Integrar vendas com pagamentos garantindo a integração perfeita entre sistemas financeiros, sempre alinhado(a) às melhores práticas de segurança e conformidade do mercado.
- Gerenciar fluxos complexos de pagamentos, incluindo autorização, captura, estornos e disputas.
- Garantir conformidade com padrões do setor, como PCI-DSS, e seguir regulamentações locais e internacionais relacionadas a pagamentos.

##BACKLOG DA SPRINT

**CONFIGURAÇÃO DO AMBIENTE**

- Descrição: Preparação do ambiente de desenvolvimento, incluindo IDEs, dependências e configuração de servidor.
- Status: Concluído.
- Data de Conclusão: 26/12/2024.

**CRIAÇÃO DO BACKEND INICIAL**

- Descrição: Estrutura inicial do backend com as dependências e configurações básicas com spring initializr.
- Status: Concluído.
- Data de Conclusão: 29/12/2024.

**CRIAÇÃO DE MODELOS E REPOSITÓRIOS**

- Descrição: Definição dos modelos de dados e repositórios para integração com o banco de dados utilizando Hibernate e Spring Data JPA.
- Status: Concluído.
- Data de Conclusão: 28/12/2024.

**INTEGRAÇÃO COM BANCO DE DADOS**

- Descrição: Configuração e integração do sistema com o banco de dados utilizando Hibernate.
- Status: Concluído.
- Data de Conclusão: 28/12/2024.

**CRIAÇÃO DE ENDPOINTS REST**

- Descrição: Criação de endpoints básicos da API REST para manipulação de dados.
- Status: Concluído.
- Data de Conclusão: 30/12/2024.

**IMPLEMENTAÇÃO DA API REST**

- Descrição: Implementação completa da API REST, com endpoints que realizam operações CRUD.
- Status: Concluído.
- Data de Conclusão: 13/01/2025.

**VERSIONAMENTO COM GIT**

- Descrição: Implementação do controle de versão utilizando Git.
- Status: Concluído.
- Data de Conclusão: 13/01/2025.

**VALIDAÇÕES E TRATAMENTO DE ERROS**

- Descrição: Implementar validações nas entradas das requisições e adicionar tratamento adequado de erros para as respostas da API.
- Prioridade: Alta.
- Status: Em Andamento.
- Data Estimada de Conclusão: 20/01/2025.

**AUTENTICAÇÃO E AUTORIZAÇÃO**

- Descrição: Adicionar autenticação e autorização para garantir que apenas usuários autenticados possam acessar os recursos da API.
- Prioridade: Alta.
- Status: Não Iniciado.
- Data Estimada de Conclusão: 30/01/2025.

**TESTES AUTOMATIZADOS**

- Descrição: Implementar testes unitários e de integração para validar as funcionalidades da API.
- Prioridade: Alta.
- Status: Não Iniciado.
- Data Estimada de Conclusão: 15/02/2025.

**INTEGRAÇÃO COM GATEWAYS DE PAGAMENTO (Ex.: Stripe, PayPal)**

- Descrição: Integrar a API com gateways de pagamento.
- Prioridade: Média.
- Status: Não Iniciado.
- Data Estimada de Conclusão: 28/02/2025.

**CONFORMIDADE COM REGULAMENTAÇÕES (PCI-DSS, etc.)**

- Descrição: Garantir que o sistema atenda aos padrões de conformidade exigidos.
- Prioridade: Média.
- Status: Não Iniciado.
- Data Estimada de Conclusão: 31/03/2025.

**IMPLEMENTAÇÃO DE FUNCIONALIDADES AVANÇADAS**

- Descrição: Adicionar recursos como estornos de pagamento e reconciliação financeira.
- Prioridade: Baixa.
- Status: Não Iniciado.
- Data Estimada de Conclusão: 30/04/2025.

**REFATORAÇÃO DE CÓDIGO**

- Descrição: Refatorar o código existente para otimizar o desempenho e a manutenção do sistema.
- Prioridade: Baixa.
- Status: Não Iniciado.
- Data Estimada de Conclusão: 30/04/2025.

---

📌 Observações Finais

- Este projeto está em constante evolução e prioriza funcionalidades de maior impacto.
- Tarefas futuras serão ajustadas com base em feedback e necessidades emergentes.
- Boas práticas, como a conformidade com PCI-DSS e documentação completa, serão abordadas nas próximas fases do desenvolvimento.
