package br.com.treinaweb.ediaristas.api.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.treinaweb.ediaristas.api.controllers.DiariaRestController;
import br.com.treinaweb.ediaristas.api.controllers.EnderecoDiaristaRestController;
import br.com.treinaweb.ediaristas.api.dtos.responses.UsuarioResponse;

@Component
public class UsuarioAssembler implements Assembler<UsuarioResponse> {

    @Override
    public void adicionarLinks(UsuarioResponse resource) {
        if (resource.isCliente()) {
            var cadastrarDiariaLink = linkTo(methodOn(DiariaRestController.class).cadastrar(null))
                .withRel("cadastrar_diaria")
                .withType("POST");

            resource.adcionarLinks(cadastrarDiariaLink);
        } else {
            var atualizarEnderecoLink = linkTo(methodOn(EnderecoDiaristaRestController.class).alterarEndereco(null))
                .withRel("atualizar_endereco")
                .withType("PUT");

            var listarEnderecoLink = linkTo(methodOn(EnderecoDiaristaRestController.class).exibirEndereco())
                .withRel("listar_endereco")
                .withType("GET");

            resource.adcionarLinks(atualizarEnderecoLink, listarEnderecoLink);
        }

        var listaDiariasLink = linkTo(methodOn(DiariaRestController.class).listarPorUsuarioLogado())
            .withRel("lista_diarias")
            .withType("GET");

        resource.adcionarLinks(listaDiariasLink);
    }

    @Override
    public void adicionarLinks(List<UsuarioResponse> collectionResource) {}

}
