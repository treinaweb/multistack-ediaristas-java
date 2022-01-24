package br.com.treinaweb.ediaristas.core.services.consultadistancia.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.api.dtos.responses.DiariaResponse;
import br.com.treinaweb.ediaristas.core.services.consultadistancia.adapters.ConsultaDistanciaService;

@Service
public class GoogleMatrixService implements ConsultaDistanciaService {

    @Value("${br.com.treinaweb.ediaristas.googleMatrix.apiKey}")
    private String apiKey;

    @Override
    public DiariaResponse calcularDistanciaEntreDoisCeps(String origem, String destino) {
        var origemFormatada = formatarCep(origem);
        var destinoFormatado = formatarCep(destino);

    }

    private String formatarCep(String cep) {
        validarCep(cep);

        var stringBuilder = new StringBuilder(cep);
        stringBuilder.insert(5, "-");
        return stringBuilder.toString();
    }

    private void validarCep(String cep) {
        if (cep.length() != 8) {
            throw new IllegalArgumentException("O cep deve ter oito digitos");
        }

        if (!cep.matches("[0-9]+")) {
            throw new IllegalArgumentException("O cep deve ter apenas números");
        }
    }

}
