package br.com.treinaweb.ediaristas.api.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.treinaweb.ediaristas.api.controllers.AvaliacaoRestController;
import br.com.treinaweb.ediaristas.api.controllers.ConfirmacaoPresencaRestController;
import br.com.treinaweb.ediaristas.api.controllers.DiariaCancelamentoRestController;
import br.com.treinaweb.ediaristas.api.controllers.DiariaPagamentoRestController;
import br.com.treinaweb.ediaristas.api.controllers.DiariaRestController;
import br.com.treinaweb.ediaristas.api.dtos.responses.DiariaResponse;
import br.com.treinaweb.ediaristas.core.repositories.AvaliacaoRepository;
import br.com.treinaweb.ediaristas.core.utils.SecurityUtils;

@Component
public class DiariaAssembler implements Assembler<DiariaResponse> {

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Override
    public void adicionarLinks(DiariaResponse resource) {
        var id = resource.getId();
        if (securityUtils.isCliente() && resource.isSemPagamento()) {
            var pagarDiariaLink = linkTo(methodOn(DiariaPagamentoRestController.class).pagar(null, id))
                .withRel("pagar_diaria")
                .withType("POST");

            resource.adcionarLinks(pagarDiariaLink);
        }

        if (isAptaParaConfirmacaoDePresenca(resource)) {
            var confirmarPresencaLink = linkTo(methodOn(ConfirmacaoPresencaRestController.class).confirmarPresenca(id))
                .withRel("confirmar_diarista")
                .withType("PATCH");

            resource.adcionarLinks(confirmarPresencaLink);
        }

        if (isAptaParaAvaliacao(resource)) {
            var avaliacaoLink = linkTo(methodOn(AvaliacaoRestController.class).avaliarDiaria(null, id))
                .withRel("avaliar_diaria")
                .withType("PATCH");
            resource.adcionarLinks(avaliacaoLink);
        }

        if (isAptaParaCancelamento(resource)) {
            var cancelarDiariaLink = linkTo(methodOn(DiariaCancelamentoRestController.class).cancelar(id, null))
                .withRel("cancelar_diaria")
                .withType("PATCH");
            resource.adcionarLinks(cancelarDiariaLink);
        }

        var selfLink = linkTo(methodOn(DiariaRestController.class).buscarPorId(id))
            .withSelfRel()
            .withType("GET");

        resource.adcionarLinks(selfLink);
    }

    @Override
    public void adicionarLinks(List<DiariaResponse> collectionResource) {
        collectionResource.stream()
            .forEach(this::adicionarLinks);
    }

    private boolean isAptaParaConfirmacaoDePresenca(DiariaResponse resource) {
        return resource.isConfirmado()
            && isDataAtendimentoNoPassado(resource)
            && resource.getDiarista() != null;
    }

    private boolean isDataAtendimentoNoPassado(DiariaResponse resource) {
        return resource.getDataAtendimento().isBefore(LocalDateTime.now());
    }

    private boolean isAptaParaAvaliacao(DiariaResponse resource) {
        return resource.isConcluido()
            && !avaliacaoRepository.existsByAvaliadorAndDiariaId(securityUtils.getUsuarioLogado(), resource.getId());
    }

    private boolean isAptaParaCancelamento(DiariaResponse resource) {
        return (resource.isPago() || resource.isConfirmado())
            && resource.getDataAtendimento().isAfter(LocalDateTime.now());
    }

}
