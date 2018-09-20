package br.com.pagseuturco.accounting.init;

import static org.junit.jupiter.api.Assertions.*;

import br.com.pagseuturco.accounting.data.TransactionsAccounting;
import br.com.pagseuturco.accounting.data.Turnover;
import br.com.pagseuturco.accounting.init.TransactionAccount;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionAccountTest {
    @Test
    public void transformIntoTurnoverListAllFieldsComplete() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader("2;CREDITO;300;101;27/08/2018;"));
        ArrayList<Turnover> expectedData = new ArrayList<Turnover>();

        String[] splittedLine = new String[5];
        splittedLine[0] = "2";
        splittedLine[1] = "CREDITO";
        splittedLine[2] = "300";
        splittedLine[3] = "101";
        splittedLine[4] = "27/08/2018";

        Turnover turnover = new Turnover(splittedLine);
        expectedData.add(turnover);

        TransactionAccount account = new TransactionAccount();

        assertEquals(expectedData, account.transformIntoTurnoverList(reader));
    }

    @Test
    public void transformIntoTurnoverListTestMissingDateField() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader("2;DEBITO;200;100;;"));
        ArrayList<Turnover> expectedData = new ArrayList<Turnover>();

        String[] splittedLine = new String[5];
        splittedLine[0] = "2";
        splittedLine[1] = "DEBITO";
        splittedLine[2] = "200";
        splittedLine[3] = "100";
        splittedLine[4] = "";

        Turnover turnover = new Turnover(splittedLine);
        expectedData.add(turnover);

        TransactionAccount account = new TransactionAccount();

        assertEquals(expectedData, account.transformIntoTurnoverList(reader));
    }

    @Test
    public void transformIntoTurnoverListTestMissingAccountField() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader("2;DEBITO;200;;27/08/2018;"));

        ArrayList<Turnover> expectedData = new ArrayList<Turnover>();

        String[] splittedLine = new String[5];
        splittedLine[0] = "2";
        splittedLine[1] = "DEBITO";
        splittedLine[2] = "200";
        splittedLine[3] = "";
        splittedLine[4] = "27/08/2018";

        Turnover turnover = new Turnover(splittedLine);
        expectedData.add(turnover);

        TransactionAccount account = new TransactionAccount();

        assertEquals(expectedData, account.transformIntoTurnoverList(reader));
    }

    @Test
    public void transformIntoTurnoverListTestMissingTypeField() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader("2;;200;100;27/08/2018;"));
        ArrayList<Turnover> expectedData = new ArrayList<Turnover>();

        String[] splittedLine = new String[5];
        splittedLine[0] = "2";
        splittedLine[1] = "";
        splittedLine[2] = "200";
        splittedLine[3] = "100";
        splittedLine[4] = "27/08/2018";

        Turnover turnover = new Turnover(splittedLine);
        expectedData.add(turnover);

        TransactionAccount account = new TransactionAccount();

        assertEquals(expectedData, account.transformIntoTurnoverList(reader));
    }

    @Test
    public void transformIntoTurnoverListTestMissingValueField() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader("2;DEBITO;;101;27/08/2018;"));
        ArrayList<Turnover> expectedData = new ArrayList<Turnover>();

        String[] splittedLine = new String[5];
        splittedLine[0] = "2";
        splittedLine[1] = "DEBITO";
        splittedLine[2] = "";
        splittedLine[3] = "101";
        splittedLine[4] = "27/08/2018";

        Turnover turnover = new Turnover(splittedLine);
        expectedData.add(turnover);

        TransactionAccount account = new TransactionAccount();

        assertEquals(expectedData, account.transformIntoTurnoverList(reader));
    }

    @Test
    public void transformIntoTurnoverListTestMissingAllFields() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader(";;;;"));
        ArrayList<Turnover> expectedData = new ArrayList<Turnover>();

        String[] splittedLine = new String[5];
        splittedLine[0] = "";
        splittedLine[1] = "";
        splittedLine[2] = "";
        splittedLine[3] = "";
        splittedLine[4] = "";

        Turnover turnover = new Turnover(splittedLine);
        expectedData.add(turnover);

        TransactionAccount account = new TransactionAccount();

        assertEquals(expectedData, account.transformIntoTurnoverList(reader));
    }

    @Test
    public void accountTransactionsByTypeExpectedCreditSum300DebitSum0() {

        ArrayList<Turnover> turnoverArrayList = new ArrayList<Turnover>();
        String accountNumber = "4";

        String[] splittedLine = new String[5];
        splittedLine[0] = "1";
        splittedLine[1] = "CREDITO";
        splittedLine[2] = "300";
        splittedLine[3] = accountNumber;
        splittedLine[4] = "";

        Turnover turnover = new Turnover(splittedLine);
        turnoverArrayList.add(turnover);

        BigDecimal creditSum = new BigDecimal(300);
        BigDecimal debitSum = new BigDecimal(0);
        TransactionsAccounting expectedAccounting = new TransactionsAccounting(creditSum, debitSum);
        TransactionAccount account = new TransactionAccount();

        assertEquals(expectedAccounting, account.accountTransactionsByType(turnoverArrayList, Integer.parseInt(accountNumber)));
    }

    @Test
    public void accountTransactionsByTypeExpectedCreditSum300DebitSum10() {

        ArrayList<Turnover> turnoverArrayList = new ArrayList<Turnover>();
        String accountNumber = "4";

        String[] splittedLine = new String[5];
        splittedLine[0] = "1";
        splittedLine[1] = "CREDITO";
        splittedLine[2] = "300";
        splittedLine[3] = accountNumber;
        splittedLine[4] = "";
        Turnover turnover = new Turnover(splittedLine);
        turnoverArrayList.add(turnover);

        splittedLine[0] = "2";
        splittedLine[1] = "DEBITO";
        splittedLine[2] = "10";
        splittedLine[3] = accountNumber;
        splittedLine[4] = "";
        turnover = new Turnover(splittedLine);
        turnoverArrayList.add(turnover);

        BigDecimal creditSum = new BigDecimal(300);
        BigDecimal debitSum = new BigDecimal(10);
        TransactionsAccounting expectedAccounting = new TransactionsAccounting(creditSum, debitSum);
        TransactionAccount account = new TransactionAccount();

        assertEquals(expectedAccounting, account.accountTransactionsByType(turnoverArrayList, Integer.parseInt(accountNumber)));
    }

    @Test
    public void accountTransactionsByTypeExpectedCreditSum10DebitSum0() {

        ArrayList<Turnover> turnoverArrayList = new ArrayList<Turnover>();
        String accountNumber = "2";

        String[] splittedLine = new String[5];
        splittedLine[0] = "1";
        splittedLine[1] = "CREDITO";
        splittedLine[2] = "300";
        splittedLine[3] = "4";
        splittedLine[4] = "";
        Turnover turnover = new Turnover(splittedLine);
        turnoverArrayList.add(turnover);

        splittedLine[0] = "2";
        splittedLine[1] = "DEBITO";
        splittedLine[2] = "10";
        splittedLine[3] = accountNumber;
        splittedLine[4] = "";
        turnover = new Turnover(splittedLine);
        turnoverArrayList.add(turnover);

        BigDecimal creditSum = new BigDecimal(0);
        BigDecimal debitSum = new BigDecimal(10);
        TransactionsAccounting expectedAccounting = new TransactionsAccounting(creditSum, debitSum);
        TransactionAccount account = new TransactionAccount();

        assertEquals(expectedAccounting, account.accountTransactionsByType(turnoverArrayList, Integer.parseInt(accountNumber)));
    }

    @Test
    public void accountNumberDoesntExistInTurnoverArrayAccountNumber4ExpectedAccountNumber4ResultsFalse() {
        ArrayList<Turnover> turnoverArrayList = new ArrayList<Turnover>();
        String accountNumber = "4";

        String[] splittedLine = new String[5];
        splittedLine[0] = "1";
        splittedLine[1] = "CREDITO";
        splittedLine[2] = "300";
        splittedLine[3] = accountNumber;
        splittedLine[4] = "";

        Turnover turnover = new Turnover(splittedLine);
        turnoverArrayList.add(turnover);

        TransactionAccount account = new TransactionAccount();

        assertEquals(false, account.accountNumberDoesntExistInTurnoverArray(4, turnoverArrayList));
    }

    @Test
    public void accountNumberDoesntExistInTurnoverArrayAccountNumber4ExpectedAccountNumber2ResultsTrue() {
        ArrayList<Turnover> turnoverArrayList = new ArrayList<Turnover>();
        String id = "1";
        String type = "CREDITO";
        String value = "300";
        String accountNumber = "4";

        String[] splittedLine = new String[5];
        splittedLine[0] = id;
        splittedLine[1] = type;
        splittedLine[2] = value;
        splittedLine[3] = accountNumber;
        splittedLine[4] = "";

        Turnover turnover = new Turnover(splittedLine);
        turnoverArrayList.add(turnover);

        TransactionAccount account = new TransactionAccount();
        assertEquals(true, account.accountNumberDoesntExistInTurnoverArray(2, turnoverArrayList));
    }
}