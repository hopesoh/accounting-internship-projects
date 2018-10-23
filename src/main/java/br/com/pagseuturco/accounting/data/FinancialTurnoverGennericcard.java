package br.com.pagseuturco.accounting.data;

import java.math.BigDecimal;
import java.util.Objects;
import java.sql.*;

public class FinancialTurnoverGennericcard implements Turnover {
    private String cardsHash;
    private String type;
    private BigDecimal value;
    private Integer account;
    private String date;

    public FinancialTurnoverGennericcard(String[] splittedLine) {
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
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public void process() {

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
