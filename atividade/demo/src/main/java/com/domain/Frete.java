package com.domain;

public class Frete {

    private static final double VALOR_BASE = 10.0;
    private static final double FRETE_MINIMO = 15.0;
    private static final double DESCONTO_CLIENTE_VIP = 0.8;
    private static final double TAXA_POR_DISTANCIA = 0.5;

    private final double pesoProduto;
    private final double distanciaEntrega;
    private final String tipoDoCliente;

    public Frete(double pesoProduto, double distanciaEntrega, String tipoDoCliente) {
        validarDados(pesoProduto, distanciaEntrega);
        this.pesoProduto = pesoProduto;
        this.distanciaEntrega = distanciaEntrega;
        this.tipoDoCliente = tipoDoCliente;
    }

    private void validarDados(double pesoProduto, double distanciaEntrega) {
        if (pesoProduto <= 0) {
            throw new IllegalArgumentException("Peso inválido");
        }
        if (distanciaEntrega <= 0) {
            throw new IllegalArgumentException("Distância inválida");
        }
    }

    public double calcularFrete() {
        double valorTotal = VALOR_BASE + calcularTaxaPorPeso() + calcularTaxaPorDistancia();
        valorTotal = aplicarDescontoCliente(valorTotal);
        valorTotal = aplicarValorMinimoFrete(valorTotal);
        return Math.round(valorTotal * 100.0) / 100.0;
    }

    private double calcularTaxaPorPeso() {
        if (pesoProduto <= 1) return 5.0;
        if (pesoProduto <= 5) return 10.0;
        return 20.0;
    }

    private double calcularTaxaPorDistancia() {
        return distanciaEntrega * TAXA_POR_DISTANCIA;
    }

    private double aplicarDescontoCliente(double valor) {
        if ("VIP".equalsIgnoreCase(tipoDoCliente)) {
            return valor * DESCONTO_CLIENTE_VIP;
        }
        return valor;
    }

    private double aplicarValorMinimoFrete(double valor) {
        return Math.max(valor, FRETE_MINIMO);
    }

    public double getPesoProduto() { return pesoProduto; }
    public double getDistanciaEntrega() { return distanciaEntrega; }
    public String getTipoDoCliente() { return tipoDoCliente; }
}