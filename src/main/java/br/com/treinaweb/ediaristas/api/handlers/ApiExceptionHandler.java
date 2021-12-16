package br.com.treinaweb.ediaristas.api.handlers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.treinaweb.ediaristas.api.dtos.responses.ErrorResponse;
import br.com.treinaweb.ediaristas.core.services.consultaendereco.exceptions.EnderecoServiceException;

@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private SnakeCaseStrategy camelCaseToSnakeCase = new SnakeCaseStrategy();

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException exception,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
    ) {
        var body = new HashMap<String, List<String>>();

        exception.getBindingResult().getFieldErrors()
            .forEach(fieldError -> {
                var field = camelCaseToSnakeCase.translate(fieldError.getField());

                if (!body.containsKey(field)) {
                    var fieldErrors = new ArrayList<String>();
                    fieldErrors.add(fieldError.getDefaultMessage());

                    body.put(field, fieldErrors);
                } else {
                    body.get(field).add(fieldError.getDefaultMessage());
                }
            });

        return ResponseEntity.badRequest().body(body);
    }

}
