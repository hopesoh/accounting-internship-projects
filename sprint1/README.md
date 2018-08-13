# Sistema de Contabilização - Sprint 1
=============

Na empresa PagSeuturco são recebidos arquivos que contém registros de movimentação financeira para contabilização diária.
Esses arquivos são recebidos em formato CSV com dados separados da seguinte forma: id_movimentacao, tipo_movimentacao, 
valor_movimentacao, conta_movimentacao, data_movimentacao.
Considerando isso, é necessário criar um sistema que separe as movimentações por "tipo de movimentação" e por "conta de movimentação"
de forma que seja possível contabilizar quanto dinheiro entrou e quanto dinheiro saiu de determinada conta.

*Critérios de aceite:*
- Deve ser possível indicar qual o caminho do arquivo que deve ser processado;
- Deve ser possível eu solicitar qual o conta de movimentação eu quero visualizar os tipos de movimentação e o total dos valores;
- Os tipos de movimentação neste momento são dédito e crédito, mas outros tipos podem ser inseridos no futuro;
- Os valores das movimentações possuirão ponto flutuante em duas casas;
- Deve haver testes unitário para o código;
 
*Exemplo de conteúdo de arquivo recebido*

```
id_movimentacao;tipo_movimentacao;valor_movimentacao;conta_movimentacao;data_movimentacao
1;DEBITO;130.55;101;15/08/2018
2;CREDITO;130.55;102;15/08/2018
3;DEBITO;10.95;102;15/08/2018
4;CREDITO;10.95;103;15/08/2018
5;DEBITO;150.50;103;15/08/2018
6;CREDITO;150.50;101;15/08/2018
7;DEBITO;300.00;102;15/08/2018
8;CREDITO;300.00;103;15/08/2018
9;DEBITO;20.55;101;15/08/2018
10;CREDITO;20.55;102;15/08/2018
11;DEBITO;1.10;102;15/08/2018
12;CREDITO;1.10;101;15/08/2018
13;DEBITO;22.45;103;15/08/2018
14;CREDITO;22.45;102;15/08/2018
15;DEBITO;72.35;101;15/08/2018
16;CREDITO;72.35;103;15/08/2018
17;DEBITO;111.22;103;15/08/2018
18;CREDITO;111.22;102;15/08/2018
19;DEBITO;145.35;103;15/08/2018
20;CREDITO;145.35;102;15/08/2018
21;DEBITO;13.50;101;15/08/2018
22;CREDITO;13.50;102;15/08/2018
```