package br.com.treinaweb.ediaristas.api.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import br.com.treinaweb.ediaristas.api.dtos.requests.ResetSenhaConfirmacaoRequest;
import br.com.treinaweb.ediaristas.api.dtos.requests.ResetSenhaRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.ediaristas.core.exceptions.SenhasNaoConferemException;
import br.com.treinaweb.ediaristas.core.services.PasswordResetService;
import br.com.treinaweb.ediaristas.core.services.email.adapters.EmailService;
import br.com.treinaweb.ediaristas.core.services.email.dtos.EmailParams;

@Service
public class ApiResetSenhaService {

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private EmailService emailService;

    @Value("${br.com.treinaweb.ediaristas.frontend.host}")
    private String hostFrontend;

    public MensagemResponse solictarResetDeSenha(ResetSenhaRequest request) {
        var passwordReset = passwordResetService.criarPasswordReset(request.getEmail());

        if (passwordReset != null) {
            var props = new HashMap<String, Object>();
            props.put("link", hostFrontend + "/recuperar-senha?token=" + passwordReset.getToken());
            var emailParams = EmailParams.builder()
                .destinatario(request.getEmail())
                .assunto("Solicitação de reset de senha")
                .template("emails/resetar-senha")
                .props(props)
                .build();
            emailService.enviarEmailComTemplateHtml(emailParams);
        }

        return new MensagemResponse("Verifique o seu e-mail para ter acesso ao link de reset de senha");
    }

    public MensagemResponse confirmarResetDeSenha(ResetSenhaConfirmacaoRequest request) {
        validarConfirmacaoSenha(request);
        passwordResetService.resetarSenha(request.getToken(), request.getPassword());
        return new MensagemResponse("Senha alterada com sucesso!");
    }

    private void validarConfirmacaoSenha(ResetSenhaConfirmacaoRequest request) {
        var senha = request.getPassword();
        var confirmacaoSenha = request.getPasswordConfirmation();

        if (!senha.equals(confirmacaoSenha)) {
            var mensagem = "Os dois campos de senha não conferem";
            var fieldError = new FieldError(request.getClass().getName(), "confirmacaoSenha", request.getPasswordConfirmation(), false, null, null, mensagem);
            throw new SenhasNaoConferemException(mensagem, fieldError);
        }
    }

}
