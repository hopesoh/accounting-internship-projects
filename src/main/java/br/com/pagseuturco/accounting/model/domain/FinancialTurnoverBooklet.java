package br.com.pagseuturco.accounting.model.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class FinancialTurnoverBooklet implements Turnover {
    private String documentNumber;
    private String name;
    private BigDecimal value;
    private Integer account;
    private String date;
    private String id;
    private String turnoverType = "BOOKLET";

    public FinancialTurnoverBooklet(String[] splittedLine) {
        documentNumber = splittedLine[0];
        name = splittedLine[1];
        id = splittedLine[2];
        if (splittedLine[3]== null || splittedLine[3].isEmpty()) {
            value = BigDecimal.ZERO;
        } else {
            value = new BigDecimal(splittedLine[3]);
        }
        if (!splittedLine[4].isEmpty()) {
            account = Integer.parseInt(splittedLine[4]);
        }
        date = splittedLine[5];
    }

    public String getDocumentNumber() { return documentNumber; }

    @Override
    public Integer getAccount() {
        return account;
    }

    @Override
    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getCardsHash() {
        return null;
    }

    @Override
    public String getTurnoverType() {
        return turnoverType;
    }

    @Override
    public BigDecimal getValue() {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        return value;
    }

    @Override
    public String getType() {
        return null;
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
