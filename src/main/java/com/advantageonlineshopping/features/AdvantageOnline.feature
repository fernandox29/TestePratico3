#language: pt

Funcionalidade: Acessar Site Advantage Online Shopping
  Como um usuário
  Eu quero acessar o site de vendas online
  Para realizar testes para NTT DATA

  Contexto:
    Dado que eu acesso o site do Advantage Online Shopping
    Então eu verifico se o site está disponível

  Cenário: Validar especificações do produto
    Dado que eu clico na opção "SPECIAL OFFER"
    E clico no botão "See offer"
    Então eu valido as informações do produto


  Cenário: Validar alteração de cor do produto no carrinho
    Dado que eu clico na opção "SPECIAL OFFER"
    E clico no botão "See offer"
    E altero a cor do produto para "corBase"
    E adiciono o produto no carrinho
    Então eu valido que produto foi adicionado ao carrinho com a cor selecionada


  Cenário: Validar página de checkout
    Dado que eu pesquiso o produto pela lupa
    E seleciono o produto pesquisado
    E altero a cor do produto para "RED"
    E altero a quantidade do produto
    E adiciono o produto no carrinho
    Então verifico o valor total dos prdutos
    E atualizo a base com a cor selecionada para o produto

  Cenário: Remover produto do carrinho de compras
    Dado que eu clico na opção "SPECIAL OFFER"
    E clico no botão "See offer"
    E adiciono o produto no carrinho
    Então eu removo o produto do carrinho
    E verifico se o carrinho está vazio

