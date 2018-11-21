package br.com.pagseuturco.accounting.model.dao.mapper;

import br.com.pagseuturco.accounting.model.domain.FinancialTurnoverTransfer;
import br.com.pagseuturco.accounting.model.domain.Turnover;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransferSQLMapper implements SQLMapper {

    private static String INSERT_QUERY = "insert into financial_turnover.transfer(id,account,value,type,date) values(default,?,?,?,?)";
    private static String SELECT_QUERY = "select * from financial_turnover.transfer;";

    @Override
    public PreparedStatement createInsertPreparedStatement(Connection connect, Turnover turnover) {
        try {
            FinancialTurnoverTransfer transfer = (FinancialTurnoverTransfer) turnover;
            PreparedStatement preparedStatement = connect.prepareStatement(INSERT_QUERY);
            preparedStatement.setInt(1, transfer.getAccount());
            preparedStatement.setBigDecimal(2, transfer.getValue());
            preparedStatement.setString(3, transfer.getType());
            preparedStatement.setString(4,transfer.getDate());
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Turnover> createFindAllStatement(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY);

            List<Turnover> turnoverArrayList = new ArrayList<>();
            while (resultSet.next()) {
                String account = resultSet.getString("account");
                String value = (resultSet.getBigDecimal("value")).toString();
                String type = resultSet.getString("type");
                String date = resultSet.getString("date");

                String[] splittedLine = new String[]{type, value, account, date};
                Turnover financialTurnoverTransfer = new FinancialTurnoverTransfer(splittedLine);
                turnoverArrayList.add(financialTurnoverTransfer);
            }
            return turnoverArrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
