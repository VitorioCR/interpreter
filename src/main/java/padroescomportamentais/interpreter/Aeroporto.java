package padroescomportamentais.interpreter;

public class Aeroporto {

    public static String formula = "pesoToneladas * 2 + distanciaKm";

    public static double calcularTaxaPouso(double pesoToneladas, double distanciaKm) {
        String expressao;
        expressao = formula.replace("pesoToneladas", Double.toString(pesoToneladas));
        expressao = expressao.replace("distanciaKm", Double.toString(distanciaKm));
        InterpretadorExpressao interpretador = new InterpretadorExpressoesAritmeticas(expressao);
        return interpretador.interpretar();
    }
}
