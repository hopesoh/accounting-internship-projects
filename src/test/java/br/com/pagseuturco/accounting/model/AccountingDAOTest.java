package br.com.pagseuturco.accounting.model;

import br.com.pagseuturco.accounting.model.data.AccountingDAO;
import br.com.pagseuturco.accounting.model.data.FinancialTurnoverTransfer;
import br.com.pagseuturco.accounting.model.data.Turnover;
import org.h2.engine.Database;
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
    public static final String CREATE_TABLE_TRANSFER = "create table FINANCIAL_TURNOVER.transfer( id number, account number not null, value number,  date varchar(20), type varchar(100) )";


    @Test
    public void accountingDAO() throws SQLException, ClassNotFoundException {
        createTable(DB_DRIVER, DB_CONNECTION, CREATE_DATABASE);
        createTable(DB_DRIVER, DB_CONNECTION, CREATE_TABLE_TRANSFER);

        AccountingDAO accountingDAO = new AccountingDAO(DB_DRIVER, DB_CONNECTION);
        List<Turnover> turnoverList = new ArrayList<>();
        String[] splittedLine = {"DEBITO", "130.55", "101", "15/08/2018"};
        String turnoverType = "TRANSFER";

        FinancialTurnoverTransfer financialTurnoverTransfer = new FinancialTurnoverTransfer(splittedLine);
        turnoverList.add(financialTurnoverTransfer);
        accountingDAO.saveFinancialTurnover(turnoverList);

        List<Turnover> turnoverFoundIntoDatabase = accountingDAO.findAll(turnoverType);
        Turnover foundTurnover = turnoverFoundIntoDatabase.get(0);

        assertEquals(foundTurnover.getName(), financialTurnoverTransfer.getName());
    }



    private void createTable(String jdbcDriver, String jdbcUrl, String createTableSql) {
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