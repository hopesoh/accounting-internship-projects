package br.com.pagseuturco.accounting.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.sql.*;

public class FinancialTurnoverBooklet implements Turnover {
    private static final BigDecimal BOOKLET_TAX = new BigDecimal(0.25);
    private String documentNumber;
    private String name;
    private BigDecimal value;
    private Integer account;
    private String date;
    private String id;

    public FinancialTurnoverBooklet(String[] splittedLine) throws SQLException, ClassNotFoundException {
        documentNumber = splittedLine[0];
        name = splittedLine[1];
        if (!splittedLine[3].isEmpty()) {
            value = new BigDecimal(splittedLine[3]);
        }
        if (!splittedLine[4].isEmpty()) {
            account = Integer.parseInt(splittedLine[4]);
        }
        date = splittedLine[5];
        id = splittedLine[2];

        process(name, value, account, date);
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

    public void process(String name, BigDecimal value, Integer account, String date) throws SQLException, ClassNotFoundException {
        Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        BigDecimal bookletValueSubtractTax = value.subtract(BOOKLET_TAX);

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/financial_turnover?"
                    + "user=sqluser&password=sqluserpw");

            preparedStatement = connect.prepareStatement("insert into financial_turnover.booklet values(default,?,?,?,?)");
            preparedStatement.setInt(1, account);
            preparedStatement.setBigDecimal(2, bookletValueSubtractTax);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, date);
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("insert into financial_turnover.pagseuturco_account values(default,?,?,?,?)");
            preparedStatement.setInt(1, account);
            preparedStatement.setBigDecimal(2, BOOKLET_TAX);
            preparedStatement.setString(3, "Booklet");
            preparedStatement.setString(4, date);
            preparedStatement.executeUpdate();


        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FinancialTurnoverBooklet turnover = (FinancialTurnoverBooklet) obj;
        return Objects.equals(name, turnover.name) &&
                Objects.equals(value, turnover.value) &&
                Objects.equals(account, turnover.account) &&
                Objects.equals(date, turnover.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, account, date);
    }
}
