# ProspAI

## Integrantes do Grupo
- AGATHA PIRES – RM552247 – (2TDSPH) - MOBILE APPLICATION DEVELOPMENT / MASTERING RELATIONAL AND NON-RELATIONAL DATABASE
- DAVID BRYAN VIANA – RM551236 – (2TDSPM) - ADVANCED BUSINESS DEVELOPMENT WITH .NET / MOBILE APPLICATION DEVELOPMENT
- GABRIEL LIMA – RM99743 – (2TDSPM) - COMPLIANCE, QUALITY ASSURANCE & TESTS / DISRUPTIVE ARCHITECTURES: IOT, IOB & GENERATIVE IA
- GIOVANNA ALVAREZ – RM98892 – (2TDSPM) - COMPLIANCE, QUALITY ASSURANCE & TESTS / DEVOPS TOOLS & CLOUD COMPUTING
- MURILO MATOS – RM552525 – (2TDSPM) - JAVA ADVANCED / DEVOPS TOOLS & CLOUD COMPUTING

## Instruções para Executar a Aplicação
Para executar o projeto ProspAI, siga estas etapas:
1. Certifique-se de ter o Java Development Kit (JDK) instalado em sua máquina.
2. Clone o repositório do projeto para sua máquina local.
3. Abra o projeto em sua IDE preferida.
4. Execute o aplicativo como um aplicativo Spring Boot.

## Imagem dos Diagramas
Você pode encontrar a documentação e os testes na pasta `DOCUMENTAÇÃO`.

## Endpoints da API

### AdminController
O AdminController é responsável por manipular as operações relacionadas à administração do sistema, como obtenção de configurações do sistema, listagem de usuários e monitoramento de desempenho.

#### Endpoints:
- `/api/admin/configuracao`: Retorna as configurações do sistema.
- `/api/admin/usuarios`: Retorna a lista de usuários do sistema.
- `/api/admin/desempenho`: Monitora o desempenho do sistema.

### ClienteController
O ClienteController gerencia as operações relacionadas aos clientes, como listar clientes, obter um cliente por ID, cadastrar, atualizar e excluir um cliente.

#### Endpoints:
- `/api/clientes`: Retorna a lista de todos os clientes.
- `/api/clientes/{id}`: Retorna um cliente específico com base no ID fornecido.
- `/api/clientes`: Cadastra um novo cliente.
- `/api/clientes/{id}`: Atualiza as informações de um cliente existente.
- `/api/clientes/{id}`: Exclui um cliente existente com base no ID fornecido.

### ComprasController
O ComprasController lida com as operações relacionadas às compras, como listar compras, obter uma compra por ID, cadastrar, atualizar e excluir uma compra.

#### Endpoints:
- `/api/compras`: Retorna a lista de todas as compras.
- `/api/compras/{id}`: Retorna uma compra específica com base no ID fornecido.
- `/api/compras`: Cadastra uma nova compra.
- `/api/compras/{id}`: Atualiza as informações de uma compra existente.
- `/api/compras/{id}`: Exclui uma compra existente com base no ID fornecido.
