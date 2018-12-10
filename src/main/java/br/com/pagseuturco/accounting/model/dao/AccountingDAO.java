package br.com.pagseuturco.accounting.model.dao;

import br.com.pagseuturco.accounting.model.domain.ProfitInserter;
import br.com.pagseuturco.accounting.model.domain.Turnover;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

                if (turnover instanceof ProfitInserter) {
                    PreparedStatement preparedStatement = ((ProfitInserter) turnover).getSQLPagSeuTurcoMapper().createInsertPagSeuTurcoPreparedStatement(connect, turnover);
                    preparedStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<Turnover> findAll(Turnover turnover) {
        try {
            Connection connection = DriverManager.getConnection(jdbcURL);
            List<Turnover> turnovers = turnover.getSQLMapper().createFindAllStatement(connection);
            return turnovers;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}