package br.com.pagseuturco.accounting.model.dao.mapper;

import br.com.pagseuturco.accounting.model.domain.FinancialTurnoverTransfer;
import br.com.pagseuturco.accounting.model.domain.Turnover;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransferSQLMapper implements SQLMapper {

    private static String INSERT_QUERY = "insert into financial_turnover.transfer(id,account,value,type,date) values(default,?,?,?,?,?)";

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

}
