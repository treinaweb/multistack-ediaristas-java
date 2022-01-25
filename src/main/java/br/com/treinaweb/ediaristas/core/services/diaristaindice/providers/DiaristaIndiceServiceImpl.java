package br.com.treinaweb.ediaristas.core.services.diaristaindice.providers;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.models.Usuario;
import br.com.treinaweb.ediaristas.core.services.consultadistancia.adapters.ConsultaDistanciaService;
import br.com.treinaweb.ediaristas.core.services.consultadistancia.exceptions.ConsultaDistanciaServiceException;
import br.com.treinaweb.ediaristas.core.services.diaristaindice.adapters.DiaristaIndiceService;

@Service
public class DiaristaIndiceServiceImpl implements DiaristaIndiceService {

    @Autowired
    private ConsultaDistanciaService consultaDistanciaService;

    @Override
    public Usuario selecionarMelhorDiarista(Diaria diaria) {
        validarDiaria(diaria);

        var destino = diaria.getCep();

        return diaria.getCandidatos()
            .stream()
            .max(Comparator.comparingDouble(candidato -> calcularIndice(candidato, destino)))
            .get();
    }

    private void validarDiaria(Diaria diaria) {
        if (diaria.getCandidatos().isEmpty()) {
            throw new IllegalArgumentException("A lista de candidatos da diária não pode estar vazia");
        }
    }

    private String getCandidatoCep(Usuario candidato) {
        validarCandidato(candidato);
        return candidato.getEndereco().getCep();
    }

    private void validarCandidato(Usuario candidato) {
        if (candidato.getEndereco() == null) {
            throw new IllegalArgumentException("Todos os candidatos da diária devem possuir um endereço");
        }
    }

    private Double getDistancia(String destino, String origem) {
        try {
            return consultaDistanciaService.calcularDistanciaEntreDoisCeps(origem, destino).getDistanciaEmKm();
        } catch (ConsultaDistanciaServiceException exception) {
            return Double.MAX_VALUE;
        }
    }

    private Double calcularIndice(Usuario candidato, String destino) {
        var origem = getCandidatoCep(candidato);
        var distancia = getDistancia(destino, origem);
        var reputacao = candidato.getReputacao();

        return (reputacao - (distancia / 10.0)) / 2.0;
    }

}
