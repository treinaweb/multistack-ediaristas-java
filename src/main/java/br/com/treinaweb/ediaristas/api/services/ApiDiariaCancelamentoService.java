package br.com.treinaweb.ediaristas.api.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.treinaweb.ediaristas.api.dtos.requests.DiariaCancelamentoRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.ediaristas.core.enums.DiariaStatus;
import br.com.treinaweb.ediaristas.core.exceptions.DiariaNaoEncontradaException;
import br.com.treinaweb.ediaristas.core.models.Avaliacao;
import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.repositories.AvaliacaoRepository;
import br.com.treinaweb.ediaristas.core.repositories.DiariaRepository;
import br.com.treinaweb.ediaristas.core.services.gatewaypagamento.adpaters.GatewayPagamentoService;
import br.com.treinaweb.ediaristas.core.utils.SecurityUtils;
import br.com.treinaweb.ediaristas.core.validators.DiariaCancelamentoValidator;

@Service
public class ApiDiariaCancelamentoService {

    @Autowired
    private DiariaRepository diariaRepository;

    @Autowired
    private DiariaCancelamentoValidator validator;

    @Autowired
    private GatewayPagamentoService gatewayPagamentoService;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Transactional(readOnly = false)
    public MensagemResponse cancelar(Long diariaId, DiariaCancelamentoRequest request) {
        var diaria = buscarDiariaPorId(diariaId);
        validator.validar(diaria);

        if (hasPenalizacao(diaria)) {
            aplicarPenalizacao(diaria);
        } else {
            gatewayPagamentoService.realizarEstornoTotal(diaria);
        }

        diaria.setStatus(DiariaStatus.CANCELADO);
        diaria.setMotivoCancelamento(request.getMotivoCancelamento());
        diariaRepository.save(diaria);

        return new MensagemResponse("A diária foi cancelado com sucesso!");
    }

    private void aplicarPenalizacao(Diaria diaria) {
        var usuarioLogado = securityUtils.getUsuarioLogado();
        if (usuarioLogado.isDiarista()) {
            penalizarDiarista(diaria);
            gatewayPagamentoService.realizarEstornoTotal(diaria);
        } else {
            gatewayPagamentoService.realizarEstornoParcial(diaria);
        }
    }

    private void penalizarDiarista(Diaria diaria) {
        var avaliacao = Avaliacao.builder()
            .nota(1.0)
            .descricao("Penalização diária cancelada")
            .avaliado(diaria.getDiarista())
            .visibilidade(false)
            .diaria(diaria)
            .build();
        avaliacaoRepository.save(avaliacao);
    }

    private Diaria buscarDiariaPorId(Long diariaId) {
        var mensagem = String.format("Diária com id %d não encontrada", diariaId);
        return diariaRepository.findById(diariaId)
            .orElseThrow(() -> new DiariaNaoEncontradaException(mensagem));
    }

    private boolean hasPenalizacao(Diaria diaria) {
        var hoje = LocalDateTime.now();
        return ChronoUnit.HOURS.between(hoje, diaria.getDataAtendimento()) < 24;
    }

}
