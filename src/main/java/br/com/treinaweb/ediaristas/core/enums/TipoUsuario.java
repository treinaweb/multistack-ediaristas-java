package br.com.treinaweb.ediaristas.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoUsuario {
    CLIENTE (1),
    DIARISTA (2),
    ADMIN (3);

    private Integer id;
}
