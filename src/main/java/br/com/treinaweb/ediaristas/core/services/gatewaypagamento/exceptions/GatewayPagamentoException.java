package br.com.treinaweb.ediaristas.core.services.gatewaypagamento.exceptions;

public class GatewayPagamentoException extends RuntimeException {

    public GatewayPagamentoException() {}

    public GatewayPagamentoException(String message) {
        super(message);
    }

}
