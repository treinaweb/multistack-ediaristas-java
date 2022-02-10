package br.com.treinaweb.ediaristas.core.services.gatewaypagamento.providers;

import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.treinaweb.ediaristas.core.enums.PagamentoStatus;
import br.com.treinaweb.ediaristas.core.exceptions.PagamentoNaoEncontradoException;
import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.models.Pagamento;
import br.com.treinaweb.ediaristas.core.repositories.PagamentoRepository;
import br.com.treinaweb.ediaristas.core.services.gatewaypagamento.adpaters.GatewayPagamentoService;
import br.com.treinaweb.ediaristas.core.services.gatewaypagamento.exceptions.GatewayPagamentoException;
import br.com.treinaweb.ediaristas.core.services.gatewaypagamento.providers.dtos.PagarMeReembolsoRequest;
import br.com.treinaweb.ediaristas.core.services.gatewaypagamento.providers.dtos.PagarMeReembolsoResponse;
import br.com.treinaweb.ediaristas.core.services.gatewaypagamento.providers.dtos.PagarMeTransacaoRequest;
import br.com.treinaweb.ediaristas.core.services.gatewaypagamento.providers.dtos.PagarMeTransacaoResponse;

@Service
public class PagarMeService implements GatewayPagamentoService {

    private static final String BASE_URL = "https://api.pagar.me/1";

    private final RestTemplate clienteHttp = new RestTemplate();

    @Value("${br.com.treinaweb.ediaristas.pagarme.apiKey}")
    private String apiKey;

    @Autowired
    private PagamentoRepository pagamentoRepository;

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

    @Override
    public Pagamento realizarEstornoTotal(Diaria diaria) {
        try {
            return tryRealizarEstornoTotal(diaria);
        } catch (HttpClientErrorException.BadRequest exception) {
            throw new GatewayPagamentoException(exception.getLocalizedMessage());
        }
    }

    @Override
    public Pagamento realizarEstornoParcial(Diaria diaria) {
        try {
            return tryRealizarEstornoParcial(diaria);
        } catch (HttpClientErrorException.BadRequest exception) {
            throw new GatewayPagamentoException(exception.getLocalizedMessage());
        }
    }

    private Pagamento tryRealizarEstornoParcial(Diaria diaria) {
        var request = PagarMeReembolsoRequest.builder()
            .amount(converterReaisParaCentavos(diaria.getPreco().divide(new BigDecimal(2))))
            .apiKey(apiKey)
            .build();
        return realizarEstorno(diaria, request);
    }

    private Pagamento tryRealizarEstornoTotal(Diaria diaria) {
        var request = PagarMeReembolsoRequest.builder()
            .apiKey(apiKey)
            .build();
        return realizarEstorno(diaria, request);
    }

    private Pagamento realizarEstorno(Diaria diaria, PagarMeReembolsoRequest request) {
        validarDiariaParaReembolso(diaria);
        var pagamento = getPagamentoDaDiaria(diaria);
        var url = BASE_URL + "/transactions/{transaction_id}/refund";
        var response = clienteHttp.postForEntity(url, request, PagarMeReembolsoResponse.class, pagamento.getTransacaoId());
        return criarPagamento(diaria, response.getBody());
    }

    private void validarDiariaParaReembolso(Diaria diaria) {
        var isNotPagaOrNotConfirmada = !(diaria.isPago() || diaria.isConfirmado());
        if (isNotPagaOrNotConfirmada) {
            throw new IllegalArgumentException("Não pode ser feito reembolso de diária que não estão pagas");
        }
    }

    private Pagamento getPagamentoDaDiaria(Diaria diaria) {
        return diaria.getPagamentos()
            .stream()
            .filter(this::isPagamentoAceito)
            .findFirst()
            .orElseThrow(PagamentoNaoEncontradoException::new);
    }

    private boolean isPagamentoAceito(Pagamento pagamento) {
        return pagamento.getStatus().equals(PagamentoStatus.ACEITO);
    }

    private Pagamento tryPagar(Diaria diaria, String cardHash) {
        var transacaoRequest = criarTransacaoRequest(diaria, cardHash);
        var url = BASE_URL + "/transactions";
        var response = clienteHttp.postForEntity(url, transacaoRequest, PagarMeTransacaoResponse.class);
        return criarPagamento(diaria, response.getBody());
    }

    private Pagamento criarPagamento(Diaria diaria, PagarMeTransacaoResponse body) {
        var pagamento = Pagamento.builder()
            .valor(diaria.getPreco())
            .transacaoId(body.getId())
            .diaria(diaria)
            .status(criarPagamentoStatus(body.getStatus()))
            .build();
        return pagamentoRepository.save(pagamento);
    }

    private Pagamento criarPagamento(Diaria diaria, PagarMeReembolsoResponse body) {
        var pagamento = Pagamento.builder()
            .valor(converterCentavosParaReais(body.getRefundedAmount()))
            .transacaoId(body.getId())
            .diaria(diaria)
            .status(PagamentoStatus.REEMBOLSADO)
            .build();
        return pagamentoRepository.save(pagamento);
    }

    private PagamentoStatus criarPagamentoStatus(String transacaoStatus) {
        return transacaoStatus.equals("paid") ? PagamentoStatus.ACEITO : PagamentoStatus.REPROVADO;
    }

    private PagarMeTransacaoRequest criarTransacaoRequest(Diaria diaria, String cardHash) {
        return PagarMeTransacaoRequest.builder()
            .amount(converterReaisParaCentavos(diaria.getPreco()))
            .cardHash(cardHash)
            .async(false)
            .apiKey(apiKey)
            .build();
    }

    private Integer converterReaisParaCentavos(BigDecimal preco) {
        return preco.multiply(new BigDecimal(100)).intValue();
    }

    private BigDecimal converterCentavosParaReais(Integer preco) {
        return new BigDecimal(preco).divide(new BigDecimal(100));
    }

    private JsonNode getJsonNode(String responseBody) {
        try {
            return objectMapper.readTree(responseBody);
        } catch (JsonProcessingException exception) {
            throw new GatewayPagamentoException(exception.getLocalizedMessage());
        }
    }

}
