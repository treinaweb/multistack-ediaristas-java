package br.com.treinaweb.ediaristas.api.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.api.dtos.responses.HateoasResponse;

@RestController
@RequestMapping("/api")
public class HomeRestController {

    @GetMapping
    public HateoasResponse home() {
        var listarServicosLink = linkTo(methodOn(ServicoRestController.class).buscarTodos())
            .withRel("listar_servicos")
            .withType("GET");
        var enderecoCepLink = linkTo(methodOn(EnderecoRestController.class).buscarEnderecoPorCep(null))
            .withRel("endereco_cep")
            .expand()
            .withType("GET");
        var diaristasLocalidadesLink = linkTo(methodOn(DiaristaRestController.class).buscarDiaristasPorCep(null))
            .withRel("diaristas_localidades")
            .expand()
            .withType("GET");
        var verificarDisponibilidadeAtendimentoLink = linkTo(methodOn(DiaristaRestController.class).verificarDisponibilidadePorCep(null))
            .withRel("verificar_disponibilidade_atendimento")
            .expand()
            .withType("GET");
        var cadastrarUsuarioLink = linkTo(methodOn(UsuarioRestController.class).cadastrar(null))
            .withRel("cadastrar_usuario")
            .withType("POST");
        var loginLink = linkTo(methodOn(AuthRestController.class).autenticar(null))
            .withRel("login")
            .withType("POST");
        var refreshLink = linkTo(methodOn(AuthRestController.class).reautenticar(null))
            .withRel("refresh")
            .withType("POST");
        var logoutLink = linkTo(methodOn(AuthRestController.class).logout(null))
            .withRel("logout")
            .withType("POST");
        var usuarioLogadoLink = linkTo(methodOn(MeRestController.class).me())
            .withRel("usuario_logado")
            .withType("GET");

        var response = new HateoasResponse();
        response.adcionarLinks(
            listarServicosLink,
            enderecoCepLink,
            diaristasLocalidadesLink,
            verificarDisponibilidadeAtendimentoLink,
            cadastrarUsuarioLink,
            loginLink,
            refreshLink,
            logoutLink,
            usuarioLogadoLink
        );
        return response;
    }

}
