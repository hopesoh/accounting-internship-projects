package br.com.pagseuturco.accounting.model.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.List;

public class AccountingDAO {

    private static final BigDecimal BOOKLET_TAX = new BigDecimal(0.25);
    private static final BigDecimal CREDIT_TAX = new BigDecimal(0.02);
    private static final BigDecimal DEBIT_TAX = new BigDecimal(0.035);

    private String jdbcDriver;
    private String jdbcURL;

    public AccountingDAO(String jdbcDriver, String jdbcURL) {
        this.jdbcDriver = jdbcDriver;
        this.jdbcURL = jdbcURL;
    }

    public void saveFinancialTurnover(List<Turnover> turnoverList) throws SQLException, ClassNotFoundException {

        for (Turnover turnover : turnoverList) {
            if (turnover.getTurnoverType() == "TRANSFER") {
                saveFinancialTurnoverTransfer(turnover);

            } else if (turnover.getTurnoverType() == "BOOKLET") {
                saveFinancialTurnoverBooklet(turnover);

            } else if (turnover.getTurnoverType() == "GENNERICCARD") {
                saveFinancialTurnoverGennericcard(turnover);

            }
        }

    }

    public List<Turnover> findAll(String turnoverType) {
        Connection connect;

        //seleciona tudo que está no banco
        try {

            Class.forName(jdbcDriver);
            connect = DriverManager.getConnection(jdbcURL);
            Statement stmt = connect.createStatement();

            String stringSelect = "select * from financial_turnover.transfer;";

            ResultSet rset = stmt.executeQuery(stringSelect);

            int rowCount = 0;
            while (rset.next()) {
                String title = rset.getString("account");
                BigDecimal value = rset.getBigDecimal("value");
                String type = rset.getString("type");
                String date = rset.getString("date");


                ++rowCount;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void saveFinancialTurnoverTransfer(Turnover turnover) throws SQLException, ClassNotFoundException {

        Connection connect;
        PreparedStatement preparedStatement;

        try {

            Class.forName(jdbcDriver);
            connect = DriverManager.getConnection(jdbcURL);

            preparedStatement = connect.prepareStatement("insert into financial_turnover.transfer values(default,?,?,?,?)");
            preparedStatement.setInt(1, turnover.getAccount());
            preparedStatement.setBigDecimal(2, turnover.getValue());
            preparedStatement.setString(3, turnover.getType());
            preparedStatement.setString(4,turnover.getDate());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw e;
        }
    }

    private void saveFinancialTurnoverBooklet(Turnover turnover) throws SQLException, ClassNotFoundException {
        Connection connect;
        PreparedStatement preparedStatement;

        BigDecimal bookletValueSubtractTax = (turnover.getValue()).subtract(BOOKLET_TAX);

        try {

            Class.forName(jdbcDriver);
            connect = DriverManager.getConnection(jdbcURL);

            preparedStatement = connect.prepareStatement("insert into financial_turnover.booklet values(default,?,?,?,?)");
            preparedStatement.setInt(1, turnover.getAccount());
            preparedStatement.setBigDecimal(2, bookletValueSubtractTax);
            preparedStatement.setString(3, turnover.getName());
            preparedStatement.setString(4, turnover.getDate());
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("insert into financial_turnover.pagseuturco_account values(default,?,?,?,?)");
            preparedStatement.setInt(1, turnover.getAccount());
            preparedStatement.setBigDecimal(2, BOOKLET_TAX);
            preparedStatement.setString(3, "Booklet");
            preparedStatement.setString(4, turnover.getDate());
            preparedStatement.executeUpdate();


        } catch (Exception e) {
            throw e;
        }
    }

    private void saveFinancialTurnoverGennericcard(Turnover turnover) throws SQLException, ClassNotFoundException {
        Connection connect;
        PreparedStatement preparedStatement;

        BigDecimal valueToAccountNumberOne = calculateTaxByTransactionType(turnover.getType(), turnover.getValue());
        BigDecimal gennericcardValueSubtractTax = (turnover.getValue()).subtract(valueToAccountNumberOne);

        try {

            Class.forName(jdbcDriver);
            connect = DriverManager.getConnection(jdbcURL);

            preparedStatement = connect.prepareStatement("insert into financial_turnover.gennericcard values(default,?,?,?,?)");
            preparedStatement.setInt(1, turnover.getAccount());
            preparedStatement.setBigDecimal(2, gennericcardValueSubtractTax.setScale(2, RoundingMode.DOWN));
            preparedStatement.setString(3, turnover.getType());
            preparedStatement.setString(4, turnover.getDate());
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("insert into financial_turnover.pagseuturco_account values(default,?,?,?,?)");
            preparedStatement.setInt(1,turnover.getAccount());
            preparedStatement.setBigDecimal(2,valueToAccountNumberOne.setScale(2, RoundingMode.DOWN));
            preparedStatement.setString(3, "Gennericard");
            preparedStatement.setString(4, turnover.getDate());
            preparedStatement.executeUpdate();


        } catch (Exception e) {
            throw e;
        }
    }

    private BigDecimal calculateTaxByTransactionType(String type, BigDecimal value) {
        if (type == "CREDIT") {
            return value.multiply(CREDIT_TAX);
        } else {
            return value.multiply(DEBIT_TAX);
        }
    }

}