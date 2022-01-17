package br.com.treinaweb.ediaristas.api.handlers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.treinaweb.ediaristas.api.dtos.responses.ErrorResponse;
import br.com.treinaweb.ediaristas.core.exceptions.TokenNaBlackListException;
import br.com.treinaweb.ediaristas.core.exceptions.ValidacaoException;
import br.com.treinaweb.ediaristas.core.services.consultacidade.exceptions.ConsultaCidadeServiceException;
import br.com.treinaweb.ediaristas.core.services.consultaendereco.exceptions.EnderecoServiceException;
import br.com.treinaweb.ediaristas.core.services.token.exceptions.TokenServiceException;

@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private SnakeCaseStrategy camelCaseToSnakeCase = new SnakeCaseStrategy();

    @ExceptionHandler(EnderecoServiceException.class)
    public ResponseEntity<Object> handleEnderecoServiceException(
        EnderecoServiceException exception, HttpServletRequest request
    ) {
        return criarErrorResponse(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), request.getRequestURI());
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<Object> handleValidacaoException(ValidacaoException exception) {
        var body = new HashMap<String, List<String>>();

        var fieldError = exception.getFieldError();
        var fieldErrors = new ArrayList<String>();

        fieldErrors.add(fieldError.getDefaultMessage());
        var field = camelCaseToSnakeCase.translate(fieldError.getField());
        body.put(field, fieldErrors);

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(TokenServiceException.class)
    public ResponseEntity<Object> handleTokenServiceException(
        TokenServiceException exception, HttpServletRequest request
    ) {
        return criarErrorResponse(HttpStatus.UNAUTHORIZED, exception.getLocalizedMessage(), request.getRequestURI());
    }

    @ExceptionHandler(TokenNaBlackListException.class)
    public ResponseEntity<Object> handleTokenNaBlackListException(
        TokenNaBlackListException exception, HttpServletRequest request
    ) {
        return criarErrorResponse(HttpStatus.UNAUTHORIZED, exception.getLocalizedMessage(), request.getRequestURI());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(
        EntityNotFoundException exception, HttpServletRequest request
    ) {
        return criarErrorResponse(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(), request.getRequestURI());
    }

    @ExceptionHandler(ConsultaCidadeServiceException.class)
    public ResponseEntity<Object> handleConsultaCidadeServiceException(
        ConsultaCidadeServiceException exception, HttpServletRequest request
    ) {
        return criarErrorResponse(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(), request.getRequestURI());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException exception,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
    ) {
        return handleBindException(exception, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(
        BindException exception,
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

    private ResponseEntity<Object> criarErrorResponse(HttpStatus status, String mensagem, String path) {
        var errorResponse = ErrorResponse.builder()
            .status(status.value())
            .timestamp(LocalDateTime.now())
            .mensagem(mensagem)
            .path(path)
            .build();

        return new ResponseEntity<>(errorResponse, status);
    }

}
