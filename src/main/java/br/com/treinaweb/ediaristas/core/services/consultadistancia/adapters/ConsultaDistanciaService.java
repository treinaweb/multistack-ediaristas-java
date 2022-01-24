package br.com.treinaweb.ediaristas.core.services.consultadistancia.adapters;

import br.com.treinaweb.ediaristas.api.dtos.responses.DiariaResponse;

public interface ConsultaDistanciaService {

    DiariaResponse calcularDistanciaEntreDoisCeps(String origem, String destino);

}
