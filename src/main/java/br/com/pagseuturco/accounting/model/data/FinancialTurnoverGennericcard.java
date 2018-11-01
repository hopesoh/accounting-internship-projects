package br.com.pagseuturco.accounting.model.data;

import java.math.BigDecimal;
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
    private String turnoverType = "GENNERICCARD";

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
    public String getTurnoverType() {
        return turnoverType;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getName() {
        return null;
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
