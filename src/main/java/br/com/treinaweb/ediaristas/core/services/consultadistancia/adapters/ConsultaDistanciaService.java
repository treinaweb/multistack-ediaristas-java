package br.com.treinaweb.ediaristas.core.services.consultadistancia.adapters;

import br.com.treinaweb.ediaristas.core.services.consultadistancia.dtos.DistanciaResponse;

public interface ConsultaDistanciaService {

    DistanciaResponse calcularDistanciaEntreDoisCeps(String origem, String destino);

}
