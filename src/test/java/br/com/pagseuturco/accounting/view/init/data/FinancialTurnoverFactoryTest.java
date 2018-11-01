package br.com.pagseuturco.accounting.view.init.data;

import br.com.pagseuturco.accounting.model.data.*;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class FinancialTurnoverFactoryTest {
    @Test
    public void buildFinancialTurnoverTransferType() throws SQLException, ClassNotFoundException {
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
    public void buildFinancialTurnoverTransferTypeMissingAccountField() throws SQLException, ClassNotFoundException {
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
    public void buildFinancialTurnoverTransferTypeMissingAllFields() throws SQLException, ClassNotFoundException {
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

    @Test(expected = NullPointerException.class)
    public void buildFinancialTurnoverTransferTypeNullCase() throws SQLException, ClassNotFoundException {
        String turnoverType = null;
        String[] splittedLine = new String[4];
        splittedLine[0] = "DEBITO";
        splittedLine[1] = "130.55";
        splittedLine[2] = "101";
        splittedLine[3] = "15/08/2018";
        FinancialTurnoverFactory financialTurnoverFactory = new FinancialTurnoverFactory();

        assertEquals(null, financialTurnoverFactory.build(null, splittedLine));
    }

    @Test(expected = NullPointerException.class)
    public void buildFinancialTurnoverTransferTypeSplittedLineNullCase() throws SQLException, ClassNotFoundException {
        String turnoverType = "TRANSFER";
        String[] splittedLine = null;
        FinancialTurnoverFactory financialTurnoverFactory = new FinancialTurnoverFactory();

        assertEquals(null, financialTurnoverFactory.build(turnoverType, null));
    }
}
