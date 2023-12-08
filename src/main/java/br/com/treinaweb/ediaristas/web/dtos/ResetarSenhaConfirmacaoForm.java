package br.com.treinaweb.ediaristas.web.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetarSenhaConfirmacaoForm {

    @NotNull
    @NotEmpty
    private String senha;

    @NotNull
    @NotEmpty
    private String confirmacaoSenha;

}
