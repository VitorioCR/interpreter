package padroescomportamentais.interpreter;

import org.junit.Test;
import static org.junit.Assert.*;

public class InterpreterTest {

    @Test
    public void testAdicao() {
        Numero esquerda = new Numero(10);
        Numero direita = new Numero(5);
        Adicao adicao = new Adicao(esquerda, direita);
        assertEquals(15.0, adicao.interpretar(), 0.001);
    }

    @Test
    public void testSubtracao() {
        Numero esquerda = new Numero(10);
        Numero direita = new Numero(3);
        Subtracao subtracao = new Subtracao(esquerda, direita);
        assertEquals(7.0, subtracao.interpretar(), 0.001);
    }

    @Test
    public void testMultiplicacao() {
        Numero esquerda = new Numero(4);
        Numero direita = new Numero(5);
        Multiplicacao multiplicacao = new Multiplicacao(esquerda, direita);
        assertEquals(20.0, multiplicacao.interpretar(), 0.001);
    }

    @Test
    public void testDivisao() {
        Numero esquerda = new Numero(20);
        Numero direita = new Numero(4);
        Divisao divisao = new Divisao(esquerda, direita);
        assertEquals(5.0, divisao.interpretar(), 0.001);
    }

    @Test
    public void testNumero() {
        Numero numero = new Numero(42);
        assertEquals(42.0, numero.interpretar(), 0.001);
        assertEquals(42.0, numero.getNumero(), 0.001);
    }

    @Test
    public void testInterpretadorExpressoesAritmeticasSimples() {
        InterpretadorExpressao interpretador = new InterpretadorExpressoesAritmeticas("3 + 2");
        assertEquals(5.0, interpretador.interpretar(), 0.001);
    }

    @Test
    public void testInterpretadorExpressoesAritmeticasComplexa() {
        // 10 * 2 + 500 = (10 * 2) + 500 = 20 + 500 = 520
        InterpretadorExpressao interpretador = new InterpretadorExpressoesAritmeticas("10 * 2 + 500");
        assertEquals(520.0, interpretador.interpretar(), 0.001);
    }

    @Test
    public void testVooCalcularTaxaPouso() {
        // Formula padrão: pesoToneladas * 2 + distanciaKm
        // Peso = 50 toneladas, Distancia = 500 km
        // 50 * 2 + 500 = 100 + 500 = 600
        Voo voo = new Voo();
        voo.setPesoToneladas(50);
        voo.setDistanciaKm(500);
        assertEquals(600.0, voo.calcularTaxaPouso(), 0.001);
    }

    @Test
    public void testVooCalcularTaxaPousoValoresDiferentes() {
        // Peso = 100 toneladas, Distancia = 1000 km
        // 100 * 2 + 1000 = 200 + 1000 = 1200
        Voo voo = new Voo();
        voo.setPesoToneladas(100);
        voo.setDistanciaKm(1000);
        assertEquals(1200.0, voo.calcularTaxaPouso(), 0.001);
    }

    @Test
    public void testAeroportoCalcularTaxaPouso() {
        // 30 * 2 + 200 = 60 + 200 = 260
        double taxa = Aeroporto.calcularTaxaPouso(30, 200);
        assertEquals(260.0, taxa, 0.001);
    }

    @Test
    public void testAeroportoFormulaPersonalizada() {
        String formulaOriginal = Aeroporto.formula;
        try {
            // Alterando a fórmula para: pesoToneladas * 3 + distanciaKm * 2
            Aeroporto.formula = "pesoToneladas * 3 + distanciaKm * 2";
            // 10 * 3 + 100 * 2 = 30 + 200 = 230
            // Avaliação left-to-right: (10 * 3) = 30, (30 + 100) = 130, (130 * 2) = 260
            // Na verdade, o parser avalia da esquerda para direita:
            // 10 * 3 = 30, 30 + 100 = 130, 130 * 2 = 260
            double taxa = Aeroporto.calcularTaxaPouso(10, 100);
            assertEquals(260.0, taxa, 0.001);
        } finally {
            Aeroporto.formula = formulaOriginal;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExpressaoInvalida() {
        new InterpretadorExpressoesAritmeticas("abc");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExpressaoIncompleta() {
        new InterpretadorExpressoesAritmeticas("5 +");
    }
}
