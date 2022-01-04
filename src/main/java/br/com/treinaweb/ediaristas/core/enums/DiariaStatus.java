package br.com.treinaweb.ediaristas.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiariaStatus {

    SEM_PAGAMENTO(1),
    PAGO(2),
    CONFIRMADO(3),
    CONCLUIDO(4),
    CANCELADO(5),
    AVALIADO(6),
    TRANSFERIDO(7);

    private Integer id;

}
