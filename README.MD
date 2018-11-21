# Sistema de Contabilização PagSeuTurco
 O objetivo do sistema é contabillizar as transações do serviço do PagSeuTurco e guardar as informações em um local durável e seguro.
 O programa é responsável por ler o arquivo, identificar o tipo de movimentação financeira ao qual o
 documento se refere, contabilizar a transação e guardá-la em sua respectiva tabela no banco de dados.
# Como utilizar o sistema
 1. Altere a URL de conexão com o banco de dados: Na classe __br.com.pagseuturco.accounting.init.Main__
 é apresentada uma URL, chamada de MYSQL_JDBC_URL.
 Fazem parte dela o username e a senha. Altere para seus dados pessoais para que
 a conexão seja estabelecida.
 2. Inicialize o banco de dados: A pasta onde se encontra o programa
 desenvolvido, conta com um arquivo .txt, chamado de initialize_database, disponibilizado para auxiliar
 na criação das tabelas e do banco de dados utilizados nesse programa.
 3. Entre com o arquivo: Após inicializado por meio da classe Main,
 o programa estará disponível para o recebimento de um arquivo de
 movimentações financeiras.
# Estrutura do projeto
| Pasta                    | Descrição  |
|--------------------------|------------|
| src                      | Pasta que contém os códigos-fontes |
| main                     | Pasta que contém as funções principais|
| resources                | Pasta que contém arquivos usados como exemplo para a execução do programa|
| controller               | Pasta que contém a classe responsável pela sequência de chamadas do programa|
| init                     | Pasta que contém a classe responsável pela inicialização do programa|
| model                    | Pasta que contém todas as classes responsáveis por trabalhar com os dados recebidos|
| view.file                | Pasta que contém a classe responsável pelas interações com o usuário|
| test					   | Pasta que contém os testes dos códigos-fontes |
| sprint-stories           | Todas as estórias solicitadas durante o desenvolvimento deste projeto |
# Frameworks utilizados
 - Junit - Versão 4
# Ferramentas utilizadas no projeto
 - Git
 - Gradle
 - IntelliJ
 - MySQL
 - H2 Database