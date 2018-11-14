package br.com.pagseuturco.accounting.model.domain;

import br.com.pagseuturco.accounting.model.dao.mapper.SQLMapper;

import java.math.BigDecimal;
import java.util.Objects;

public class FinancialTurnoverBooklet implements Turnover, BookletType {
    private String documentNumber;
    private String name;
    private BigDecimal value;
    private Integer account;
    private String date;
    private String identification;
    private String turnoverType = "BOOKLET";
    private static final BigDecimal BOOKLET_TAX = new BigDecimal(0.25);

    public FinancialTurnoverBooklet(String[] splittedLine) {
        documentNumber = splittedLine[0];
        name = splittedLine[1];
        identification = splittedLine[2];
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
    public SQLMapper getSQLMapper() {
        return null;
    }

    @Override
    public String getDocumentNumber() {
        return documentNumber;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIdentification() {
        return identification;
    }
}
