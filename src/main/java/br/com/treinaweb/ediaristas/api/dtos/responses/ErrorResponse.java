package br.com.treinaweb.ediaristas.api.dtos.responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private Integer status;

    private LocalDateTime timestamp;

    private String mensagem;

    private String path;

}
