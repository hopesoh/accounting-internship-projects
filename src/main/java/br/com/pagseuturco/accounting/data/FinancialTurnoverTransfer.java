package br.com.pagseuturco.accounting.data;

import java.math.BigDecimal;
import java.util.Objects;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FinancialTurnoverTransfer implements Turnover {
    private String type;
    private BigDecimal value;
    private Integer account;
    private String date;

    public FinancialTurnoverTransfer(String[] splittedLine) throws ClassNotFoundException, SQLException {
        type = splittedLine[0];
        if (!splittedLine[1].isEmpty()) {
            value = new BigDecimal(splittedLine[1]);
        }
        if (!splittedLine[2].isEmpty()) {
            account = Integer.parseInt(splittedLine[2]);
        }
        date = splittedLine[3];

        process(account, value, type,date);
    }

    @Override
    public Integer getAccount() {
        return account;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    public void process(Integer account, BigDecimal value, String type, String date) throws ClassNotFoundException, SQLException {
        Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/financial_turnover?"
                    + "user=sqluser&password=sqluserpw");

            preparedStatement = connect.prepareStatement("insert into financial_turnover.transfer values(default,?,?,?,?)");
            preparedStatement.setInt(1,account);
            preparedStatement.setBigDecimal(2, value);
            preparedStatement.setString(3,type);
            preparedStatement.setString(4,date);
            preparedStatement.executeUpdate();


        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value, account, date);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FinancialTurnoverTransfer turnover = (FinancialTurnoverTransfer) obj;
        return Objects.equals(type, turnover.type) &&
                Objects.equals(value, turnover.value) &&
                Objects.equals(account, turnover.account) &&
                Objects.equals(date, turnover.date);
    }
}
