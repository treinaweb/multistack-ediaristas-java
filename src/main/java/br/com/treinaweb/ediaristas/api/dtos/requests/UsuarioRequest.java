package br.com.treinaweb.ediaristas.api.dtos.requests;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class UsuarioRequest {

    @NotNull
    @Size(min = 3, max = 255)
    private String nomeCompleto;

    @NotNull
    @Size(max = 255)
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String passwordConfirmation;

    @NotNull
    private Integer tipoUsuario;

    @NotNull
    @Size(min = 11, max = 11)
    @CPF
    private String cpf;

    @NotNull
    @Past
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate nascimento;

    @NotNull
    @Size(min = 11, max = 11)
    private String telefone;

    @Size(min = 11, max = 255)
    private String chavePix;

}
