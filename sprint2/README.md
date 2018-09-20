# Sistema de Contabilização - Sprint 2

O sistema de contabilização do PagSeuTurco está evoluindo e precisa processar diversos tipos de documentos de movimentações diferentes, 
faz-se necessário que o sistema consiga trabalhar processando os arquivos independente do tipo de documento que será inserido na pasta de leitura.



**Regras de processamento**
Cada tipo de arquivo possui uma regra diferente de processamento:

Documento Movimentação Boleto:
 - Para cada movimentação do tipo boleto deve ser adicionado o valor de 0.25 centavos na conta do PagSeuTurco(1) e removido da conta de crédito;

Documento Movimentação Cartão:
 - Se for cartão de débito dever ser adicionado o valor de 2.00% na conta do PagSeuTurco e removido da conta conta de crédito
 - Se for cartão de crédito dever ser adicionado o valor de 3.50% na conta do PagSeuTurco e removido da conta conta de crédito;

Documento Movimentação Transferencia:
 - Para transferências não haverá débito na conta do usuário;




**Exemplos de documentos:**

```
 
Documento Movimentação Boleto (financial turnover booklet)
numero_documento; nome_emissor;identificacao_emissor;valor_documento;conta;data_recebimento
5140265105;Roshni Dixon;60346402026;110;101;15/08/2018
4195660854;Donnell Currie;66484689040;111;101;15/08/2018
3134546731;Roma Nelson;28525592005;112;102;15/08/2018
2530214826;Favour Foster;26518883030;113;102;15/08/2018
1164336844;Vienna Bellamy;29249325053;114;103;15/08/2018
9139163845;Eleni Holman;50212903047;115;103;15/08/2018
8502898352;Phillippa Chandler;40795447078;101;116;15/08/2018
7104447649;Alexandria Willis;56240852066;101;117;15/08/2018
6645409402;Sanna O'Neill;51198407018;118;102;15/08/2018
5452258542;Kourtney Rennie;28657523051;119;102;15/08/2018


Documento Movimentação Cartão (financial turnover genericcard)
hash_cartao;tipo_transacao;valor;conta;data_transacao
c57e3608-10d1-4314-8fa2-e554d0da5457;DEBIT;200;101;15/08/2018
591611af-adcd-4076-9d97-1090fd78d09a;CREDIT;129.30;101;15/08/2018 
6f0e09f4-0442-490a-b8ec-774240f8badb;CREDIT;132.33;102;15/08/2018
b11e01d2-4a30-4158-858e-157f1f55bc6d;CREDIT;42;102;15/08/2018
57d9bfd7-736a-47c9-9484-1989361a8220;DEBIT;33;103;15/08/2018
aebd205d-db84-4589-b8b7-5b6236e1033a;CREDIT;115;103;15/08/2018
1cb8ee27-8966-494c-9fa8-2944d87de09b;DEBIT;45.30;101;15/08/2018
9f22932d-5c01-48db-a0ff-3f450d139f62;CREDIT;24.00;101;15/08/2018
804ffb8b-b123-4bdc-a7a9-05651ce3099a;CREDIT;118.44;102;15/08/2018
ef6a7d2d-4470-4763-852b-c645aa98dd67;DEBIT;99;102;15/08/2018
b7017530-f79d-4da4-adb0-f3d69b1ece2d;CREDIT;102.31;103;15/08/2018
1946b43b-3ae3-4580-819c-26fd522b5bd6;DEBIT;25.00;103;15/08/2018
c3c88405-9069-4e05-979b-66b16cce8db0;CREDIT;66.06;101;15/08/2018
68dbc961-8af8-4544-829f-20b4421b94ee;CREDIT;1120;101;15/08/2018
ab93e34e-63f5-4208-bbf9-9bcf81d79aab;CREDIT;10.01;102;15/08/2018
74eef40a-5b13-4995-927e-c6f4451e01ab;CREDIT;22.30;102;15/08/2018
f737037b-8cc8-408d-968e-32aa0eb44052;DEBIT;30;103;15/08/2018
421ea591-3d15-4771-8f21-2d82f03a6a76;DEBIT;31.05;103;15/08/2018
f52c9878-ec75-46a9-9c0c-379ef01604e1;CREDIT;20.00;101;15/08/2018
ecf16f9e-fa27-4fcf-a779-da50b4ff8eb1;CREDIT:7;101;15/08/2018
f480d21b-93e4-4907-9ece-80d22b6043a9;DEBIT;10.00;102;15/08/2018


Documento Movimentação Transferencia (financial turnover transfer)
tipo;valor;conta;data_transacao
DEBITO;130.55;101;15/08/2018
CREDITO;130.55;102;15/08/2018
DEBITO;10.95;102;15/08/2018
CREDITO;10.95;103;15/08/2018
DEBITO;150.50;103;15/08/2018
CREDITO;150.50;101;15/08/2018
DEBITO;300.00;102;15/08/2018
CREDITO;300.00;103;15/08/2018
DEBITO;20.55;101;15/08/2018
CREDITO;20.55;102;15/08/2018
DEBITO;1.10;102;15/08/2018
CREDITO;1.10;101;15/08/2018
DEBITO;22.45;103;15/08/2018
CREDITO;22.45;102;15/08/2018
DEBITO;72.35;101;15/08/2018
CREDITO;72.35;103;15/08/2018
DEBITO;111.22;103;15/08/2018
CREDITO;111.22;102;15/08/2018
DEBITO;145.35;103;15/08/2018
CREDITO;145.35;102;15/08/2018
DEBITO;13.50;101;15/08/2018
CREDITO;13.50;102;15/08/2018
```
