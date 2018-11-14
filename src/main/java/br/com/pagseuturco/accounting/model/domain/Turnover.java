package br.com.pagseuturco.accounting.model.domain;

import br.com.pagseuturco.accounting.model.dao.mapper.SQLMapper;

import java.math.BigDecimal;

public interface Turnover {

    Integer getAccount();

    String getDate();

    String getTurnoverType();

    BigDecimal getValue();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();


    SQLMapper getSQLMapper();
}
