package br.com.treinaweb.ediaristas.core.listeners;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.treinaweb.ediaristas.core.events.NovoUsuarioEvent;
import br.com.treinaweb.ediaristas.core.models.Usuario;
import br.com.treinaweb.ediaristas.core.services.email.adapters.EmailService;
import br.com.treinaweb.ediaristas.core.services.email.dtos.EmailParams;

@Component
public class NovoUsuarioListener {

    @Autowired
    private EmailService emailService;

    @EventListener
    public void handleNovoUsuarioEvent(NovoUsuarioEvent event) {
        var usuario = event.getUsuario();

        if (usuario.isCliente() || usuario.isDiarista()) {
            var emailParams = criarEmailParams(usuario);

            emailService.enviarEmailComTemplateHtml(emailParams);
        }
    }

    private EmailParams criarEmailParams(Usuario usuario) {
        var props = criarEmailProps(usuario);
        var emailParams = new EmailParams();
        emailParams.setDestinatario(usuario.getEmail());
        emailParams.setAssunto("Cadastro realizado com sucesso");
        emailParams.setTemplate("emails/boas-vindas");
        emailParams.setProps(props);
        return emailParams;
    }

    private HashMap<String, Object> criarEmailProps(Usuario usuario) {
        var props = new HashMap<String, Object>();
        props.put("nome", usuario.getNomeCompleto());
        props.put("tipoUsuario", usuario.getTipoUsuario().name());
        return props;
    }

}
