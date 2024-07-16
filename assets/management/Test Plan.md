<div align="justify" >
      
# Test Plan FinApp



## 1 Introdução

FinApp é uma aplicação web e mobile desenvolvida para solucionar o problema de gerenciamento financeiro compartilhado. A FinApp oferece uma plataforma colaborativa que permite a criação de uma "carteira" compartilhada para registro de despesas da dupla, fornecendo recursos como notificações de transações e relatórios de gastos. O objetivo principal deste projeto é simplificar o acompanhamento das finanças compartilhadas, mantendo os usuários informados sobre sua situação financeira.





## 2 Arquitetura


A arquitetura do projeto é baseada na utilização do React Native e React no Front-End, usando o React para o desenvolvimento web e o React Native para a parte mobile. O Springboot foi escolhido para o desenvolvimento do Back-End, enquanto a mensageria será implementada pelo AWS SQS.
O MySQL foi escolhido como o banco de dados devido à sua robustez, flexibilidade e confiabilidade, suportando grandes volumes de dados e alta demanda de acesso simultâneo, além de oferecer recursos avançados e ter uma comunidade ativa de desenvolvedores.





## 3 Funcionalidade


### 3.1	Mobile


| Funcionalidade | Cadastro |
| --- | --- |
| Comportamento Esperado | Ao preencher todos os campos obrigatórios, o usuário é redirecionado para a página principal. |
| Verificações | - Todos os campos devem ser obrigatórios.<br>- Exibir uma mensagem de confirmação em caso positivo.<br>- Redirecionar o usuário para tela de login.<br>- Exibir a mensagem de falha em caso do email ser existente.<br>- Exibir mensagem de falha no caso de campo obrigatório incompleto. |
| Critérios de Aceite | - Todos os campos devem ser obrigatórios.<br>- Exibir uma mensagem de confirmação em caso positivo.<br>- Redirecionar o usuário para a tela principal.<br>- Exibir a mensagem de falha em caso de email já existente.<br>- Exibir mensagem de falha caso o campo obrigatório esteja incompleto. |




| Funcionalidade | Login |
| --- | --- |
| Comportamento Esperado | Ao digitar seu usuário e senha corretamente, o usuário irá logar na plataforma e será direcionado para a página inicial. |
| Verificações | - Login no Sistema com sucesso.<br>- Usuário Inválido.<br>- Usuário não preencheu campo obrigatório.<br>- Senha Incorreta. |
| Critérios de Aceite | Ter acesso ao sistema. |




| Funcionalidade | Gerenciar Metas |
| --- | --- |
| Comportamento Esperado | O usuário deve ser capaz de definir, atualizar e acompanhar suas metas financeiras personalizadas. Ele deve ter uma visão clara de seu progresso em direção a essas metas. |
| Verificações | - Definição de meta com sucesso.<br>- Atualização de meta com sucesso.<br>- Acompanhamento de progresso da meta com sucesso.<br>- Meta inválida ou não definida. |
| Critérios de Aceite | O usuário consegue gerenciar suas metas financeiras de forma eficaz e tem uma visão clara de seu progresso em direção a elas. |




| Funcionalidade | Gerenciar Categorias |
| --- | --- |
| Comportamento Esperado | O usuário deve ser capaz de criar categorias personalizadas para melhor organização e rastreamento dos gastos. |
| Verificações | - Criação de categoria com sucesso.<br>- Categoria inválida ou não definida.<br>- Atualização de categoria com sucesso.<br>- Exclusão de categoria com sucesso. |
| Critérios de Aceite | O usuário consegue criar, atualizar e excluir categorias personalizadas para uma melhor organização e rastreamento dos seus gastos. |




| Funcionalidade | Compartilhamento de Carteiras |
| --- | --- |
| Comportamento Esperado | O usuário deve ser capaz de compartilhar suas carteiras com outros usuários. |
| Verificações | - Compartilhamento de carteira com sucesso.<br>- Carteira inválida ou não definida.<br>- Usuário com quem a carteira é compartilhada é válido.<br>- Revogação de compartilhamento de carteira com sucesso. |
| Critérios de Aceite | O usuário consegue compartilhar e revogar o compartilhamento de suas carteiras com outros usuários de forma eficaz. |




| Funcionalidade | Histórico de Transações |
| --- | --- |
| Comportamento Esperado | O usuário deve ser capaz de manter um registro completo de todas as transações realizadas, permitindo revisar seu histórico financeiro a qualquer momento. |
| Verificações | - Registro de transação com sucesso.<br>- Visualização do histórico de transações com sucesso. |
| Critérios de Aceite | O usuário consegue registrar e visualizar todas as transações realizadas de forma eficaz. |




| Funcionalidade | Notificações de Transações |
| --- | --- |
| Comportamento Esperado | O usuário deve receber notificações em tempo real sempre que uma transação for realizada. |
| Verificações | - Recebimento de notificação com sucesso.<br>- Falha no recebimento de notificação. |
| Critérios de Aceite | O usuário recebe notificações em tempo real sempre que uma transação é realizada. |




| Funcionalidade | Comentários em Transações |
| --- | --- |
| Comportamento Esperado | O usuário deve ser capaz de deixar comentários em transações. |
| Verificações | - Adição de comentário com sucesso.<br>-  Atualização de comentário com sucesso.<br>- Exclusão de comentário com sucesso. |
| Critérios de Aceite | O usuário consegue adicionar, atualizar e excluir comentários em transações de forma eficaz. |





| Funcionalidade | Geração de Relatórios de Transações |
| --- | --- |
| Comportamento Esperado | O usuário deve ser capaz de gerar relatórios sobre as transações. |
| Verificações | - Geração de relatório com sucesso.<br>- Falha na geração de relatório. |
| Critérios de Aceite | O usuário consegue gerar relatórios sobre as transações realizadas de forma eficaz. |

