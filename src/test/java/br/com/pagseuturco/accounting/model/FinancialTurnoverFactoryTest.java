package br.com.pagseuturco.accounting.model;

import br.com.pagseuturco.accounting.model.domain.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FinancialTurnoverFactoryTest {
    @Test
    public void buildFinancialTurnoverTransferType() {
        String turnoverType = "TRANSFER";
        String[] splittedLine = new String[] {"DEBITO", "130.55", "101", "15/08/2018"};
        FinancialTurnoverFactory financialTurnoverFactory = new FinancialTurnoverFactory();

        FinancialTurnoverTransfer financialTurnoverTransfer = new FinancialTurnoverTransfer(splittedLine);
        assertEquals(financialTurnoverTransfer, financialTurnoverFactory.build(turnoverType, splittedLine));
    }

    @Test
    public void buildFinancialTurnoverTransferTypeMissingAccountField() {
        String turnoverType = "TRANSFER";
        String[] splittedLine = new String[] {"DEBITO", "130.55", "", "15/08/2018"};
        FinancialTurnoverFactory financialTurnoverFactory = new FinancialTurnoverFactory();

        FinancialTurnoverTransfer financialTurnoverTransfer = new FinancialTurnoverTransfer(splittedLine);
        assertEquals(financialTurnoverTransfer, financialTurnoverFactory.build(turnoverType, splittedLine));
    }

    @Test
    public void buildFinancialTurnoverTransferTypeMissingAllFields() {
        String turnoverType = "TRANSFER";
        String[] splittedLine = new String[] {"", "", "", ""};
        FinancialTurnoverFactory financialTurnoverFactory = new FinancialTurnoverFactory();

        FinancialTurnoverTransfer financialTurnoverTransfer = new FinancialTurnoverTransfer(splittedLine);
        assertEquals(financialTurnoverTransfer, financialTurnoverFactory.build(turnoverType, splittedLine));
    }

    @Test(expected = NullPointerException.class)
    public void buildFinancialTurnoverTransferTypeNullCase()  {
        String[] splittedLine = new String[] {"DEBITO", "130.55", "101", "15/08/2018"};
        FinancialTurnoverFactory financialTurnoverFactory = new FinancialTurnoverFactory();

        assertNull(financialTurnoverFactory.build(null, splittedLine));
    }

    @Test(expected = NullPointerException.class)
    public void buildFinancialTurnoverTransferTypeSplittedLineNullCase() {
        String turnoverType = "TRANSFER";
        FinancialTurnoverFactory financialTurnoverFactory = new FinancialTurnoverFactory();

        assertNull(financialTurnoverFactory.build(turnoverType, null));
    }
}
