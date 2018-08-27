import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class prepareOsDadosParaContabilizacaoTeste {

    @Test
    public void testeUmPrepareOsDadosParaContabilizacao() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader("2;CREDITO;300;101;27/08/2018"));
        ArrayList<String> idMovimentacao = new ArrayList<>();
        idMovimentacao.add("2");
        ArrayList<String> tipoMovimentacao = new ArrayList<>();
        tipoMovimentacao.add("CREDITO");
        ArrayList<String> valorMovimentacao = new ArrayList<>();
        valorMovimentacao.add("300");
        ArrayList<String> contaMovimentacao = new ArrayList<>();
        contaMovimentacao.add("101");

        DadosPreparadosParaContabilizacao dadosEsperados = new DadosPreparadosParaContabilizacao(idMovimentacao,tipoMovimentacao,valorMovimentacao,contaMovimentacao);
        assertEquals(dadosEsperados, Main.prepareOsDadosParaContabilizacao(reader));
    }

    @Test
    public void testeDoisPrepareOsDadosParaContabilizacao() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader("2;DEBITO;200;100;"));
        ArrayList<String> idMovimentacao = new ArrayList<>();
        idMovimentacao.add("2");
        ArrayList<String> tipoMovimentacao = new ArrayList<>();
        tipoMovimentacao.add("DEBITO");
        ArrayList<String> valorMovimentacao = new ArrayList<>();
        valorMovimentacao.add("200");
        ArrayList<String> contaMovimentacao = new ArrayList<>();
        contaMovimentacao.add("100");

        DadosPreparadosParaContabilizacao dadosEsperados = new DadosPreparadosParaContabilizacao(idMovimentacao,tipoMovimentacao,valorMovimentacao,contaMovimentacao);
        assertEquals(dadosEsperados, Main.prepareOsDadosParaContabilizacao(reader));
    }

    @Test
    public void testeTresPrepareOsDadosParaContabilizacao() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader("2;DEBITO;200;;27/08/2018"));
        ArrayList<String> idMovimentacao = new ArrayList<>();
        idMovimentacao.add("2");
        ArrayList<String> tipoMovimentacao = new ArrayList<>();
        tipoMovimentacao.add("DEBITO");
        ArrayList<String> valorMovimentacao = new ArrayList<>();
        valorMovimentacao.add("200");
        ArrayList<String> contaMovimentacao = new ArrayList<>();
        contaMovimentacao.add("");

        DadosPreparadosParaContabilizacao dadosEsperados = new DadosPreparadosParaContabilizacao(idMovimentacao,tipoMovimentacao,valorMovimentacao,contaMovimentacao);
        assertEquals(dadosEsperados, Main.prepareOsDadosParaContabilizacao(reader));
    }

    @Test
    public void testeQuatroPrepareOsDadosParaContabilizacao() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader(";;;;27/08/2018"));
        ArrayList<String> idMovimentacao = new ArrayList<>();
        idMovimentacao.add("");
        ArrayList<String> tipoMovimentacao = new ArrayList<>();
        tipoMovimentacao.add("");
        ArrayList<String> valorMovimentacao = new ArrayList<>();
        valorMovimentacao.add("");
        ArrayList<String> contaMovimentacao = new ArrayList<>();
        contaMovimentacao.add("");

        DadosPreparadosParaContabilizacao dadosEsperados = new DadosPreparadosParaContabilizacao(idMovimentacao,tipoMovimentacao,valorMovimentacao,contaMovimentacao);
        assertEquals(dadosEsperados, Main.prepareOsDadosParaContabilizacao(reader));
    }

    @Test
    public void testeCincoPrepareOsDadosParaContabilizacao() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader(";;;; "));
        ArrayList<String> idMovimentacao = new ArrayList<>();
        idMovimentacao.add("");
        ArrayList<String> tipoMovimentacao = new ArrayList<>();
        tipoMovimentacao.add("");
        ArrayList<String> valorMovimentacao = new ArrayList<>();
        valorMovimentacao.add("");
        ArrayList<String> contaMovimentacao = new ArrayList<>();
        contaMovimentacao.add("");

        DadosPreparadosParaContabilizacao dadosEsperados = new DadosPreparadosParaContabilizacao(idMovimentacao,tipoMovimentacao,valorMovimentacao,contaMovimentacao);
        assertEquals(dadosEsperados, Main.prepareOsDadosParaContabilizacao(reader));
    }

    @Test
    public void testeSeisPrepareOsDadosParaContabilizacao() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader(";;;;"));
        DadosPreparadosParaContabilizacao dadosEsperados = null;
        assertEquals(dadosEsperados, Main.prepareOsDadosParaContabilizacao(reader));
    }
}