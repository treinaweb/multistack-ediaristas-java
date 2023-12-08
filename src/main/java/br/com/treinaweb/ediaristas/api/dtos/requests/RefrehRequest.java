package br.com.treinaweb.ediaristas.api.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefrehRequest {

    @NotNull
    @NotEmpty
    private String refresh;

}
