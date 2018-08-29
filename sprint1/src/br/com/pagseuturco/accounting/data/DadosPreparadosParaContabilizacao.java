package br.com.pagseuturco.accounting.data;

import java.util.ArrayList;
import java.util.Objects;

public class DadosPreparadosParaContabilizacao {

    private ArrayList<String> valoresIdMovimentacao;
    private ArrayList<String> valoresTipoMovimentacao;
    private ArrayList<String> valoresValorMovimentacao;
    private ArrayList<String> valoresContaMovimentacao;

    public DadosPreparadosParaContabilizacao(ArrayList<String> valoresIdMovimentacao,
                                             ArrayList<String> valoresTipoMovimentacao,
                                             ArrayList<String> valoresValorMovimentacao,
                                             ArrayList<String> valoresContaMovimentacao) {
        this.valoresIdMovimentacao = valoresIdMovimentacao;
        this.valoresTipoMovimentacao = valoresTipoMovimentacao;
        this.valoresValorMovimentacao = valoresValorMovimentacao;
        this.valoresContaMovimentacao = valoresContaMovimentacao;
    }

    public ArrayList<String> getIdMovimentacao() {
        return valoresIdMovimentacao;
    }

    public ArrayList<String> getTipoMovimentacao() {
        return valoresTipoMovimentacao;
    }

    public ArrayList<String> getValorMovimentacao() {
        return valoresValorMovimentacao;
    }

    public ArrayList<String> getContaMovimentacao() {
        return valoresContaMovimentacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DadosPreparadosParaContabilizacao that = (DadosPreparadosParaContabilizacao) o;
        return Objects.equals(valoresIdMovimentacao, that.valoresIdMovimentacao) &&
                Objects.equals(valoresTipoMovimentacao, that.valoresTipoMovimentacao) &&
                Objects.equals(valoresValorMovimentacao, that.valoresValorMovimentacao) &&
                Objects.equals(valoresContaMovimentacao, that.valoresContaMovimentacao);
    }
}
