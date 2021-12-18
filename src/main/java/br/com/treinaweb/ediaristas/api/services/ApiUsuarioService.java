package br.com.treinaweb.ediaristas.api.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import br.com.treinaweb.ediaristas.api.dtos.requests.UsuarioRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.UsuarioResponse;
import br.com.treinaweb.ediaristas.api.mappers.ApiUsuarioMapper;
import br.com.treinaweb.ediaristas.core.exceptions.SenhasNaoConferemException;
import br.com.treinaweb.ediaristas.core.repositories.UsuarioRepository;
import br.com.treinaweb.ediaristas.core.services.email.adapters.EmailService;
import br.com.treinaweb.ediaristas.core.services.email.dtos.EmailParams;
import br.com.treinaweb.ediaristas.core.services.storage.adapters.StorageService;
import br.com.treinaweb.ediaristas.core.validators.UsuarioValidator;

@Service
public class ApiUsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private ApiUsuarioMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioValidator validator;

    @Autowired
    private StorageService storageService;

    @Autowired
    private EmailService emailService;

    public UsuarioResponse cadastrar(UsuarioRequest request) {
        validarConfirmacaoSenha(request);

        var usuarioParaCadastrar = mapper.toModel(request);

        validator.validar(usuarioParaCadastrar);

        var senhaEnctiptada = passwordEncoder.encode(usuarioParaCadastrar.getSenha());
        usuarioParaCadastrar.setSenha(senhaEnctiptada);

        var fotoDocumento = storageService.salvar(request.getFotoDocumento());
        usuarioParaCadastrar.setFotoDocumento(fotoDocumento);

        if (usuarioParaCadastrar.isDiarista()) {
            var reputacaoMedia = calcularMediaReputacaoDiaristas();
            usuarioParaCadastrar.setReputacao(reputacaoMedia);
        }

        var usuarioCadastrado = repository.save(usuarioParaCadastrar);

        if (usuarioCadastrado.isCliente() || usuarioCadastrado.isDiarista()) {
            var props = new HashMap<String, Object>();
            props.put("nome", usuarioCadastrado.getNomeCompleto());
            props.put("tipoUsuario", usuarioCadastrado.getTipoUsuario().name());
            var emailParams = new EmailParams();
            emailParams.setDestinatario(usuarioCadastrado.getEmail());
            emailParams.setAssunto("Cadastro realizado com sucesso");
            emailParams.setTemplate("emails/boas-vindas");
            emailParams.setProps(props);

            emailService.enviarEmailComTemplateHtml(emailParams);
        }

        return mapper.toResponse(usuarioCadastrado);
    }

    private Double calcularMediaReputacaoDiaristas() {
        var reputacaoMedia = repository.getMediaReputacaoDiarista();
        if (reputacaoMedia == null || reputacaoMedia == 0.0) {
            reputacaoMedia = 5.0;
        }
        return reputacaoMedia;
    }

    private void validarConfirmacaoSenha(UsuarioRequest request) {
        var senha = request.getPassword();
        var confirmacaoSenha = request.getPasswordConfirmation();

        if (!senha.equals(confirmacaoSenha)) {
            var mensagem = "Os dois campos de senha não conferem";
            var fieldError = new FieldError(request.getClass().getName(), "passwordConfirmation", request.getPasswordConfirmation(), false, null, null, mensagem);

            throw new SenhasNaoConferemException(mensagem, fieldError);
        }
    }

}
