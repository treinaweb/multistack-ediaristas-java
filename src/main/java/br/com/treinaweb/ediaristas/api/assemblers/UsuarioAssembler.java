package br.com.treinaweb.ediaristas.api.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.treinaweb.ediaristas.api.controllers.CidadesAtendidasRestController;
import br.com.treinaweb.ediaristas.api.controllers.DiariaRestController;
import br.com.treinaweb.ediaristas.api.controllers.EnderecoDiaristaRestController;
import br.com.treinaweb.ediaristas.api.controllers.OportunidadeRestController;
import br.com.treinaweb.ediaristas.api.controllers.UsuarioRestController;
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

            var relacionarCidadesLink = linkTo(methodOn(CidadesAtendidasRestController.class).atualizarCidadesAtendidas(null))
                .withRel("relacionar_cidades")
                .withType("PUT");

            var cidadesAtendidasLink = linkTo(methodOn(CidadesAtendidasRestController.class).listarCidadesAtendidas())
                .withRel("cidades_atendidas")
                .withType("GET");

            var listaOportunidadesLink = linkTo(methodOn(OportunidadeRestController.class).buscarOportunidades())
                .withRel("lista_oportunidades")
                .withType("GET");

            resource.adcionarLinks(atualizarEnderecoLink, listarEnderecoLink, relacionarCidadesLink, cidadesAtendidasLink, listaOportunidadesLink);
        }

        var listaDiariasLink = linkTo(methodOn(DiariaRestController.class).listarPorUsuarioLogado())
            .withRel("lista_diarias")
            .withType("GET");

        var alterarFotoUsuarioLink = linkTo(methodOn(UsuarioRestController.class).atualizarFotoUsuario(null))
            .withRel("alterar_foto_usuario")
            .withType("POST");

        var editarUsuarioLink = linkTo(methodOn(UsuarioRestController.class).atualizar(null))
            .withRel("editar_usuario")
            .withType("PUT");

        resource.adcionarLinks(listaDiariasLink, alterarFotoUsuarioLink, editarUsuarioLink);
    }

    @Override
    public void adicionarLinks(List<UsuarioResponse> collectionResource) {}

}
