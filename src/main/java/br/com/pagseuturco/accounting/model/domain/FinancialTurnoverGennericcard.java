package br.com.pagseuturco.accounting.model.domain;

import br.com.pagseuturco.accounting.model.dao.mapper.GennericcardSQLMapper;
import br.com.pagseuturco.accounting.model.dao.mapper.SQLMapper;
import br.com.pagseuturco.accounting.model.dao.mapper.SQLPagSeuTurcoMapper;

import java.math.BigDecimal;
import java.util.Objects;

public class FinancialTurnoverGennericcard implements Turnover, GennericcardType {
    private static final GennericcardSQLMapper GENNERICCARD_SQL_MAPPER = new GennericcardSQLMapper();
    private static final GennericcardSQLMapper BOOKLET_PAGSEUTURCO_SQL_MAPPER = new GennericcardSQLMapper();

    private String cardsHash;
    private String type;
    private BigDecimal value;
    private Integer account;
    private String date;
    private String turnoverType = "GENNERICCARD";

    public FinancialTurnoverGennericcard(String[] splittedLine) {
        cardsHash = splittedLine[0];
        type = splittedLine[1];
        if (splittedLine[2]== null || splittedLine[2].isEmpty()) {
            value = BigDecimal.ZERO;
        } else {
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

    @Override
    public SQLMapper getSQLMapper() {
        return GENNERICCARD_SQL_MAPPER;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getCardsHash() {
        return cardsHash;
    }

    @Override
    public SQLPagSeuTurcoMapper getSQLPagSeuTurcoMapper() { return BOOKLET_PAGSEUTURCO_SQL_MAPPER; }
}
