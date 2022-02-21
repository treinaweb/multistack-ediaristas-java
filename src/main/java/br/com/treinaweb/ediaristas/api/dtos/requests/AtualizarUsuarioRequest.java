package br.com.treinaweb.ediaristas.api.dtos.requests;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.treinaweb.ediaristas.core.validators.Idade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class AtualizarUsuarioRequest {

    @Size(min = 3, max = 255)
    private String nomeCompleto;

    @Email
    @Size(max = 255)
    private String email;

    @CPF
    @Size(min = 11, max = 11)
    private String cpf;

    @Past
    @Idade(min = 18, max = 100)
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate nascimento;

    @Size(min = 11, max = 11)
    private String telefone;

    @Size(min = 11, max = 255)
    private String chavePix;

    @Size(max = 255)
    private String password;

    @Size(max = 255)
    private String newPassword;

    @Size(max = 255)
    private String passwordConfirmation;

}
