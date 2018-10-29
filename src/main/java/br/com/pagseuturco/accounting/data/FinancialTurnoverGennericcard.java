package br.com.pagseuturco.accounting.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.sql.*;

public class FinancialTurnoverGennericcard implements Turnover {
    private static final BigDecimal CREDIT_TAX = new BigDecimal(0.02);
    private static final BigDecimal DEBIT_TAX = new BigDecimal(0.035);
    private String cardsHash;
    private String type;
    private BigDecimal value;
    private Integer account;
    private String date;

    public FinancialTurnoverGennericcard(String[] splittedLine) throws SQLException, ClassNotFoundException {
        cardsHash = splittedLine[0];
        type = splittedLine[1];
        if (!splittedLine[2].isEmpty()) {
            value = new BigDecimal(splittedLine[2]);
        }
        if (!splittedLine[3].isEmpty()) {
            account = Integer.parseInt(splittedLine[3]);
        }
        date = splittedLine[4];

        process(type, value, account, date);
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

    public void process(String type, BigDecimal value, Integer account, String date) throws SQLException, ClassNotFoundException {
        Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        BigDecimal valueToAccountNumberOne = calculateTaxByTransactionType(type, value);
        BigDecimal gennericcardValueSubtractTax = value.subtract(valueToAccountNumberOne);

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/financial_turnover?"
                    + "user=sqluser&password=sqluserpw");

            preparedStatement = connect.prepareStatement("insert into financial_turnover.gennericcard values(default,?,?,?,?)");
            preparedStatement.setInt(1, account);
            preparedStatement.setBigDecimal(2, gennericcardValueSubtractTax.setScale(2, RoundingMode.DOWN));
            preparedStatement.setString(3, type);
            preparedStatement.setString(4, date);
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("insert into financial_turnover.pagseuturco_account values(default,?,?,?,?)");
            preparedStatement.setInt(1,account);
            preparedStatement.setBigDecimal(2,valueToAccountNumberOne.setScale(2, RoundingMode.DOWN));
            preparedStatement.setString(3, "Gennericard");
            preparedStatement.setString(4, date);
            preparedStatement.executeUpdate();


        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal calculateTaxByTransactionType(String type, BigDecimal value) {
        if (type == "CREDIT") {
            return value.multiply(CREDIT_TAX);
        } else {
            return value.multiply(DEBIT_TAX);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FinancialTurnoverGennericcard turnover = (FinancialTurnoverGennericcard) obj;
        return Objects.equals(cardsHash, turnover.cardsHash) &&
                Objects.equals(type, turnover.type) &&
                Objects.equals(value, turnover.value) &&
                Objects.equals(account, turnover.account) &&
                Objects.equals(date, turnover.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardsHash, type, value, account, date);
    }
}
