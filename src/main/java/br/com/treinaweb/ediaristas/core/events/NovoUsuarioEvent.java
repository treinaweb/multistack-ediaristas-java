package br.com.treinaweb.ediaristas.core.events;

import org.springframework.context.ApplicationEvent;

import br.com.treinaweb.ediaristas.core.models.Usuario;
import lombok.Getter;

@Getter
public class NovoUsuarioEvent extends ApplicationEvent {

    private Usuario usuario;

    public NovoUsuarioEvent(Object source, Usuario usuario) {
        super(source);
        this.usuario = usuario;
    }

}
