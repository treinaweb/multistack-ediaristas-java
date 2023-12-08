package br.com.treinaweb.ediaristas.api.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetSenhaRequest {

    @Email
    @NonNull
    @NotEmpty
    private String email;

}