### 3.2	Web

| Funcionalidade | Login |
| --- | --- |
| Comportamento Esperado | Ao digitar seu usuário e senha corretamente, o usuário irá logar na plataforma e será direcionado para a página inicial. |
| Verificações | - Login no Sistema com sucesso.<br>- Usuário Inválido.<br>- Usuário não preencheu campo obrigatório.<br>- Senha Incorreta. |
| Critérios de Aceite | Ter acesso ao sistema. |

| Funcionalidade | Visualização de Metas |
| --- | --- |
| Comportamento Esperado | O usuário deve ser capaz de visualizar suas metas financeiras e o progresso em direção a elas. |
| Verificações | - Visualização de metas com sucesso.<br>- Falha na visualização de metas. |
| Critérios de Aceite | O usuário consegue visualizar suas metas financeiras e o progresso em direção a elas de forma eficaz. |

| Funcionalidade | Visualização de Categorias |
| --- | --- |
| Comportamento Esperado | O usuário deve ser capaz de visualizar as categorias personalizadas criadas para melhor organização e rastreamento das transações. |
| Verificações | - Visualização de categorias com sucesso.<br>- Falha na visualização de categorias. |
| Critérios de Aceite | O usuário consegue visualizar as categorias personalizadas criadas para uma melhor organização e rastreamento das transações. |

| Funcionalidade | Visualização de Carteiras Compartilhadas |
| --- | --- |
| Comportamento Esperado | O usuário deve ser capaz de visualizar as carteiras que foram compartilhadas com ele. |
| Verificações | - Visualização de carteiras compartilhadas com sucesso.<br>- Falha na visualização de carteiras compartilhadas. |
| Critérios de Aceite | O usuário consegue visualizar as carteiras que foram compartilhadas com ele. |

| Funcionalidade | Visualização de Carteiras Individuais |
| --- | --- |
| Comportamento Esperado | O usuário deve ser capaz de visualizar as carteiras individuais. |
| Verificações | - Visualização de carteiras individuais com sucesso.<br>- Falha na visualização de carteiras individuais. |
| Critérios de Aceite | O usuário consegue visualizar as carteiras individuais. |

| Funcionalidade | Visualização do Histórico de Transações |
| --- | --- |
| Comportamento Esperado | O usuário deve ser capaz de exibir um registro completo de todas as transações realizadas, permitindo revisar seu histórico financeiro. |
| Verificações | - Exibição do histórico de transações com sucesso.<br>- Falha na exibição do histórico de transações. |
| Critérios de Aceite | O usuário consegue exibir um registro completo de todas as transações realizadas e revisar seu histórico financeiro. |

| Funcionalidade | Notificações de Transações |
| --- | --- |
| Comportamento Esperado | O usuário deve ser capaz de visualizar notificações sobre transações realizadas. |
| Verificações | - Visualização de notificações com sucesso.<br>- Falha na visualização de notificações. |
| Critérios de Aceite | O usuário consegue visualizar notificações sobre transações realizadas. |

| Funcionalidade | Visualização de Comentários em Transações |
| --- | --- |
| Comportamento Esperado | O usuário deve ser capaz de visualizar os comentários deixados em transações. |
| Verificações | - Visualização de comentários com sucesso.<br>- Falha na visualização de comentários. |
| Critérios de Aceite | O usuário consegue visualizar os comentários deixados em transações. |

## 4 Estratégia de Teste

### 4.1 Escopo de Testes

O plano de testes abrange todas as funcionalidades descritas na tabela acima. 

Serão executados testes nos níveis conforme a descrição abaixo.

Testes Unitários: o código terá uma cobertura de 50% de testes unitários, que são de responsabilidade dos desenvolvedores.
Testes Automatizados: Serão realizados testes end-to-end para todas as funcionalidades, responsabilidade do time de qualidade, seguindo a documentação de Cenários de teste e deste Test Plan.



### 4.2 Ambiente e Ferramentas

Os testes serão feitos do ambiente de produção.

As seguintes ferramentas serão utilizadas no teste:



| Ferramenta | Time | Descrição |
| --- | --- | --- |
| Postman | Qualidade | Ferramenta para realização de testes de API |
| Cypress | Qualidade | Ferramenta para realização de testes na plataforma Web|
| Maestro | Qualidade | Ferramenta para realização de testes na plataforma Mobile|
| JUnit | Desenvolvimento | Framework utilizada para testes unitários |





## 5 Classificação de Bugs

Os Bugs serão classificados com as seguintes severidades:


| ID | Nível de Severidade | Descrição |
| --- | --- | --- |
| 1 | Blocker | Bug que bloqueia o teste de uma função ou feature causa crash na aplicação.<br>Botão não funciona impedindo o uso completo da funcionalidade.<br>Bloqueia a entrega. |
| 2 | Grave | Funcionalidade não funciona como o esperado.<br>Input incomum causa efeitos irreversíveis. |
| 3 | Moderada | Funcionalidade não atinge certos critérios de aceitação, mas sua funcionalidade em geral não é afetada.<br>Mensagem de erro ou sucesso não é exibida. |
| 4 | Pequena | Quase nenhum impacto na funcionalidade, porém atrapalha a experiência.<br>Erro ortográfico.<br>Pequenos erros de UI. |





## 6 Definição de Pronto 

Serão consideradas prontas as funcionalidades que passarem pelas verificações e testes descritas neste Test Plan, não apresentarem bugs com a severidade acima de Minor, e passarem por uma validação de negócio de responsabilidade do time de QA.
