package br.com.treinaweb.ediaristas.web.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import br.com.treinaweb.ediaristas.core.exceptions.SenhasNaoConferemException;
import br.com.treinaweb.ediaristas.core.services.PasswordResetService;
import br.com.treinaweb.ediaristas.core.services.email.adapters.EmailService;
import br.com.treinaweb.ediaristas.core.services.email.dtos.EmailParams;
import br.com.treinaweb.ediaristas.web.dtos.ResetarSenhaConfirmacaoForm;
import br.com.treinaweb.ediaristas.web.dtos.ResetarSenhaForm;

@Service
public class WebResetarSenhaService {

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private EmailService emailService;

    @Value("${br.com.treinaweb.ediaristas.backend.host}")
    private String hostBackend;

    public void solictarResetDeSenha(ResetarSenhaForm form) {
        var passwordReset = passwordResetService.criarPasswordReset(form.getEmail());

        if (passwordReset != null) {
            var props = new HashMap<String, Object>();
            props.put("link", hostBackend + "/admin/resetar-senha/confirmacao?token=" + passwordReset.getToken());
            var emailParams = EmailParams.builder()
                .destinatario(form.getEmail())
                .assunto("Solicitação de reset de senha")
                .template("emails/resetar-senha")
                .props(props)
                .build();
            emailService.enviarEmailComTemplateHtml(emailParams);
        }
    }

    public void confirmarResetDeSenha(String token, ResetarSenhaConfirmacaoForm form) {
        validarConfirmacaoSenha(form);
        passwordResetService.resetarSenha(token, form.getSenha());
    }

    private void validarConfirmacaoSenha(ResetarSenhaConfirmacaoForm form) {
        var senha = form.getSenha();
        var confirmacaoSenha = form.getConfirmacaoSenha();

        if (!senha.equals(confirmacaoSenha)) {
            var mensagem = "Os dois campos de senha não conferem";
            var fieldError = new FieldError(form.getClass().getName(), "confirmacaoSenha", form.getConfirmacaoSenha(), false, null, null, mensagem);
            throw new SenhasNaoConferemException(mensagem, fieldError);
        }
    }

}
