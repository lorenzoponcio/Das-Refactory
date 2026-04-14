package com.controller;

import java.util.HashMap;
import java.util.Map;
import com.domain.Frete;

public class FreteController {

    public Map<String, Object> calcularFrete(Map<String, Object> requisicao) {
        try {
            double pesoProduto = (Double) requisicao.get("peso");
            double distanciaEntrega = (Double) requisicao.get("distancia");
            String tipoDoCliente = (String) requisicao.get("tipo_cliente");

            Frete frete = new Frete(pesoProduto, distanciaEntrega, tipoDoCliente);
            double valorFrete = frete.calcularFrete();

            Map<String, Object> resposta = new HashMap<>();
            resposta.put("peso", frete.getPesoProduto());
            resposta.put("distancia", frete.getDistanciaEntrega());
            resposta.put("tipo_cliente", frete.getTipoDoCliente());
            resposta.put("valor_frete", valorFrete);
            resposta.put("status_code", 200);
            return resposta;

        } catch (IllegalArgumentException e) {
            Map<String, Object> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            erro.put("status_code", 400);
            return erro;

        } catch (Exception e) {
            Map<String, Object> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            erro.put("status_code", 500);
            return erro;
        }
    }
}