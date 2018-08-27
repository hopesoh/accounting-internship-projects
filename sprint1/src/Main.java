import java.io.*;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Scanner;

class Main {

    private static final String PONTOEVIRGULA = ";";

    public static void main(String[] args) throws Exception {
        //lê arquivo csv
        Scanner entrada = new Scanner(System.in);
        System.out.print( "Insira o caminho do arquivo: " );
        //no windows src/com/company/arquivo.csv
        //no linux src/arquivo.csv
        String arquivo = entrada.nextLine();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo)));

        DadosPreparadosParaContabilizacao dadosPreparados = prepareOsDadosParaContabilizacao(reader);

        //pede o numero da conta de movimentação
        //retorneNumeroCertoDaConta
        System.out.print( "Insira o numero da conta: " );
        int numeroDaConta = Integer.parseInt(entrada.nextLine());
        boolean numeroCertoDaConta = retorneNumeroCertoDeConta(numeroDaConta, dadosPreparados.getContaMovimentacao());
        while (numeroCertoDaConta) {
            if (numeroCertoDaConta) {
                System.out.println("Esse número de conta não existe.");
                System.out.print("Entre com um numero de conta válido: ");
                numeroDaConta = Integer.parseInt(entrada.nextLine());
                numeroCertoDaConta = retorneNumeroCertoDeConta(numeroDaConta, dadosPreparados.getContaMovimentacao());
            }
        }

        ContabilizacaoDeTransacoes contabilizacaoDeTransacoes = contabilizeTransacoesPorTipo(numeroDaConta,
                dadosPreparados.getTipoMovimentacao(),
                dadosPreparados.getValorMovimentacao(),
                dadosPreparados.getContaMovimentacao());
        System.out.println("A soma dos valores de Débito é: " + contabilizacaoDeTransacoes.getValorContabilizadoDeDebito());
        System.out.println("A soma dos valores de Crédito é: "+ contabilizacaoDeTransacoes.getValorContabilizadoDeCredito());
        reader.close();
    }

    public static DadosPreparadosParaContabilizacao prepareOsDadosParaContabilizacao (BufferedReader reader) throws IOException {
        String linha = null;
        ArrayList<String> idMovimentacao = new ArrayList<>();
        ArrayList<String> tipoMovimentacao = new ArrayList<>();
        ArrayList<String> valorMovimentacao = new ArrayList<>();
        ArrayList<String> contaMovimentacao = new ArrayList<>();

        while ((linha = reader.readLine()) != null) {
            String[] tipoDePagamento = linha.split(PONTOEVIRGULA);
            if (tipoDePagamento.length == 0) {
                return null;
            }
            idMovimentacao.add(tipoDePagamento[0]);
            tipoMovimentacao.add(tipoDePagamento[1]);
            valorMovimentacao.add(tipoDePagamento[2]);
            contaMovimentacao.add(tipoDePagamento[3]);
        }

        return new DadosPreparadosParaContabilizacao(idMovimentacao, tipoMovimentacao, valorMovimentacao, contaMovimentacao);
    }

    public static boolean retorneNumeroCertoDeConta(int numeroDaConta, ArrayList<String> conta) {
        boolean contaInexistente = true;
        int tamanho = conta.size();
        for (int i = 1; i < tamanho; i++) {
            if (numeroDaConta == Integer.parseInt(conta.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static ContabilizacaoDeTransacoes contabilizeTransacoesPorTipo (
            int numeroDaConta,
            ArrayList<String> tipo,
            ArrayList<String> valor,
            ArrayList<String> conta
    ) {
        int i;
        BigDecimal somaDebito = new BigDecimal(0);
        BigDecimal somaCredito = new BigDecimal(0);
        int tamanho = conta.size();
        for (i=1; i<tamanho; i++) {
            int num = Integer.parseInt(conta.get(i));
            if (numeroDaConta == num) {
                BigDecimal valorNaFormaBigDecimal = new BigDecimal(valor.get(i));
                if (tipo.get(i).equals("CREDITO")) {
                    somaCredito = somaCredito.add(valorNaFormaBigDecimal);
                }
                if (tipo.get(i).equals("DEBITO")) {
                    somaDebito = somaDebito.add(valorNaFormaBigDecimal);
                }
            }
        }
        return new ContabilizacaoDeTransacoes(somaCredito, somaDebito);
    }
}