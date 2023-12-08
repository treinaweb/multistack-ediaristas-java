package br.com.treinaweb.ediaristas.api.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDiaristaRequest {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 60)
    private String logradouro;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 10)
    private String numero;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 30)
    private String bairro;

    @Size(max = 255)
    private String complemento;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 8)
    private String cep;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 30)
    private String cidade;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 2)
    private String estado;

}
