package br.com.pagseuturco.accounting.model.data;

import java.math.BigDecimal;
import java.util.Objects;

public class TransactionsAccounting {

    private BigDecimal creditValue;
    private BigDecimal dabitValue;

    public TransactionsAccounting(BigDecimal creditValue, BigDecimal dabitValue) {
        this.creditValue = creditValue;
        this.dabitValue = dabitValue;
    }

    public BigDecimal getCreditValue() {
        return creditValue;
    }

    public BigDecimal getDabitValue() {
        return dabitValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionsAccounting that = (TransactionsAccounting) o;
        return Objects.equals(creditValue, that.creditValue) &&
                Objects.equals(dabitValue, that.dabitValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditValue, dabitValue);
    }
}
