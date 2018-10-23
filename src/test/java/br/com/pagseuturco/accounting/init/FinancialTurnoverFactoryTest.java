package br.com.pagseuturco.accounting.init;

import br.com.pagseuturco.accounting.data.*;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class FinancialTurnoverFactoryTest {
    @Test
    public void buildFinancialTurnoverTransferType() {
        String turnoverType = "TRANSFER";
        String[] splittedLine = new String[4];
        splittedLine[0] = "DEBITO";
        splittedLine[1] = "130.55";
        splittedLine[2] = "101";
        splittedLine[3] = "15/08/2018";
        FinancialTurnoverFactory financialTurnoverFactory = new FinancialTurnoverFactory();

        FinancialTurnoverTransfer financialTurnoverTransfer = new FinancialTurnoverTransfer(splittedLine);
        assertEquals(financialTurnoverTransfer, financialTurnoverFactory.build(turnoverType, splittedLine));
    }

    @Test
    public void buildFinancialTurnoverTransferTypeMissingAccountField() {
        String turnoverType = "TRANSFER";
        String[] splittedLine = new String[4];
        splittedLine[0] = "DEBITO";
        splittedLine[1] = "130.55";
        splittedLine[2] = "";
        splittedLine[3] = "15/08/2018";
        FinancialTurnoverFactory financialTurnoverFactory = new FinancialTurnoverFactory();

        FinancialTurnoverTransfer financialTurnoverTransfer = new FinancialTurnoverTransfer(splittedLine);
        assertEquals(financialTurnoverTransfer, financialTurnoverFactory.build(turnoverType, splittedLine));
    }

    @Test
    public void buildFinancialTurnoverTransferTypeMissingAllFields() {
        String turnoverType = "TRANSFER";
        String[] splittedLine = new String[4];
        splittedLine[0] = "";
        splittedLine[1] = "";
        splittedLine[2] = "";
        splittedLine[3] = "";
        FinancialTurnoverFactory financialTurnoverFactory = new FinancialTurnoverFactory();

        FinancialTurnoverTransfer financialTurnoverTransfer = new FinancialTurnoverTransfer(splittedLine);
        assertEquals(financialTurnoverTransfer, financialTurnoverFactory.build(turnoverType, splittedLine));
    }

    @Test
    public void buildFinancialTurnoverTransferTypeNullCase() {
        String turnoverType = null;
        String[] splittedLine = new String[4];
        splittedLine[0] = "DEBITO";
        splittedLine[1] = "130.55";
        splittedLine[2] = "101";
        splittedLine[3] = "15/08/2018";
        FinancialTurnoverFactory financialTurnoverFactory = new FinancialTurnoverFactory();

        assertEquals(null, financialTurnoverFactory.build(null, splittedLine));
    }

    @Test
    public void buildFinancialTurnoverTransferTypeSplittedLineNullCase() {
        String turnoverType = "TRANSFER";
        String[] splittedLine = null;
        FinancialTurnoverFactory financialTurnoverFactory = new FinancialTurnoverFactory();

        assertEquals(null, financialTurnoverFactory.build(turnoverType, null));
    }
}
