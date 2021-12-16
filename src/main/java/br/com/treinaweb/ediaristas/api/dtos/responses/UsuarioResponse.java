package br.com.treinaweb.ediaristas.api.dtos.responses;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class UsuarioResponse {

    private Long id;
    private String nomeCompleto;
    private String email;
    private Integer tipoUsuario;
    private String cpf;
    private LocalDate nascimento;
    private String telefone;
    private String chavePix;

}
