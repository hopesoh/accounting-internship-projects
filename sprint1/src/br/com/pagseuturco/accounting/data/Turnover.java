package br.com.pagseuturco.accounting.data;

import java.math.BigDecimal;
import java.util.Objects;

public class Turnover {
    private Integer id;
    private String type;
    private BigDecimal value;
    private Integer account;
    private String date;

    public Turnover(String[] splittedLine) {
        if(!splittedLine[0].isEmpty()) {
            id = Integer.parseInt(splittedLine[0]);
        }
        type = splittedLine[1];
        if (!splittedLine[2].isEmpty()) {
            value = new BigDecimal(splittedLine[2]);
        }
        if (!splittedLine[3].isEmpty()) {
            account = Integer.parseInt(splittedLine[3]);
        }
        date = splittedLine[4];
    }

    public Integer getAccount() {
        return account;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turnover turnover = (Turnover) o;
        return Objects.equals(id, turnover.id) &&
                Objects.equals(type, turnover.type) &&
                Objects.equals(value, turnover.value) &&
                Objects.equals(account, turnover.account) &&
                Objects.equals(date, turnover.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, value, account, date);
    }

    public BigDecimal getValue() {
        return value;
    }

}
