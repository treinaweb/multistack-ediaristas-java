package br.com.treinaweb.ediaristas.api.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {

    @NotNull
    @NotEmpty
    @Email
    @Size(max = 255)
    private String email;

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String senha;

}
