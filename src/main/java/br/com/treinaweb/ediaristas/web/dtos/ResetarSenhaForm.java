package br.com.treinaweb.ediaristas.web.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetarSenhaForm {

    @NotNull
    @NotEmpty
    @Email
    private String email;

}
