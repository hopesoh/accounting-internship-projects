package br.com.pagseuturco.accounting.data;

import java.math.BigDecimal;
import java.util.Objects;
import java.sql.*;

public class FinancialTurnoverBooklet implements Turnover {
    private Integer documentNumber;
    private String name;
    private Integer id;
    private BigDecimal value;
    private Integer account;
    private String date;

    public FinancialTurnoverBooklet(String[] splittedLine) {
        if(!splittedLine[0].isEmpty()) {
            documentNumber = Integer.parseInt(splittedLine[0]);
        }
        name = splittedLine[1];
        if (!splittedLine[2].isEmpty()) {
            id = Integer.parseInt(splittedLine[2]);
        }
        if (!splittedLine[3].isEmpty()) {
            value = new BigDecimal(splittedLine[3]);
        }
        if (!splittedLine[4].isEmpty()) {
            account = Integer.parseInt(splittedLine[4]);
        }
        date = splittedLine[5];
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
        FinancialTurnoverBooklet turnover = (FinancialTurnoverBooklet) obj;
        return Objects.equals(documentNumber, turnover.documentNumber) &&
                Objects.equals(name, turnover.name) &&
                Objects.equals(id, turnover.id) &&
                Objects.equals(value, turnover.value) &&
                Objects.equals(account, turnover.account) &&
                Objects.equals(date, turnover.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, name, id, value, account, date);
    }
}
