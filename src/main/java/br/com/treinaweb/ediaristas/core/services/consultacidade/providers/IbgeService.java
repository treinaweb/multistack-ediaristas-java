package br.com.treinaweb.ediaristas.core.services.consultacidade.providers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.treinaweb.ediaristas.core.services.consultacidade.adapters.ConsultaCidadeService;
import br.com.treinaweb.ediaristas.core.services.consultacidade.dtos.CidadeResponse;
import br.com.treinaweb.ediaristas.core.services.consultacidade.exceptions.ConsultaCidadeServiceException;

@Service
public class IbgeService implements ConsultaCidadeService {

    private final static String BASE_URL = "https://servicodados.ibge.gov.br/api/v1";

    private final RestTemplate clienteHttp = new RestTemplate();

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public CidadeResponse buscarCidadePorCodigoIbge(String codigoIbge) throws ConsultaCidadeServiceException {
        var url = BASE_URL + "/localidades/municipios/" + codigoIbge;
        var response = clienteHttp.getForEntity(url, String.class);
        var jsonNode = getJsonNode(response);
        validarJsonNode(jsonNode);

        var cidade = getCidade(jsonNode);
        var estado = getEstado(jsonNode);

        return new CidadeResponse(codigoIbge, cidade, estado);
    }

    private String getEstado(JsonNode jsonNode) {
        return jsonNode.path("microrregiao")
            .path("mesorregiao")
            .path("UF")
            .path("sigla")
            .asText();
    }

    private String getCidade(JsonNode jsonNode) {
        return jsonNode.path("nome").asText();
    }

    private void validarJsonNode(JsonNode jsonNode) {
        if (jsonNode.path("nome").asText().isEmpty()) {
            throw new ConsultaCidadeServiceException("Cidade não encontrada");
        }
    }

    private JsonNode getJsonNode(ResponseEntity<String> response) {
        try {
            return objectMapper.readTree(response.getBody());
        } catch (JsonMappingException e) {
            throw new ConsultaCidadeServiceException(e.getLocalizedMessage());
        } catch (JsonProcessingException e) {
            throw new ConsultaCidadeServiceException(e.getLocalizedMessage());
        }
    }

}
