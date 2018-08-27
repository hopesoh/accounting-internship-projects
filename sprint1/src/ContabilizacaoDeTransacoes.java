import java.math.BigDecimal;
import java.util.Objects;

/**
 * Representa a contabilização de transações para um determinado usuário.
 */
public class ContabilizacaoDeTransacoes {

    private BigDecimal valorContabilizadoDeCredito;
    private BigDecimal valorContabilizadoDeDebito;

    public ContabilizacaoDeTransacoes(BigDecimal valorContabilizadoDeCredito, BigDecimal valorContabilizadoDeDebito) {
        this.valorContabilizadoDeCredito = valorContabilizadoDeCredito;
        this.valorContabilizadoDeDebito = valorContabilizadoDeDebito;
    }

    public BigDecimal getValorContabilizadoDeCredito() {
        return valorContabilizadoDeCredito;
    }

    public BigDecimal getValorContabilizadoDeDebito() {
        return valorContabilizadoDeDebito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContabilizacaoDeTransacoes that = (ContabilizacaoDeTransacoes) o;
        return Objects.equals(valorContabilizadoDeCredito, that.valorContabilizadoDeCredito) &&
                Objects.equals(valorContabilizadoDeDebito, that.valorContabilizadoDeDebito);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valorContabilizadoDeCredito, valorContabilizadoDeDebito);
    }
}
