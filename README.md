# Atividade Crud RPG - Professor Eduardo

## Nome: Gustavo Alves Carneiro
## RA: 23202882-2

Uma API simples pra gerenciar aventureiros e seus itens mágicos no mundo de RPG, feita com Spring Boot e banco H2

## Endpoints:

http://localhost:8080

- Cadastrar: POST /personagens
- Listar: GET /personagens
- Buscar por id: GET /personagens/1
- Atualizar nome aventureiro: PUT /personagens/1/nome-aventureiro
- Remover: DELETE /personagens/1
- Adicionar item: POST /personagens/2/itens/2
- Listar itens: GET /personagens/2/itens
- Remover item: DELETE /personagens/2/itens/1
- Buscar amuleto: GET /personagens/2/amuleto
- Cadastrar itens: POST /itens
- Listar itens: GET /itens
- Buscar itens por id: GET /itens/1

## Regras especificadas pelo professor:

### Personagens:
- Ao criar um personagem, você tem 10 pontos pra dividir entre força e defesa como quiser, sem ultrapassar o limite (ex.: 7-3, 4-6, 8-2)
- Classes permitidas: GUERREIRO, MAGO, ARQUEIRO, LADINO, BARDO (outras não são aceitas)
- A força e defesa exibidas já incluem os bônus dos itens mágicos equipados
- Limite de 1 amuleto por personagem
- Itens duplicados (mesmo ID) não podem ser equipados

### Itens Mágicos:
- Tipos aceitos: ARMA, ARMADURA, AMULETO
- Armas: defesa sempre 0
- Armaduras: força sempre 0
- Amuletos: podem ter força e defesa, mas só 1 por personagem
- Força e defesa variam de 1 a 10, e não podem ser ambos 0 no mesmo item
