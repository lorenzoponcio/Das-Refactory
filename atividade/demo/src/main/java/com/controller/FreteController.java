package com.controller;

import java.util.HashMap;
import java.util.Map;
import com.domain.Frete;

public class FreteController {

    public Map<String, Object> calcularFrete(Map<String, Object> request) {
        try {
            double peso = (Double) request.get("peso");
            double distancia = (Double) request.get("distancia");
            String tipoCliente = (String) request.get("tipo_cliente");

            Frete frete = new Frete(peso, distancia, tipoCliente);
            double valorFrete = frete.calcular();

            Map<String, Object> response = new HashMap<>();
            response.put("peso", frete.getPeso());
            response.put("distancia", frete.getDistancia());
            response.put("tipo_cliente", frete.getTipoCliente());
            response.put("valor_frete", valorFrete);
            response.put("status_code", 200);
            return response;

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