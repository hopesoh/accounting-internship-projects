package br.com.pagseuturco.accounting.model.dao.mapper;

import br.com.pagseuturco.accounting.model.domain.FinancialTurnoverGennericcard;
import br.com.pagseuturco.accounting.model.domain.Turnover;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GennericcardSQLMapper implements SQLMapper, SQLPagSeuTurcoMapper {
    private static String INSERT_QUERY = "insert into financial_turnover.gennericcard(id,cardhash,type,value,account,date) values(default,?,?,?,?,?)";
    private static String SELECT_QUERY = "select * from financial_turnover.gennericcard;";
    private static String PAGSEUTURCO_INSERT_QUERY = "insert into financial_turnover.pagseuturco_account(id,account,value,type,date) values(default,?,?,?,?)";
    private static final BigDecimal CREDIT_TAX = new BigDecimal(0.02);
    private static final BigDecimal DEBIT_TAX = new BigDecimal(0.035);

    @Override
    public PreparedStatement createInsertPreparedStatement(Connection connect, Turnover turnover) {

        try {
            FinancialTurnoverGennericcard gennericcard = (FinancialTurnoverGennericcard) turnover;

            BigDecimal valueToAccountNumberOne = calculateTaxByTransactionType(gennericcard.getType(), gennericcard.getValue());
            BigDecimal gennericcardValueSubtractTax = (gennericcard.getValue()).subtract(valueToAccountNumberOne);

            PreparedStatement preparedStatement = connect.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, gennericcard.getCardsHash());
            preparedStatement.setString(2, gennericcard.getType());
            preparedStatement.setBigDecimal(3, gennericcardValueSubtractTax.setScale(2, RoundingMode.DOWN));
            preparedStatement.setInt(4,gennericcard.getAccount());
            preparedStatement.setString(5, gennericcard.getDate());
            preparedStatement.executeUpdate();
            return preparedStatement;

        } catch (SQLException e){
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
                String cardHash = resultSet.getString("cardhash");
                String type = resultSet.getString("type");
                String value = (resultSet.getBigDecimal("value")).toString();
                String account = Integer.toString(resultSet.getInt("account"));
                String date = resultSet.getString("date");

                String[] splittedLine = new String[]{cardHash, type, value, account, date};
                Turnover financialTurnoverGennericcard = new FinancialTurnoverGennericcard(splittedLine);
                turnoverArrayList.add(financialTurnoverGennericcard);
            }
            return turnoverArrayList;
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private BigDecimal calculateTaxByTransactionType(String type, BigDecimal value) {
        if (type == "CREDIT") {
            return value.multiply(CREDIT_TAX);
        } else {
            return value.multiply(DEBIT_TAX);
        }
    }

    @Override
    public PreparedStatement createInsertPagSeuTurcoPreparedStatement(Connection connect, Turnover turnover) {
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(PAGSEUTURCO_INSERT_QUERY);

            FinancialTurnoverGennericcard gennericcard = (FinancialTurnoverGennericcard) turnover;
            BigDecimal valueToAccountNumberOne = calculateTaxByTransactionType(gennericcard.getType(), gennericcard.getValue());

            preparedStatement.setInt(1, turnover.getAccount());
            preparedStatement.setBigDecimal(2, valueToAccountNumberOne.setScale(2, RoundingMode.DOWN));
            preparedStatement.setString(3, turnover.getTurnoverType());
            preparedStatement.setString(4, turnover.getDate());

            return preparedStatement;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
