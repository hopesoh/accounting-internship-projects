package br.com.pagseuturco.accounting.model.dao;

import br.com.pagseuturco.accounting.model.domain.FinancialTurnoverGennericcard;
import br.com.pagseuturco.accounting.model.domain.FinancialTurnoverTransfer;
import br.com.pagseuturco.accounting.model.domain.Turnover;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountingDAO {

    private String jdbcURL;

    public AccountingDAO(String jdbcDriver, String jdbcURL) {
        try {
            Class.forName(jdbcDriver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.jdbcURL = jdbcURL;
    }

    public void saveFinancialTurnover(List<Turnover> turnoverList) {
        try {
            Connection connect = DriverManager.getConnection(jdbcURL);

            for (Turnover turnover : turnoverList) {
                PreparedStatement insertPreparedStatement = turnover.getSQLMapper().createInsertPreparedStatement(connect, turnover);
                insertPreparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException();
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turnoverArrayList;
    }

    public List<Turnover> findAllGennericcardType() {
        Connection connection;
        Statement statement;
        List<Turnover> turnoverArrayList = new ArrayList<>();

        try {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turnoverArrayList;
    }

    public List<Turnover> findAllBookletType() {
        Connection connection;
        Statement statement;
        List<Turnover> turnoverArrayList = new ArrayList<>();

        try {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turnoverArrayList;
    }

}