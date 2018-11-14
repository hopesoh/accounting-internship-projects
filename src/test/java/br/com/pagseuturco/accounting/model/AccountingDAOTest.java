package br.com.pagseuturco.accounting.model;

import br.com.pagseuturco.accounting.model.domain.*;
import br.com.pagseuturco.accounting.model.dao.AccountingDAO;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AccountingDAOTest {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

    public static final String CREATE_DATABASE = "create SCHEMA FINANCIAL_TURNOVER;";
    public static final String CREATE_TABLE_TRANSFER = "create table IF NOT EXISTS FINANCIAL_TURNOVER.transfer( id number, account number not null, value number,  date varchar(20), type varchar(100) )";
    public static final String CLEAN_TABLE = "DROP table IF EXISTS FINANCIAL_TURNOVER.transfer;";


    @BeforeClass
    public static void saladaMista() {
        createTable(DB_DRIVER, DB_CONNECTION, CREATE_DATABASE);
    }

    @Before
    public void peraUva() {
        createTable(DB_DRIVER, DB_CONNECTION, CLEAN_TABLE);
        createTable(DB_DRIVER, DB_CONNECTION, CREATE_TABLE_TRANSFER);
    }

    @Test (expected = RuntimeException.class)
    public void saveAFinancialTurnoverTransferTypeIntoDatabaseMissingAccountField() throws SQLException, ClassNotFoundException {
        AccountingDAO accountingDAO = new AccountingDAO(DB_DRIVER, DB_CONNECTION);
        List<Turnover> turnoverList = new ArrayList<>();
        String[] splittedLine = {"DEBITO", "130.55", "", "15/08/2018"};
        String turnoverType = "TRANSFER";

        FinancialTurnoverTransfer financialTurnoverTransfer = new FinancialTurnoverTransfer(splittedLine);
        turnoverList.add(financialTurnoverTransfer);
        accountingDAO.saveFinancialTurnover(turnoverList);

        List<Turnover> turnoverFoundIntoDatabase = accountingDAO.findAll(turnoverType);
        Turnover foundTurnover = turnoverFoundIntoDatabase.get(0);

        assertEquals(true, foundTurnover.equals(financialTurnoverTransfer));
    }


    static void createTable(String jdbcDriver, String jdbcUrl, String createTableSql) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(jdbcUrl);

            stmt = conn.createStatement();
            stmt.executeUpdate(createTableSql);

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }

            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}