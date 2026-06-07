package padroescomportamentais.interpreter;

public class Voo {

    private double pesoToneladas;
    private double distanciaKm;

    public double getPesoToneladas() {
        return pesoToneladas;
    }

    public void setPesoToneladas(double pesoToneladas) {
        this.pesoToneladas = pesoToneladas;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public double calcularTaxaPouso() {
        return Aeroporto.calcularTaxaPouso(this.pesoToneladas, this.distanciaKm);
    }
}
