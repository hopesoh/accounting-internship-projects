package br.com.pagseuturco.accounting.model.data;

import java.math.BigDecimal;

public interface Turnover {

    public Integer getAccount();

    public String getDate();

    public String getType();

    @Override
    public boolean equals(Object o);

    @Override
    public int hashCode();

    public BigDecimal getValue();

}
