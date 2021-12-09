package br.com.treinaweb.ediaristas.api.handlers;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.treinaweb.ediaristas.api.dtos.responses.ErrorResponse;
import br.com.treinaweb.ediaristas.core.services.consultaendereco.exceptions.EnderecoServiceException;

@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EnderecoServiceException.class)
    public ResponseEntity<Object> handleEnderecoServiceException(
        EnderecoServiceException exception, HttpServletRequest request
    ) {
        var errorReponse = ErrorResponse.builder()
            .status(400)
            .timestamp(LocalDateTime.now())
            .mensagem(exception.getLocalizedMessage())
            .path(request.getRequestURI())
            .build();

        return ResponseEntity.badRequest().body(errorReponse);
    }

}
