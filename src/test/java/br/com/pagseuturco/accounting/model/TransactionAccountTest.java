package br.com.pagseuturco.accounting.model;

import br.com.pagseuturco.accounting.model.domain.FinancialTurnoverFactory;
import br.com.pagseuturco.accounting.model.domain.FinancialTurnoverTransfer;
import br.com.pagseuturco.accounting.model.domain.TransactionAccount;
import br.com.pagseuturco.accounting.model.domain.Turnover;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
        assertEquals("TRANSFER", account.identifyFileByType(transferHeader));
    }

    @Test(expected = IllegalArgumentException.class)
    public void identifyFinancialTurnoverByTypeEnterInexistenceHeaderExpectedNull() {
        final String transferHeader = "tipo;valor_movimentacao;conta;data_transacao";

        TransactionAccount account = new TransactionAccount();
        assertNull(account.identifyFileByType(transferHeader));
    }

    @Test
    public void transformIntoTurnoverListAllFieldsComplete() {
        List<String> financialTurnoverDocument = new ArrayList<>();
        financialTurnoverDocument.add("tipo;valor;conta;data_transacao\n");
        financialTurnoverDocument.add("DEBITO;130.55;101;15/08/2018");
        String turnoverType = "TRANSFER";
        TransactionAccount account = new TransactionAccount();

        List<Turnover> expectedData = new ArrayList<Turnover>();
        String[] splittedLine = new String[] {"DEBITO", "130.55", "101", "15/08/2018"};
        FinancialTurnoverFactory financialTurnoverFactory = new FinancialTurnoverFactory();
        Turnover financialTurnover = financialTurnoverFactory.build(turnoverType,splittedLine);
        expectedData.add(financialTurnover);

        assertEquals(expectedData, account.transformFileIntoTurnoverList(turnoverType, financialTurnoverDocument));
    }

    @Test
    public void transformIntoTurnoverListMissingAccountField() {
        List<String> financialTurnoverDocument = new ArrayList<>();
        financialTurnoverDocument.add("tipo;valor;conta;data_transacao\n");
        financialTurnoverDocument.add("DEBITO;130.55;;15/08/2018");
        String turnoverType = "TRANSFER";
        TransactionAccount account = new TransactionAccount();

        List<Turnover> expectedData = new ArrayList<Turnover>();
        String[] splittedLine = new String[] {"DEBITO", "130.55", "", "15/08/2018"};
        FinancialTurnoverFactory financialTurnoverFactory = new FinancialTurnoverFactory();
        Turnover financialTurnover = financialTurnoverFactory.build(turnoverType,splittedLine);
        expectedData.add(financialTurnover);

        assertEquals(expectedData, account.transformFileIntoTurnoverList(turnoverType, financialTurnoverDocument));
    }

    @Test
    public void transformIntoTurnoverListMissingAllFields() {
        List<String> financialTurnoverDocument = new ArrayList<>();
        financialTurnoverDocument.add("tipo;valor;conta;data_transacao\n");
        financialTurnoverDocument.add(";;;");
        String turnoverType = "TRANSFER";
        TransactionAccount account = new TransactionAccount();

        List<Turnover> expectedData = new ArrayList<Turnover>();
        String[] splittedLine = new String[] {"", "", "", ""};
        FinancialTurnoverFactory financialTurnoverFactory = new FinancialTurnoverFactory();
        Turnover financialTurnover = financialTurnoverFactory.build(turnoverType,splittedLine);
        expectedData.add(financialTurnover);

        assertEquals(expectedData, account.transformFileIntoTurnoverList(turnoverType, financialTurnoverDocument));
    }

    @Test
    public void processFileTransferTypeIntoTurnoverList() {
        List<String> fileIntoList = new ArrayList<>();
        fileIntoList.add("tipo;valor;conta;data_transacao");
        fileIntoList.add("DEBITO;130.55;101;15/08/2018");
        TransactionAccount account = new TransactionAccount();

        List<Turnover> expectedData;
        String turnoverType = "TRANSFER";
        expectedData = account.transformFileIntoTurnoverList(turnoverType,fileIntoList);

        assertEquals(expectedData, account.processFile(fileIntoList));

    }

    @Test
    public void processFileMissingAllFields() {
        List<String> fileIntoList = new ArrayList<>();
        fileIntoList.add("tipo;valor;conta;data_transacao");
        fileIntoList.add(";;;");
        TransactionAccount account = new TransactionAccount();

        String turnoverType = "TRANSFER";
        List<Turnover> expectedData = account.transformFileIntoTurnoverList(turnoverType,fileIntoList);

        assertEquals(expectedData, account.processFile(fileIntoList));

    }

    @Test
    public void transformIntoTurnoverListTransferTypeAllFieldsComplete() {
        List<String> fileIntoList = new ArrayList<>();
        fileIntoList.add("tipo;valor;conta;data_transacao");
        fileIntoList.add("DEBITO;130.55;101;15/08/2018");
        String turnoverType = "TRANSFER";
        TransactionAccount account = new TransactionAccount();

        List<Turnover> expectedData = new ArrayList<Turnover>();
        String[] splittedLine = new String[] {"DEBITO", "130.55", "101", "15/08/2018"};

        FinancialTurnoverTransfer financialTurnoverTransfer = new FinancialTurnoverTransfer(splittedLine);
        expectedData.add(financialTurnoverTransfer);

        assertEquals(expectedData, account.transformFileIntoTurnoverList(turnoverType,fileIntoList));

    }

    @Test(expected = NullPointerException.class)
    public void transformIntoTurnoverListEnterANullListExpectedNullReturn() {
        String turnoverType = "TRANSFER";
        TransactionAccount account = new TransactionAccount();

        assertNull(account.transformFileIntoTurnoverList(turnoverType, null));
    }

    @Test
    public void identifyTurnoverByTypeGennericcardType() {
        String gennericcardHeader = "hash_cartao;tipo_transacao;valor;conta;data_transacao";
        TransactionAccount account = new TransactionAccount();

        String turnoverType = "GENNERICCARD";

        assertEquals(turnoverType, account.identifyFileByType(gennericcardHeader));

    }

    @Test(expected = IllegalArgumentException.class)
    public void identifyTurnoverByTypeIndeterminatedType() {
        String indeterminatedHeader = "tipo;nome;conta;data_transacao";
        TransactionAccount account = new TransactionAccount();

        assertNull(account.identifyFileByType(indeterminatedHeader));

    }
}