package br.com.pagseuturco.accounting.data;

import java.math.BigDecimal;
import java.util.Objects;

public interface Turnover {

    public Integer getAccount();

    public String getDate();

    @Override
    public boolean equals(Object o);

    @Override
    public int hashCode();

    public BigDecimal getValue();

}
