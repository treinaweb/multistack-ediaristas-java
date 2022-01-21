package br.com.treinaweb.ediaristas.api.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.treinaweb.ediaristas.api.controllers.CandidaturaRestController;
import br.com.treinaweb.ediaristas.api.dtos.responses.OportunidadeResponse;

@Component
public class OportunidadeAssembler implements Assembler<OportunidadeResponse> {

    @Override
    public void adicionarLinks(OportunidadeResponse resource) {
        var id = resource.getId();

        var candidatarDiariaLink = linkTo(methodOn(CandidaturaRestController.class).candidatar(id))
            .withRel("candidatar_diaria")
            .withType("POST");

        resource.adcionarLinks(candidatarDiariaLink);
    }

    @Override
    public void adicionarLinks(List<OportunidadeResponse> collectionResource) {
        collectionResource.stream()
            .forEach(this::adicionarLinks);
    }

}
