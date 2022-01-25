package br.com.treinaweb.ediaristas.core.services.consultadistancia.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistanciaResponse {

    private String origem;

    private String destino;

    private Double distanciaEmKm;

}
