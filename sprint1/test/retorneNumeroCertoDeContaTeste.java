import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class retorneNumeroCertoDeContaTeste {

    @Test
    public void testeUmRetorneNumeroCertoDeConta() {
        ArrayList<String> contaDeMovimentacao = new ArrayList<>();
        contaDeMovimentacao.add("conta_movimentacao");
        contaDeMovimentacao.add("4");
        assertEquals(false, Main.retorneNumeroCertoDeConta(4, contaDeMovimentacao));
    }

    @Test
    public void testeDoisRetorneNumeroCertoDeConta() {
        ArrayList<String> contaDeMovimentacao = new ArrayList<>();
        contaDeMovimentacao.add("conta_movimentacao");
        contaDeMovimentacao.add("4");
        assertEquals(true, Main.retorneNumeroCertoDeConta(2, contaDeMovimentacao));
    }
}
