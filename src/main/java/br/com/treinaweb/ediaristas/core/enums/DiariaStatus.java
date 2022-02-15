package br.com.treinaweb.ediaristas.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiariaStatus {

    SEM_PAGAMENTO(1, "Aguardando Pagamento"),
    PAGO(2, "Paga"),
    CONFIRMADO(3, "Diarista Selecionado"),
    CONCLUIDO(4, "Presença Confirmada"),
    CANCELADO(5, "Cancelada"),
    AVALIADO(6, "Avaliada"),
    TRANSFERIDO(7, "Transferido para Diarista");

    private Integer id;

    private String descricao;

}
