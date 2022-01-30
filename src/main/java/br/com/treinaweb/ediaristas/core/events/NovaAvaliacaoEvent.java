package br.com.treinaweb.ediaristas.core.events;

import org.springframework.context.ApplicationEvent;

import br.com.treinaweb.ediaristas.core.models.Avaliacao;
import lombok.Getter;

@Getter
public class NovaAvaliacaoEvent extends ApplicationEvent {

    private Avaliacao avaliacao;

    public NovaAvaliacaoEvent(Object source, Avaliacao avaliacao) {
        super(source);
        this.avaliacao = avaliacao;
    }

}
