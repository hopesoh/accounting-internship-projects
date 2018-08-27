import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class contabilizeTransacoesPorTipoTeste {

    @Test
    public void testeUmContabilizeTransacoesPorTipo() {
        int numeroDaConta = 4;
        ArrayList<String> tipoDeMovimentacao = new ArrayList<>();
        tipoDeMovimentacao.add("tipo_movimentacao");
        tipoDeMovimentacao.add("CREDITO");
        ArrayList<String> valorDeMovimentacao = new ArrayList<>();
        valorDeMovimentacao.add("valor_movimentacao");
        valorDeMovimentacao.add("300");
        ArrayList<String> contaDeMovimentacao = new ArrayList<>();
        contaDeMovimentacao.add("conta_movimentacao");
        contaDeMovimentacao.add("4");
        BigDecimal somaDebito = new BigDecimal(0);
        BigDecimal somaCredito = new BigDecimal(300);
        ContabilizacaoDeTransacoes contabilizacaoEsperada = new ContabilizacaoDeTransacoes(somaCredito, somaDebito);
        Main conta = new Main();

        assertEquals(contabilizacaoEsperada, conta.contabilizeTransacoesPorTipo(numeroDaConta, tipoDeMovimentacao, valorDeMovimentacao, contaDeMovimentacao));
    }

    @Test
    public void testeDoisContabilizeTransacoesPorTipo() {
        int numeroDaConta = 4;
        ArrayList<String> tipoDeMovimentacao = new ArrayList<>();
        tipoDeMovimentacao.add("tipo_movimentacao");
        tipoDeMovimentacao.add("CREDITO");
        tipoDeMovimentacao.add("DEBITO");
        ArrayList<String> valorDeMovimentacao = new ArrayList<>();
        valorDeMovimentacao.add("valor_movimentacao");
        valorDeMovimentacao.add("300");
        valorDeMovimentacao.add("10");
        ArrayList<String> contaDeMovimentacao = new ArrayList<>();
        contaDeMovimentacao.add("conta_movimentacao");
        contaDeMovimentacao.add("4");
        contaDeMovimentacao.add("4");
        BigDecimal somaDebito = new BigDecimal(10);
        BigDecimal somaCredito = new BigDecimal(300);
        ContabilizacaoDeTransacoes contabilizacaoEsperada = new ContabilizacaoDeTransacoes(somaCredito, somaDebito);
        Main conta = new Main();

        assertEquals(contabilizacaoEsperada, conta.contabilizeTransacoesPorTipo(numeroDaConta, tipoDeMovimentacao, valorDeMovimentacao, contaDeMovimentacao));
    }

    @Test
    public void testeTresContabilizeTransacoesPorTipo() {
        int numeroDaConta = 2;
        ArrayList<String> tipoDeMovimentacao = new ArrayList<>();
        tipoDeMovimentacao.add("tipo_movimentacao");
        tipoDeMovimentacao.add("CREDITO");
        tipoDeMovimentacao.add("DEBITO");
        ArrayList<String> valorDeMovimentacao = new ArrayList<>();
        valorDeMovimentacao.add("valor_movimentacao");
        valorDeMovimentacao.add("300");
        valorDeMovimentacao.add("10");
        ArrayList<String> contaDeMovimentacao = new ArrayList<>();
        contaDeMovimentacao.add("conta_movimentacao");
        contaDeMovimentacao.add("4");
        contaDeMovimentacao.add("2");
        BigDecimal somaDebito = new BigDecimal(10);
        BigDecimal somaCredito = new BigDecimal(0);
        ContabilizacaoDeTransacoes contabilizacaoEsperada = new ContabilizacaoDeTransacoes(somaCredito, somaDebito);
        Main conta = new Main();

        assertEquals(contabilizacaoEsperada, conta.contabilizeTransacoesPorTipo(numeroDaConta, tipoDeMovimentacao, valorDeMovimentacao, contaDeMovimentacao));
    }

}