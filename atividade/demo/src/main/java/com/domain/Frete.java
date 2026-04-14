package com.domain;

public class Frete {

    private static final double VALOR_BASE = 10.0;
    private static final double FRETE_MINIMO = 15.0;
    private static final double DESCONTO_VIP = 0.8;
    private static final double TAXA_DISTANCIA = 0.5;

    private final double peso;
    private final double distancia;
    private final String tipoCliente;

    public Frete(double peso, double distancia, String tipoCliente) {
        validar(peso, distancia);
        this.peso = peso;
        this.distancia = distancia;
        this.tipoCliente = tipoCliente;
    }

    private void validar(double peso, double distancia) {
        if (peso <= 0) {
            throw new IllegalArgumentException("Peso inválido");
        }
        if (distancia <= 0) {
            throw new IllegalArgumentException("Distância inválida");
        }
    }

    public double calcular() {
        double total = VALOR_BASE + calcularTaxaPeso() + calcularTaxaDistancia();
        total = aplicarDesconto(total);
        total = aplicarFreteMinimo(total);
        return Math.round(total * 100.0) / 100.0;
    }

    private double calcularTaxaPeso() {
        if (peso <= 1) return 5.0;
        if (peso <= 5) return 10.0;
        return 20.0;
    }

    private double calcularTaxaDistancia() {
        return distancia * TAXA_DISTANCIA;
    }

    private double aplicarDesconto(double valor) {
        if ("VIP".equalsIgnoreCase(tipoCliente)) {
            return valor * DESCONTO_VIP;
        }
        return valor;
    }

    private double aplicarFreteMinimo(double valor) {
        return Math.max(valor, FRETE_MINIMO);
    }

    public double getPeso() { return peso; }
    public double getDistancia() { return distancia; }
    public String getTipoCliente() { return tipoCliente; }
}
