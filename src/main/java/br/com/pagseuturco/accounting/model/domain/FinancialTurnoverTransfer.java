package br.com.pagseuturco.accounting.model.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class FinancialTurnoverTransfer implements Turnover {
    private String type;
    private BigDecimal value;
    private Integer account;
    private String date;
    private String turnoverType = "TRANSFER";

    public FinancialTurnoverTransfer(String[] splittedLine) {

        type = splittedLine[0];
        if (splittedLine[1] == null || splittedLine[1].isEmpty()) {
            value = BigDecimal.ZERO;
        } else {
            value = new BigDecimal(splittedLine[1]);
        }

        if (!splittedLine[2].isEmpty()) {
            account = Integer.parseInt(splittedLine[2]);
        }

        date = splittedLine[3];

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

    public String getType() {
        return type;
    }

    @Override
    public String getName() { return null; }

    @Override
    public String getCardsHash() {
        return null;
    }

    @Override
    public String getDocumentNumber() {
        return null;
    }

    @Override
    public BigDecimal getValue() {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        return value;
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
