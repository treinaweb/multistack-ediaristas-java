package br.com.treinaweb.ediaristas.core.services.gatewaypagamento.providers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.models.Pagamento;
import br.com.treinaweb.ediaristas.core.services.gatewaypagamento.adpaters.GatewayPagamentoService;
import br.com.treinaweb.ediaristas.core.services.gatewaypagamento.exceptions.GatewayPagamentoException;

@Service
public class PagarMeService implements GatewayPagamentoService {

    private static final String BASE_URL = "https://api.pagar.me/1";

    private final RestTemplate clienteHttp = new RestTemplate();

    @Value("${br.com.treinaweb.ediaristas.pagarme.apiKey}")
    private String apiKey;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Pagamento pagar(Diaria diaria, String cardHash) {
        try {
            return tryPagar(diaria, cardHash);
        } catch (HttpClientErrorException.BadRequest exception) {
            var responseBody = exception.getResponseBodyAsString();
            var jsonNode = getJsonNode(responseBody);
            var message = jsonNode.path("errors")
                .path(0)
                .path("message")
                .asText();
            throw new GatewayPagamentoException(message);
        }
    }

    private Pagamento tryPagar(Diaria diaria, String cardHash) {
        return null;
    }

    private JsonNode getJsonNode(String responseBody) {
        try {
            return objectMapper.readTree(responseBody);
        } catch (JsonProcessingException exception) {
            throw new GatewayPagamentoException(exception.getLocalizedMessage());
        }
    }

}
