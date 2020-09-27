# Biblioteca Virtual

**API Rest de uma biblioteca virtual simples para acompanhamento de aluguel e reserva de livros.**

## Utilização

Você precisa ter o JDK instalado e configurado em sua máquina.

Após isso, clone o projeto para sua máquina e o execute.

Então com algum gerenciador de requisição API, crie chamadas específicas, 
que podem ser encontradas nos controllers dentro de pacotes '/v1'.

As chamadas são de cadastrar livro, cadastrar cliente, reservar livro, cancelar reserva de livro, 
alugar livro, devolver livro alugado, retornar page de livros, retornar page de clientes, retornar 
informações gerais de livros (dashboard) e retornar quais livros são os mais alugados (dashboard). 
Esses dois últimos fazem parte do mesmo endpoint, mas são diferenciados através do parâmetro do tipo 
de chart que será utilizado, por exemplo:

* {this.baseUrl}/v1/books/dashboard/pie -> retornar informações específicas para o chart do tipo torta.
* {this.baseUrl}/v1/books/dashboard/bar -> retorna informações específicas para o chart do tipo barra.

Na pasta '/resources/templates' existem dois arquivos semelhantes, mas em formatos diferentes, que servem 
como modelo de requisição para os endpoints. Foi utilizada a ferramenta Insomnia. 

## Endpoint de acesso ao contexto de livros

* /v1/books/**

## Endpoint de acesso ao contexto de clientes

* /v1/customers/**

## Banco de Dados

Momentaneamente, foi utilizado o banco in-memory H2 Database.


