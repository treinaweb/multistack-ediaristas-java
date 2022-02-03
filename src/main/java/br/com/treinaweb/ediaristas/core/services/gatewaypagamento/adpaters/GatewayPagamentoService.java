package br.com.treinaweb.ediaristas.core.services.gatewaypagamento.adpaters;

import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.models.Pagamento;

public interface GatewayPagamentoService {

    Pagamento pagar(Diaria diaria, String cardHash);

}
