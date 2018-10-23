package br.com.pagseuturco.accounting.init;

import br.com.pagseuturco.accounting.data.FinancialTurnoverFactory;
import br.com.pagseuturco.accounting.data.TransactionsAccounting;
import br.com.pagseuturco.accounting.data.Turnover;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TransactionAccountTest {
    @Test
    public void transformFileIntoListAllFieldsComplete() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader("DEBITO;130.55;101;15/08/2018"));

        ArrayList<String> splittedLine = new ArrayList<String>();
        splittedLine.add("DEBITO;130.55;101;15/08/2018");
        TransactionAccount account = new TransactionAccount();
        assertEquals(splittedLine, account.transformFileIntoList(reader));
    }

    @Test
    public void transformFileIntoListMissingTurnoverLine() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader(""));

        ArrayList<String> splittedLine = new ArrayList<String>();
        TransactionAccount account = new TransactionAccount();
        assertEquals(splittedLine, account.transformFileIntoList(reader));
    }

    @Test
    public void identifyFinancialTurnoverByTypeExpectedEnterTransferHeaderExpectedTransferType() {
        final String transferHeader = "tipo;valor;conta;data_transacao";

        TransactionAccount account = new TransactionAccount();
        assertEquals("TRANSFER", account.identifyTurnoverByType(transferHeader));
    }

    @Test
    public void identifyFinancialTurnoverByTypeEnterInexistenceHeaderExpectedNull() {
        final String transferHeader = "tipo;valor_movimentacao;conta;data_transacao";

        TransactionAccount account = new TransactionAccount();
        assertEquals(null, account.identifyTurnoverByType(transferHeader));
    }

    @Test
    public void transformIntoTurnoverListAllFieldsComplete() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader("tipo;valor;conta;data_transacao\n" +
                "DEBITO;130.55;101;15/08/2018"));
        String turnoverType = "TRANSFER";
        String header = "tipo;valor;conta;data_transacao";
        TransactionAccount account = new TransactionAccount();

        ArrayList<Turnover> expectedData = new ArrayList<Turnover>();
        String[] splittedLine = new String[4];
        splittedLine[0] = "DEBITO";
        splittedLine[1] = "130.55";
        splittedLine[2] = "101";
        splittedLine[3] = "15/08/2018";
        FinancialTurnoverFactory financialTurnoverFactory = new FinancialTurnoverFactory();
        Turnover financialTurnover = financialTurnoverFactory.build(turnoverType,splittedLine);
        expectedData.add(financialTurnover);

        assertEquals(expectedData, account.transformIntoTurnoverList(turnoverType, reader, header));
    }

    @Test
    public void transformIntoTurnoverListMissingAccountField() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader("tipo;valor;conta;data_transacao\n" +
                "DEBITO;130.55;;15/08/2018"));
        String turnoverType = "TRANSFER";
        String header = "tipo;valor;conta;data_transacao";
        TransactionAccount account = new TransactionAccount();

        ArrayList<Turnover> expectedData = new ArrayList<Turnover>();
        String[] splittedLine = new String[4];
        splittedLine[0] = "DEBITO";
        splittedLine[1] = "130.55";
        splittedLine[2] = "";
        splittedLine[3] = "15/08/2018";
        FinancialTurnoverFactory financialTurnoverFactory = new FinancialTurnoverFactory();
        Turnover financialTurnover = financialTurnoverFactory.build(turnoverType,splittedLine);
        expectedData.add(financialTurnover);

        assertEquals(expectedData, account.transformIntoTurnoverList(turnoverType, reader, header));
    }

    @Test
    public void transformIntoTurnoverListMissingAllFields() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader("tipo;valor;conta;data_transacao\n" +
                ";;;"));
        String turnoverType = "TRANSFER";
        String header = "tipo;valor;conta;data_transacao";
        TransactionAccount account = new TransactionAccount();

        ArrayList<Turnover> expectedData = new ArrayList<Turnover>();
        String[] splittedLine = new String[4];
        splittedLine[0] = "";
        splittedLine[1] = "";
        splittedLine[2] = "";
        splittedLine[3] = "";
        FinancialTurnoverFactory financialTurnoverFactory = new FinancialTurnoverFactory();
        Turnover financialTurnover = financialTurnoverFactory.build(turnoverType,splittedLine);
        expectedData.add(financialTurnover);

        assertEquals(expectedData, account.transformIntoTurnoverList(turnoverType, reader, header));
    }

}