package br.com.pagseuturco.accounting.init;

import br.com.pagseuturco.accounting.data.TransactionsAccounting;
import br.com.pagseuturco.accounting.data.Turnover;
import br.com.pagseuturco.accounting.file.FileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class TransactionAccount {

    private static final String SEMICOLON = ";";
    private static final String HEADER = "id_movimentacao;tipo_movimentacao;valor_movimentacao;conta_movimentacao;data_movimentacao";

    public void processFile () throws IOException {

        FileReader fileReader = new FileReader();
        BufferedReader reader = fileReader.readFile();

        ArrayList<Turnover> turnoverList = transformIntoTurnoverList(reader);

        Scanner input = new Scanner(System.in);
        System.out.print( "Insira o numero da conta: " );
        int accountNumber = Integer.parseInt(input.nextLine());

        while (accountNumberDoesntExistInTurnoverArray(accountNumber, turnoverList)) {
            System.out.println("Esse número de conta não existe.");
            System.out.print("Entre com um numero de conta válido: ");
            accountNumber = Integer.parseInt(input.nextLine());
        }

        TransactionsAccounting transactionsAccounting = accountTransactionsByType(turnoverList, accountNumber);
        System.out.println("A soma dos valores de Débito é: " + transactionsAccounting.getDabitValue());
        System.out.println("A soma dos valores de Crédito é: "+ transactionsAccounting.getCreditValue());
        reader.close();
    }

    public ArrayList<Turnover> transformIntoTurnoverList(BufferedReader reader) throws IOException {

        String fileLine = null;
        ArrayList<Turnover> turnoverArrayList = new ArrayList<Turnover>();


        while ((fileLine = reader.readLine()) != null) {

            if (fileLine.equals(HEADER)) {
                continue;
            }

            String[] splittedLine = fileLine.split(SEMICOLON, -1);

            if (splittedLine.length == 0) {
                return null;
            }

            Turnover turnover = new Turnover(splittedLine);
            turnoverArrayList.add(turnover);
        }

        return turnoverArrayList;
    }

    public boolean accountNumberDoesntExistInTurnoverArray (int accountNumber, ArrayList<Turnover> turnoverArrayList) {

        for (Turnover turnover : turnoverArrayList) {
            if (accountNumber == turnover.getAccount()) {
                return false;
            }
        }

        return true;
    }

    public TransactionsAccounting accountTransactionsByType(ArrayList<Turnover> turnoverArrayList, int accountNumber) {

        BigDecimal debitSum = new BigDecimal(0);
        BigDecimal creditSum = new BigDecimal(0);

        for (Turnover turnover : turnoverArrayList) {
            if (accountNumber == turnover.getAccount()) {
                if (turnover.getType().equals("CREDITO")) {
                    creditSum = creditSum.add(turnover.getValue());
                }
                if (turnover.getType().equals("DEBITO")) {
                    debitSum = debitSum.add(turnover.getValue());
                }
            }
         }

        return new TransactionsAccounting(creditSum, debitSum);
    }
}
