package br.com.pagseuturco.accounting.model.dao.mapper;

import br.com.pagseuturco.accounting.model.domain.FinancialTurnoverBooklet;
import br.com.pagseuturco.accounting.model.domain.Turnover;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookletSQLMapper implements SQLMapper, SQLPagSeuTurcoMapper {
    private static final BigDecimal BOOKLET_TAX = new BigDecimal(0.25);
    private static String INSERT_QUERY = "insert into financial_turnover.booklet(id,name,documentNumber,value,account,date) values(default,?,?,?,?,?)";
    private static String SELECT_QUERY = "select * from financial_turnover.booklet;";
    private static String PAGSEUTURCO_INSERT_QUERY = "insert into financial_turnover.pagseuturco_account(id,account,value,type,date) values(default,?,?,?,?)";

    @Override
    public PreparedStatement createInsertPreparedStatement(Connection connect, Turnover turnover) {

        BigDecimal bookletValueSubtractTax = (turnover.getValue()).subtract(BOOKLET_TAX);
        try {
            FinancialTurnoverBooklet booklet = (FinancialTurnoverBooklet) turnover;
            PreparedStatement preparedStatement = connect.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, booklet.getName());
            preparedStatement.setString(2, booklet.getDocumentNumber());
            preparedStatement.setBigDecimal(3, bookletValueSubtractTax);
            preparedStatement.setInt(4, booklet.getAccount());
            preparedStatement.setString(5, booklet.getDate());
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
                String name = resultSet.getString("name");
                String documentNumber = resultSet.getString("type");
                String value = (resultSet.getBigDecimal("value")).toString();
                String account = Integer.toString(resultSet.getInt("account"));
                String date = resultSet.getString("date");

                String[] splittedLine = new String[]{name, documentNumber, value, account, date};
                Turnover financialTurnoverBooklet = new FinancialTurnoverBooklet(splittedLine);
                turnoverArrayList.add(financialTurnoverBooklet);
            }
            return turnoverArrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public PreparedStatement createInsertPagSeuTurcoPreparedStatement(Connection connect, Turnover turnover) {
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(PAGSEUTURCO_INSERT_QUERY);

            preparedStatement.setInt(1, turnover.getAccount());
            preparedStatement.setBigDecimal(2, BOOKLET_TAX);
            preparedStatement.setString(3, turnover.getTurnoverType());
            preparedStatement.setString(4, turnover.getDate());

            return preparedStatement;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
