package br.com.treinaweb.ediaristas.api.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.treinaweb.ediaristas.api.controllers.DiariaPagamentoRestController;
import br.com.treinaweb.ediaristas.api.controllers.DiariaRestController;
import br.com.treinaweb.ediaristas.api.dtos.responses.DiariaResponse;
import br.com.treinaweb.ediaristas.core.utils.SecurityUtils;

@Component
public class DiariaAssembler implements Assembler<DiariaResponse> {

    @Autowired
    private SecurityUtils securityUtils;

    @Override
    public void adicionarLinks(DiariaResponse resource) {
        var id = resource.getId();
        if (securityUtils.isCliente() && resource.isSemPagamento()) {
            var pagarDiariaLink = linkTo(methodOn(DiariaPagamentoRestController.class).pagar(null, id))
                .withRel("pagar_diaria")
                .withType("POST");

            resource.adcionarLinks(pagarDiariaLink);
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

}
