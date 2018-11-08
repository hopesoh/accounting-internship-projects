package br.com.pagseuturco.accounting.model.domain;

import java.math.BigDecimal;

public interface Turnover {

    Integer getAccount();

    String getDate();

    String getTurnoverType();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

    BigDecimal getValue();

    String getType();

    String getName();

    String getCardsHash();

    String getDocumentNumber();
}
