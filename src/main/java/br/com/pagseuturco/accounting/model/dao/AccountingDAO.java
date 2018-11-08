package br.com.pagseuturco.accounting.model.dao;

import br.com.pagseuturco.accounting.model.domain.FinancialTurnoverGennericcard;
import br.com.pagseuturco.accounting.model.domain.FinancialTurnoverTransfer;
import br.com.pagseuturco.accounting.model.domain.Turnover;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.ArrayList;
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
        List<Turnover> turnoverList = new ArrayList<>();

        if (turnoverType == "TRANSFER") {
            turnoverList = findAllTransferType();
        } else if (turnoverType == "BOOKLET") {
            turnoverList = findAllGennericcardType();
        } else if (turnoverType == "GENNERICCARD") {
            turnoverList = findAllBookletType();
        }

        return turnoverList;
    }

    public List<Turnover> findAllTransferType() {
        Connection connection;
        Statement statement;
        List<Turnover> turnoverArrayList = new ArrayList<>();

        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL);
            statement = connection.createStatement();

            String selectQuery = "select * from financial_turnover.transfer;";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                String account = resultSet.getString("account");
                String value = (resultSet.getBigDecimal("value")).toString();
                String type = resultSet.getString("type");
                String date = resultSet.getString("date");

                String[] splittedLine = new String[]{type, value, account, date};
                Turnover turnover = new FinancialTurnoverTransfer(splittedLine);
                turnoverArrayList.add(turnover);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return turnoverArrayList;
    }

    public List<Turnover> findAllGennericcardType() {
        Connection connection;
        Statement statement;
        List<Turnover> turnoverArrayList = new ArrayList<>();

        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL);
            statement = connection.createStatement();

            String selectQuery = "select * from financial_turnover.gennericcard;";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                String cardHash = resultSet.getString("cardhash");
                String type = resultSet.getString("type");
                String value = (resultSet.getBigDecimal("value")).toString();
                String account = Integer.toString(resultSet.getInt("account"));
                String date = resultSet.getString("date");

                String[] splittedLine = new String[]{cardHash, type, value, account, date};
                Turnover turnover = new FinancialTurnoverGennericcard(splittedLine);
                turnoverArrayList.add(turnover);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return turnoverArrayList;
    }

    public List<Turnover> findAllBookletType() {
        Connection connection;
        Statement statement;
        List<Turnover> turnoverArrayList = new ArrayList<>();

        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL);
            statement = connection.createStatement();

            String selectQuery = "select * from financial_turnover.booklet;";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String identification = resultSet.getString("type");
                String value = (resultSet.getBigDecimal("value")).toString();
                String account = Integer.toString(resultSet.getInt("account"));
                String date = resultSet.getString("date");

                String[] splittedLine = new String[]{name, identification, value, account, date};
                Turnover turnover = new FinancialTurnoverTransfer(splittedLine);
                turnoverArrayList.add(turnover);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return turnoverArrayList;
    }


    private void saveFinancialTurnoverTransfer(Turnover turnover) {

        Connection connect;
        PreparedStatement preparedStatement;

        try {

            Class.forName(jdbcDriver);
            connect = DriverManager.getConnection(jdbcURL);

            preparedStatement = connect.prepareStatement("insert into financial_turnover.transfer(id, account, value, type, date) values(default,?,?,?,?)");
            preparedStatement.setInt(1, turnover.getAccount());
            preparedStatement.setBigDecimal(2, turnover.getValue());
            preparedStatement.setString(3, turnover.getType());
            preparedStatement.setString(4,turnover.getDate());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private void saveFinancialTurnoverBooklet(Turnover turnover) throws SQLException, ClassNotFoundException {
        Connection connect;
        PreparedStatement preparedStatement;

        BigDecimal bookletValueSubtractTax = (turnover.getValue()).subtract(BOOKLET_TAX);

        try {

            Class.forName(jdbcDriver);
            connect = DriverManager.getConnection(jdbcURL);

            preparedStatement = connect.prepareStatement("insert into financial_turnover.booklet values(default,?,?,?,?,?)");
            preparedStatement.setString(1, turnover.getName());
            preparedStatement.setString(2, turnover.getDocumentNumber());
            preparedStatement.setBigDecimal(3, bookletValueSubtractTax);
            preparedStatement.setInt(4, turnover.getAccount());
            preparedStatement.setString(5, turnover.getDate());
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

            preparedStatement = connect.prepareStatement("insert into financial_turnover.gennericcard values(default,?,?,?,?,?)");
            preparedStatement.setString(1, turnover.getCardsHash());
            preparedStatement.setString(2, turnover.getType());
            preparedStatement.setBigDecimal(3, gennericcardValueSubtractTax.setScale(2, RoundingMode.DOWN));
            preparedStatement.setInt(4, turnover.getAccount());
            preparedStatement.setString(5, turnover.getDate());
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

    public BigDecimal calculateTaxByTransactionType(String type, BigDecimal value) {
        if (type.equals("CREDIT")) {
            return value.multiply(CREDIT_TAX);
        } else if (type.equals("DEBIT")) {
            return value.multiply(DEBIT_TAX);
        } else {
            throw new RuntimeException();
        }
    }
}